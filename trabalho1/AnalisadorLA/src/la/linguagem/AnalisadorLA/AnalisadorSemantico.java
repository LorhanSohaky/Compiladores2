package la.linguagem.AnalisadorLA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import la.linguagem.ANTLR.laBaseVisitor;
import la.linguagem.ANTLR.laParser;
import la.linguagem.ANTLR.laParser.CmdAtribuicaoContext;
import la.linguagem.ANTLR.laParser.CmdRetorneContext;
import la.linguagem.ANTLR.laParser.DeclaracaoLocalConstanteContext;
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
import la.linguagem.ANTLR.laParser.RegistroContext;
import la.linguagem.ANTLR.laParser.TermoContext;
import la.linguagem.ANTLR.laParser.Termo_logicoContext;
import la.linguagem.ANTLR.laParser.VariavelContext;

public class AnalisadorSemantico extends laBaseVisitor<Object> {

	SaidaParser saida = SaidaParser.getInstance();
	PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
	HashMap<String, List<EntradaTabelaDeSimbolos>> tabelaDeParametros = new HashMap<String, List<EntradaTabelaDeSimbolos>>();
	HashMap<String, List<EntradaTabelaDeSimbolos>> tabelaDeRegistros = new HashMap<String, List<EntradaTabelaDeSimbolos>>();
	ArrayList<String> tabelaDeTipos = new ArrayList<String>();

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
				&& !tipoCompleto.contains("registro") && !pilhaDeTabelas.existeSimbolo(tipo)) {
			saida.println(
					"Linha " + ctx.tipo().getStart().getLine() + ": tipo " + ctx.tipo().getText() + " nao declarado");
		}

		for (IdentificadorContext variavel : ctx.identificadores) {
			String identificador = variavel.getText().contains("[")
					? variavel.getText().substring(0, variavel.getText().indexOf("["))
					: variavel.getText();
			if (pilhaDeTabelas.existeSimbolo(identificador)) {
				saida.println("Linha " + variavel.getStart().getLine() + ": identificador " + identificador
						+ " ja declarado anteriormente");
			} else {

				// variável é um registro sem nome
				if (tipoCompleto.contains("registro")) {
					if (!tabelaDeRegistros.containsKey(identificador)) {
						tabelaDeRegistros.put(identificador, new ArrayList<>());
						visitChildren(ctx.tipo());
					}
				}

				if (pilhaDeTabelas.topo().getEscopo().startsWith("registro")) {
					String id_reg = pilhaDeTabelas.topo().getEscopo();
					EntradaTabelaDeSimbolos atributo = new EntradaTabelaDeSimbolos(identificador, tipoCompleto,
							"campo");
					// adicionando variável à lista de atributos do registro
					id_reg = id_reg.substring(id_reg.indexOf("[") + 1, id_reg.indexOf("]"));
					if (tabelaDeRegistros.containsKey(id_reg)) {
						tabelaDeRegistros.get(id_reg).add(atributo);
					}
				}
				pilhaDeTabelas.topo().adicionarSimbolo(identificador, tipoCompleto, "variavel");
			}
		}

		return null;

	}

	@Override
	public Void visitIdentificador(IdentificadorContext ctx) {
		String identificador = ctx.getText().contains("[") ? ctx.identificador1.getText() : ctx.getText();
		if (identificador.contains(".")) {
			String tipoIdentificador = verificaTipo(ctx);
			if (tipoIdentificador == "null" || tipoIdentificador == null) {
				saida.println("Linha " + ctx.identificador1.getLine() + ": identificador " + identificador
						+ " nao declarado");
			}
		} else {
			if (!pilhaDeTabelas.existeSimbolo(identificador)) {
				saida.println("Linha " + ctx.identificador1.getLine() + ": identificador " + identificador
						+ " nao declarado");
			}
		}
		return null;
	}

	@Override
	public String visitRegistro(RegistroContext ctx) {
		String id_reg;

		if (ctx.getParent().getParent() instanceof VariavelContext) {
			for (int i = 0; i < ((VariavelContext) ctx.getParent().getParent()).identificador().size(); i++) {

				id_reg = ((VariavelContext) ctx.getParent().getParent()).identificador().get(i).getText();
				pilhaDeTabelas.empilhar(new TabelaDeSimbolos("registro[" + id_reg + "]"));
				for (VariavelContext var : ctx.variavel()) {
					visitVariavel(var);
				}
				pilhaDeTabelas.desempilhar();
				if (tabelaDeRegistros.get(id_reg) != null) {
					for (EntradaTabelaDeSimbolos atrib : tabelaDeRegistros.get(id_reg)) {
						String id_atrib = id_reg + "." + atrib.getSimbolo();
						pilhaDeTabelas.topo().adicionarSimbolo(id_atrib, atrib.getTipoDeDado(), atrib.getTipoDoToken());
					}
				}
			}
		} else if (ctx.getParent().getParent() instanceof DeclaracaoLocalTipoContext) {
			id_reg = ((DeclaracaoLocalTipoContext) ctx.getParent().getParent()).IDENT().getText();
			pilhaDeTabelas.empilhar(new TabelaDeSimbolos("registro[" + id_reg + "]"));
			for (VariavelContext var : ctx.variavel()) {
				visitVariavel(var);
			}
			pilhaDeTabelas.desempilhar();
		}

		return "registro";
	}

	@Override
	public Object visitCmdAtribuicao(CmdAtribuicaoContext ctx) {
		String tipoDoIdentificador = verificaTipo(ctx.identificador());
		String tipoDaExpressao = verificaTipo(ctx.expressao());

		boolean incompativel = false;

		if (tipoDoIdentificador != null) {
			if (tipoDoIdentificador.startsWith("^")) {
				if (tipoDaExpressao.startsWith("&")
						&& !tipoDaExpressao.substring(1).equals(tipoDaExpressao.substring(1))) {
					incompativel = true;
				} else if (ctx.getStart().getText().equals("^")
						&& !isTiposCompativeis(tipoDaExpressao, tipoDoIdentificador.substring(1))) {
					incompativel = true;
				}

			} else {
				if (tipoDoIdentificador.startsWith("registro")) {
					String identificador = ctx.identificador().getText();
					if (identificador.contains(".")) {
						String registro = identificador.substring(0, identificador.indexOf("."));
						String atributo = identificador.substring(identificador.indexOf(".") + 1,
								identificador.length());
						for (EntradaTabelaDeSimbolos item : tabelaDeRegistros.get(registro)) {
							if (atributo.equals(item.getSimbolo())) {
								tipoDoIdentificador = item.getTipoDeDado();
								break;
							}
						}
					}
				}

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

		super.visitChildren(ctx);

		pilhaDeTabelas.desempilhar();

		return null;
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
			List<EntradaTabelaDeSimbolos> argumentos = new ArrayList<EntradaTabelaDeSimbolos>();
			tabelaDeParametros.put(nomeFuncao, argumentos);
			for (ParametroContext parametro : ctx.parametros().parametro()) {
				visitParametro(parametro, argumentos);
			}
		}

		super.visitChildren(ctx);
		pilhaDeTabelas.desempilhar();
		return null;
	}

	public Object visitParametro(ParametroContext ctx, List<EntradaTabelaDeSimbolos> listaDeParametros) {
		String tipo = ctx.tipo_estendido().getText();
		for (IdentificadorContext identificador : ctx.identificador()) {
			listaDeParametros.add(new EntradaTabelaDeSimbolos(identificador.getText(), tipo));
		}
		visitParametro(ctx);
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
			saida.println(
					"Linha " + ctx.start.getLine() + ": identificador " + novoTipo + " ja declarado anteriormente");
		} else {
			if (ctx.tipo().registro() != null) {
				pilhaDeTabelas.topo().adicionarSimbolo(novoTipo, "registro", "registro");
				tabelaDeRegistros.put(novoTipo, new ArrayList<>());
			} else {
				pilhaDeTabelas.topo().adicionarSimbolo(novoTipo, ctx.tipo().getText(), "tipo");
			}
			tabelaDeTipos.add(novoTipo);
			if (!novoTipo.startsWith("^")) {
				tabelaDeTipos.add("^" + ctx.IDENT().getText());
			}
		}

		return super.visitChildren(ctx);
	}

	@Override
	public Object visitDeclaracaoLocalConstante(DeclaracaoLocalConstanteContext ctx) {
		String constante = ctx.IDENT().getText();
		String tipo = ctx.tipo_basico().getText();
		if (pilhaDeTabelas.existeSimbolo(constante)) {
			// TODO exibir erro adequado
		} else {
			pilhaDeTabelas.topo().adicionarSimbolo(constante, tipo, "constante");
		}

		return super.visitChildren(ctx);
	}

	@Override
	public Object visitCmdRetorne(CmdRetorneContext ctx) {
		String escopo = pilhaDeTabelas.topo().getEscopo();
		if (pilhaDeTabelas.getTipoDoToken(escopo) == null || !pilhaDeTabelas.getTipoDoToken(escopo).equals("funcao")) {
			saida.println("Linha " + ctx.getStart().getLine() + ": comando retorne nao permitido nesse escopo");
		}
		return null;
	}

	@Override
	public Void visitParcela_unaria(Parcela_unariaContext ctx) {
		if (ctx.IDENT() != null) {
			String nomeFuncao = ctx.IDENT().getText();
			List<EntradaTabelaDeSimbolos> parametros = tabelaDeParametros.get(nomeFuncao);

			if (ctx.expressao().size() < parametros.size()) {
				saida.println("Linha " + ctx.getStart().getLine() + ": incompatibilidade de parametros na chamada de "
						+ nomeFuncao);
			} else {
				for (int i = 0; i < ctx.expressao().size(); i++) {
					if (!verificaTipo(ctx.expressao(i)).equals(parametros.get(i).getTipoDeDado())) {
						saida.println("Linha " + ctx.getStart().getLine()
								+ ": incompatibilidade de parametros na chamada de " + nomeFuncao);
					}
				}
			}

		}

		visitChildren(ctx);

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

	private String verificaTipo(IdentificadorContext ctx) {
		String identificador = ctx.getText().contains("[") ? ctx.getText().substring(0, ctx.getText().indexOf("["))
				: ctx.getText();
		String tipoIdentificador = pilhaDeTabelas.getTipoDeDado(
				identificador.contains(".") ? identificador.substring(0, identificador.indexOf(".")) : identificador);
		if (ctx.getStart().getText().equals("^")) {
			return "^" + tipoIdentificador;
		} else if (identificador.contains(".")) {
			if (tabelaDeRegistros.containsKey(tipoIdentificador)) {
				String item = identificador.substring(identificador.indexOf(".") + 1, identificador.length());
				List<EntradaTabelaDeSimbolos> atributos = tabelaDeRegistros.get(tipoIdentificador);
				for (EntradaTabelaDeSimbolos atributo : atributos) {
					if (atributo.getSimbolo().equals(item)) {
						return atributo.getTipoDeDado();
					}
				}
				return "null";
			} else {
				// TODO printar erro
			}

			// TODO ir na tabela de registro, buscar o registro e, então buscar o tipo de
			// dado do atributo
		}
		return tipoIdentificador;
	}

	private String verificaTipo(Parcela_unariaContext ctx) {
		if (ctx.identificador() != null) {
			return verificaTipo(ctx.identificador());

		} else if (ctx.IDENT() != null) {
			return pilhaDeTabelas.getTipoDeDado(ctx.IDENT().getText());
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
			return "&" + verificaTipo(ctx.identificador());
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
