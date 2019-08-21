package la.linguagem.AnalisadorLA;

import la.linguagem.ANTLR.laBaseVisitor;
import la.linguagem.ANTLR.laParser;
import la.linguagem.ANTLR.laParser.CmdAtribuicaoContext;
import la.linguagem.ANTLR.laParser.CmdRetorneContext;
import la.linguagem.ANTLR.laParser.DeclaracaoLocalTipoContext;
import la.linguagem.ANTLR.laParser.Declaracao_global_funcaoContext;
import la.linguagem.ANTLR.laParser.Declaracao_global_procedimentoContext;
import la.linguagem.ANTLR.laParser.ExpressaoContext;
import la.linguagem.ANTLR.laParser.Expressao_aritmeticaContext;
import la.linguagem.ANTLR.laParser.Expressao_relacionalContext;
import la.linguagem.ANTLR.laParser.FatorContext;
import la.linguagem.ANTLR.laParser.Fator_logicoContext;
import la.linguagem.ANTLR.laParser.IdentificadorContext;
import la.linguagem.ANTLR.laParser.ParametroContext;
import la.linguagem.ANTLR.laParser.ParcelaContext;
import la.linguagem.ANTLR.laParser.Parcela_logicaContext;
import la.linguagem.ANTLR.laParser.Parcela_nao_unariaContext;
import la.linguagem.ANTLR.laParser.Parcela_unariaContext;
import la.linguagem.ANTLR.laParser.TermoContext;
import la.linguagem.ANTLR.laParser.Termo_logicoContext;
import la.linguagem.ANTLR.laParser.VariavelContext;

public class AnalisadorSemantico extends laBaseVisitor {

	SaidaParser saida = SaidaParser.getInstance();

	PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();

	@Override
	public Void visitPrograma(laParser.ProgramaContext ctx) {
		pilhaDeTabelas.empilhar(new TabelaDeSimbolos("global"));

		super.visitPrograma(ctx);

		pilhaDeTabelas.desempilhar();
		return null;
	}

	@Override
	public Void visitVariavel(VariavelContext ctx) {

		String tipoCompleto = ctx.tipo().getText();
		String tipo = tipoCompleto.startsWith("^") ? tipoCompleto.substring(1) : tipoCompleto;

		if (!tipo.equals("literal") && !tipo.equals("inteiro") && !tipo.equals("real") && !tipo.equals("logico")
				&& !pilhaDeTabelas.existeSimbolo(tipo)) {
			saida.println(
					"Linha " + ctx.tipo().getStart().getLine() + ": tipo " + ctx.tipo().getText() + " nao declarado");
		}

		for (IdentificadorContext variavel : ctx.identificadores) {
			if (pilhaDeTabelas.existeSimbolo(variavel.getText())) {
				saida.println("Linha " + variavel.getStart().getLine() + ": identificador " + variavel.getText()
						+ " ja declarado anteriormente");
			} else {
				pilhaDeTabelas.topo().adicionarSimbolo(variavel.getText(), tipoCompleto, "variavel");
			}
		}

		return null;

	}

	@Override
	public Void visitIdentificador(IdentificadorContext ctx) {
		if (!pilhaDeTabelas.existeSimbolo(ctx.identificador1.getText())) {
			saida.println("Linha " + ctx.identificador1.getLine() + ": identificador " + ctx.identificador1.getText()
					+ " nao declarado");
		}

		return null;
	}

	@Override
	public Object visitCmdAtribuicao(CmdAtribuicaoContext ctx) {
		String tipoDoIdentificador = pilhaDeTabelas.getTipo(ctx.identificador().getText());
		String tipoDaExpressao = verificaTipo(ctx.expressao());

		boolean incompativel = false;

		if (tipoDoIdentificador.startsWith("^")) {
			if (tipoDaExpressao.startsWith("&") && !tipoDaExpressao.substring(1).equals(tipoDaExpressao.substring(1))) {
				incompativel = true;
			} else if (ctx.getStart().getText().equals("^")
					&& !isTiposCompativeis(tipoDaExpressao.substring(1), tipoDaExpressao)) {
				incompativel = true;
			}

		} else {
			if (!isTiposCompativeis(tipoDoIdentificador, tipoDaExpressao)) {
				incompativel = true;
			}
		}

		if (incompativel) {
			String destino = ctx.getStart().getText().equals("^") ? "^" + ctx.identificador().getText()
					: ctx.identificador().getText();
			saida.println("Linha " + ctx.identificador().getStart().getLine() + ": atribuicao nao compativel para "
					+ destino);
		}

		return super.visitChildren(ctx);
	}

	@Override
	public Object visitDeclaracao_global_procedimento(Declaracao_global_procedimentoContext ctx) {
		String nomeProcedimento = ctx.IDENT().getText();
		if (pilhaDeTabelas.existeSimbolo(nomeProcedimento)) {
			// TODO ver saída de erro ideal
		} else {
			pilhaDeTabelas.topo().adicionarSimbolo(nomeProcedimento, "null", "procedimento");

			pilhaDeTabelas.empilhar(new TabelaDeSimbolos(nomeProcedimento));
			for (ParametroContext parametro : ctx.parametros().parametro()) {
				visitParametro(parametro);
			}
		}

		return super.visitChildren(ctx);
	}

	@Override
	public Object visitDeclaracao_global_funcao(Declaracao_global_funcaoContext ctx) {
		String nomeFuncao = ctx.IDENT().getText();
		String tipoRetorno = ctx.tipo_estendido().getText();

		if (pilhaDeTabelas.existeSimbolo(nomeFuncao)) {
			// TODO ver saída de erro ideal
		} else {
			pilhaDeTabelas.topo().adicionarSimbolo(nomeFuncao, tipoRetorno, "funcao");

			pilhaDeTabelas.empilhar(new TabelaDeSimbolos(nomeFuncao));
			for (ParametroContext parametro : ctx.parametros().parametro()) {
				visitParametro(parametro);
			}
		}
		return super.visitChildren(ctx);
	}

	@Override
	public Object visitParametro(ParametroContext ctx) {
		String tipo = ctx.tipo_estendido().getText();
		for (IdentificadorContext identificador : ctx.identificador()) {
			pilhaDeTabelas.topo().adicionarSimbolo(identificador.getText(), tipo, "parametro");
		}
		return super.visitChildren(ctx);
	}

	private String verificaTipo(ExpressaoContext ctx) {
		String tipo = verificaTipo(ctx.termo_logico(0));
		if (ctx.termo_logico().size() > 1) {
			for (int i = 1; i < ctx.termo_logico().size(); i++) {
				tipo = castTipo(tipo, verificaTipo(ctx.termo_logico(i)));
			}
		}
		return tipo;
	}

	@Override
	public Object visitDeclaracaoLocalTipo(DeclaracaoLocalTipoContext ctx) {
		String novoTipo = ctx.IDENT().getText();
		if (pilhaDeTabelas.existeSimbolo(novoTipo)) {
			// TODO exibir erro adequado
		} else {
			pilhaDeTabelas.topo().adicionarSimbolo(novoTipo, ctx.tipo().getText(), "tipo");
		}

		return super.visitChildren(ctx);
	}

	@Override
	public Object visitCmdRetorne(CmdRetorneContext ctx) {
		String escopo = pilhaDeTabelas.topo().getEscopo();
		if (!pilhaDeTabelas.getSimbolo(escopo).equals("funcao")) {
			saida.println("Linha " + ctx.getStart().getLine() + ": comando retorne nao permitido nesse escopo");
		}
		return null;
	}

	private String verificaTipo(Termo_logicoContext ctx) {
		String tipo = verificaTipo(ctx.fator_logico(0));
		if (ctx.fator_logico().size() > 1) {
			for (int i = 1; i < ctx.fator_logico().size(); i++) {
				tipo = castTipo(tipo, verificaTipo(ctx.fator_logico(i)));
			}
		}
		return tipo;
	}

	private String verificaTipo(Fator_logicoContext ctx) {
		if (ctx.getStart().getText().equals("nao")) {
			if (!verificaTipo(ctx.parcela_logica()).equals("logico")) {
				return "null";
			}
		}
		return verificaTipo(ctx.parcela_logica());
	}

	private String verificaTipo(Parcela_logicaContext ctx) {
		if (ctx.expressao_relacional() != null) {
			return verificaTipo(ctx.expressao_relacional());
		} else {
			return "logico";
		}
	}

	private String verificaTipo(Expressao_relacionalContext ctx) {
		String tipo = verificaTipo(ctx.expressao_aritmetica(0));
		if (ctx.expressao_aritmetica().size() > 1) {
			for (int i = 1; i < ctx.expressao_aritmetica().size(); i++) {
				tipo = castTipo(tipo, verificaTipo(ctx.expressao_aritmetica(i)));
			}
		}

		if (ctx.operador_relacional() != null) {
			return "logico";
		}
		return tipo;
	}

	private String verificaTipo(Expressao_aritmeticaContext ctx) {
		String tipo = verificaTipo(ctx.termo(0));
		if (ctx.termo().size() > 1) {
			for (int i = 1; i < ctx.termo().size(); i++) {
				tipo = castTipo(tipo, verificaTipo(ctx.termo(i)));
			}
		}
		return tipo;
	}

	private String verificaTipo(TermoContext ctx) {
		String tipo = verificaTipo(ctx.fator(0));
		if (ctx.fator().size() > 1) {
			for (int i = 1; i < ctx.fator().size(); i++) {
				tipo = castTipo(tipo, verificaTipo(ctx.fator(i)));
			}
		}
		return tipo;
	}

	private String verificaTipo(FatorContext ctx) {
		String tipo = verificaTipo(ctx.parcela(0));
		if (ctx.parcela().size() > 1) {
			for (int i = 1; i < ctx.parcela().size(); i++) {
				tipo = castTipo(tipo, verificaTipo(ctx.parcela(i)));
			}
		}
		return tipo;
	}

	private String verificaTipo(ParcelaContext ctx) {
		if (ctx.parcela_unaria() != null) {
			return verificaTipo(ctx.parcela_unaria());
		} else if (ctx.parcela_nao_unaria() != null) {
			return verificaTipo(ctx.parcela_nao_unaria());
		}
		return null;
	}

	private String verificaTipo(Parcela_unariaContext ctx) {
		if (ctx.identificador() != null) {
			String tipoIdentificador = pilhaDeTabelas.getTipo(ctx.identificador().getText());
			if (ctx.getStart().getText().equals("^")) {
				return "^" + tipoIdentificador;
			}
			return tipoIdentificador;
		} else if (ctx.IDENT() != null) {
			return pilhaDeTabelas.getTipo(ctx.IDENT().getText());
		} else if (ctx.NUM_INT() != null) {
			return "inteiro";
		} else if (ctx.NUM_REAL() != null) {
			return "real";
		} else if (ctx.expressao() != null) {
			return verificaTipo(ctx.expressao(0));
		}

		return null;
	}

	private String verificaTipo(Parcela_nao_unariaContext ctx) {
		if (ctx.CADEIA() != null) {
			return "literal";
		} else if (ctx.identificador() != null) {
			return "&identificador";
		}

		return null;
	}

	private String castTipo(String arg1, String arg2) {
		String tipo = "null";

		if (arg1.equals(arg2)) {
			tipo = arg1;
		} else {
			if (isTiposCompativeis(arg1, arg2)) {
				tipo = "real";
			}
		}

		return tipo;
	}

	private boolean isTiposCompativeis(String arg1, String arg2) {
		boolean compativeis = true;

		if (!arg1.equals(arg2)) {
			if ((arg1.equals("inteiro") && arg2.equals("real")) || (arg1.equals("real") && arg2.equals("inteiro"))) {
				compativeis = true;
			} else {
				compativeis = false;
			}
		}

		return compativeis;
	}

}
