package la.linguagem.AnalisadorLA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import la.linguagem.ANTLR.laBaseVisitor;
import la.linguagem.ANTLR.laParser.CmdEscrevaContext;
import la.linguagem.ANTLR.laParser.CmdLeiaContext;
import la.linguagem.ANTLR.laParser.CorpoContext;
import la.linguagem.ANTLR.laParser.DeclaracaoLocalConstanteContext;
import la.linguagem.ANTLR.laParser.DeclaracaoLocalTipoContext;
import la.linguagem.ANTLR.laParser.ExpressaoContext;
import la.linguagem.ANTLR.laParser.Expressao_aritmeticaContext;
import la.linguagem.ANTLR.laParser.Expressao_relacionalContext;
import la.linguagem.ANTLR.laParser.FatorContext;
import la.linguagem.ANTLR.laParser.Fator_logicoContext;
import la.linguagem.ANTLR.laParser.IdentificadorContext;
import la.linguagem.ANTLR.laParser.ParcelaContext;
import la.linguagem.ANTLR.laParser.Parcela_logicaContext;
import la.linguagem.ANTLR.laParser.Parcela_nao_unariaContext;
import la.linguagem.ANTLR.laParser.Parcela_unariaContext;
import la.linguagem.ANTLR.laParser.ProgramaContext;
import la.linguagem.ANTLR.laParser.TermoContext;
import la.linguagem.ANTLR.laParser.Termo_logicoContext;
import la.linguagem.ANTLR.laParser.VariavelContext;

public class GeradorDeCodigo extends laBaseVisitor<String> {
	SaidaParser saida = SaidaParser.getInstance();
	PilhaDeTabelas escopos = new PilhaDeTabelas();
	HashMap<String, List<EntradaTabelaDeSimbolos>> tabelaDeParametros = new HashMap<String, List<EntradaTabelaDeSimbolos>>();
	HashMap<String, List<EntradaTabelaDeSimbolos>> tabelaDeRegistros = new HashMap<String, List<EntradaTabelaDeSimbolos>>();
	ArrayList<String> tabelaDeTipos = new ArrayList<String>();

	@Override
	public String visitPrograma(ProgramaContext ctx) {
		/* programa: declaracoes 'algoritmo' corpo 'fim_algoritmo' */

		escopos.empilhar(new TabelaDeSimbolos("global"));

		saida.println("#include <stdio.h>");
		saida.println("#include <stdlib.h>");
		saida.println("#include <string.h>");
		saida.println("#include <stdbool.h>");

		super.visitChildren(ctx);

		escopos.desempilhar();

		return null;
	}

	@Override
	public String visitDeclaracaoLocalConstante(DeclaracaoLocalConstanteContext ctx) {
		/* declaracao_local: 'constante' IDENT ':' tipo_basico '=' valor_constante */

		return null;
	}

	@Override
	public String visitDeclaracaoLocalTipo(DeclaracaoLocalTipoContext ctx) {
		/* 'tipo' IDENT ':' tipo # declaracao_local_tipo */

		return null;
	}

	@Override
	public String visitVariavel(VariavelContext ctx) {
		/*
		 * variavel: identificadores+=identificador (','
		 * identificadores+=identificador)* ':' tipo
		 */

		String tipoDeDado = ctx.tipo().getText();
		String tipoC = tipoLA2C(tipoDeDado);
		for (IdentificadorContext id : ctx.identificador()) {
			String simbolo = id.getText();
			String tipoDoToken = "variavel";

			if (!id.dimensao().getText().isEmpty()) {
				simbolo = simbolo.substring(0, simbolo.indexOf("["));
				tipoDoToken += " vetor";
			}
			escopos.topo().adicionarSimbolo(simbolo, tipoDeDado, tipoDoToken);

			indentacao();
			saida.println(tipoC + " " + id.getText() + ";");

		}
		return null;
	}

	@Override
	public String visitCorpo(CorpoContext ctx) {
		/* corpo: declaracao_local* cmd* */

		saida.println("int main() {");

		super.visitChildren(ctx);

		indentacao();
		saida.println("return 0;");
		saida.println("}");
		return null;
	}

	@Override
	public String visitCmdLeia(CmdLeiaContext ctx) {
		/*
		 * cmdLeia: 'leia' '(' '^' ? identificadores+=identificador (',' '^' ?
		 * identificadores+=identificador)* ')'
		 */

		indentacao();
		saida.print("scanf(\"");

		String formato = "";
		String argumentos = "";

		for (IdentificadorContext id : ctx.identificadores) {
			formato += formatoLA2C(escopos.getTipoDeDado(id.getText()));
			String simbolo = "&" + id.getText();
			argumentos += simbolo + ",";
		}

		argumentos = argumentos.substring(0, argumentos.length() - 1);// Removendo a última vírgula
		saida.print(formato);
		saida.print("\",");
		saida.print(argumentos);
		saida.println(");");

		return null;
	}

	@Override
	public String visitCmdEscreva(CmdEscrevaContext ctx) {
		/* cmdEscreva: 'escreva' '(' expressao (',' expressao)* ')' */

		indentacao();
		saida.print("printf(\"");

		String formato = "";
		String argumentos = "";

		for (ExpressaoContext expressao : ctx.expressao()) {

			String tipo = verificaTipo(expressao);

			System.out.printf("%s %s %s", expressao.getText(), escopos.getTipoDeDado(expressao.getText()),
					formatoLA2C(escopos.getTipoDeDado(expressao.getText())));
			formato += formatoLA2C(escopos.getTipoDeDado(expressao.getText()));
			argumentos += expressao.getText() + ",";
		}

		argumentos = argumentos.substring(0, argumentos.length() - 1);// Removendo a última vírgula
		saida.print(formato);
		saida.print("\",");
		saida.print(argumentos);
		saida.println(");");

		return null;
	}

	private String tipoLA2C(String tipo) {
		switch (tipo) {
		case "inteiro":
			tipo = "int";
			break;
		case "real":
			tipo = "double";
			break;
		case "literal":
			tipo = "char*";
			break;
		case "logico":
			tipo = "bool";
			break;

		default:
			break;
		}
		return tipo;
	}

	private String formatoLA2C(String tipo) {
		switch (tipo) {
		case "inteiro":
			tipo = "%d";
			break;
		case "real":
			tipo = "%f";
			break;
		case "literal":
			tipo = "%s";
			break;
		case "logico":
			tipo = "%d";
			break;

		default:
			break;
		}
		return tipo;

	}

	private void indentacao() {
		for (int i = 0; i < escopos.size(); i++) {
			saida.print("    ");
		}
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
		String tipoIdentificador = escopos.getTipoDeDado(
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
			return escopos.getTipoDeDado(ctx.IDENT().getText());
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
