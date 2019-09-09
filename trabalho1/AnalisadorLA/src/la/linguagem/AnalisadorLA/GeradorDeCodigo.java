package la.linguagem.AnalisadorLA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import la.linguagem.ANTLR.laBaseVisitor;
import la.linguagem.ANTLR.laParser.CmdAtribuicaoContext;
import la.linguagem.ANTLR.laParser.CmdCasoContext;
import la.linguagem.ANTLR.laParser.CmdContext;
import la.linguagem.ANTLR.laParser.CmdEscrevaContext;
import la.linguagem.ANTLR.laParser.CmdLeiaContext;
import la.linguagem.ANTLR.laParser.CmdSeContext;
import la.linguagem.ANTLR.laParser.CorpoContext;
import la.linguagem.ANTLR.laParser.DeclaracaoLocalConstanteContext;
import la.linguagem.ANTLR.laParser.DeclaracaoLocalTipoContext;
import la.linguagem.ANTLR.laParser.ExpressaoContext;
import la.linguagem.ANTLR.laParser.Expressao_aritmeticaContext;
import la.linguagem.ANTLR.laParser.Expressao_relacionalContext;
import la.linguagem.ANTLR.laParser.FatorContext;
import la.linguagem.ANTLR.laParser.Fator_logicoContext;
import la.linguagem.ANTLR.laParser.IdentificadorContext;
import la.linguagem.ANTLR.laParser.Item_selecaoContext;
import la.linguagem.ANTLR.laParser.Numero_intervaloContext;
import la.linguagem.ANTLR.laParser.ParcelaContext;
import la.linguagem.ANTLR.laParser.Parcela_logicaContext;
import la.linguagem.ANTLR.laParser.Parcela_nao_unariaContext;
import la.linguagem.ANTLR.laParser.Parcela_unariaContext;
import la.linguagem.ANTLR.laParser.ProgramaContext;
import la.linguagem.ANTLR.laParser.TermoContext;
import la.linguagem.ANTLR.laParser.Termo_logicoContext;
import la.linguagem.ANTLR.laParser.Tipo_estendidoContext;
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

		saida.print("#define ");
		saida.println(ctx.IDENT().getText() + " " + ctx.valor_constante().getText());

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

			if (tipoDeDado.equals("literal")) {
				simbolo += "[1024]";
			}

			indentacao();
			saida.println(tipoC + " " + simbolo + ";");

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
			String tipoDeDado = escopos.getTipoDeDado(id.getText());
			String simbolo = "";
			formato += formatoLA2C(tipoDeDado);
			if (!tipoDeDado.equals("literal")) {
				simbolo += "&";
			}
			simbolo += id.getText();
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

			String texto = expressao.getText();

			if (texto.contains("\"")) {
				formato += texto.substring(texto.indexOf("\"") + 1, texto.lastIndexOf("\""));
			} else {
				String tipo = verificaTipo(expressao);
				formato += formatoLA2C(tipo);
				argumentos += texto + ",";
			}
		}

		saida.print(formato);
		saida.print("\"");

		if (argumentos.length() > 0) {
			argumentos = argumentos.substring(0, argumentos.length() - 1);// Removendo a última vírgula
			saida.print(",");
		}

		saida.print(argumentos);
		saida.println(");");

		return null;
	}

	@Override
	public String visitCmdAtribuicao(CmdAtribuicaoContext ctx) {
		/* cmdAtribuicao: '^' ? identificador '<-' expressao */

		indentacao();
		saida.print(ctx.identificador().getText());
		saida.print(" = ");
		saida.print(ctx.expressao().getText());
		saida.println(";");
		return null;
	}

	@Override
	public String visitCmdSe(CmdSeContext ctx) {
		/* cmdSe: 'se' expressao 'entao' cmd* ('senao' cmd*)? 'fim_se' */

		indentacao();
		saida.print("if ( ");
		saida.print(visitExpressao(ctx.expressao()));
		saida.println(" ) {");

		escopos.empilhar(new TabelaDeSimbolos("se"));
		for (CmdContext comando : ctx.cmd_se) {
			visitCmd(comando);
		}
		escopos.desempilhar();

		if (ctx.operador_se != null) {
			indentacao();
			saida.println("} else {");

			escopos.empilhar(new TabelaDeSimbolos("senao"));
			for (CmdContext comando : ctx.cmd_senao) {
				visitCmd(comando);
			}
			escopos.desempilhar();

			indentacao();
			saida.println("}");
		} else {
			indentacao();
			saida.println("}");
		}

		return null;
	}

	@Override
	public String visitCmdCaso(CmdCasoContext ctx) {
		/*
		 * cmdCaso: 'caso' expressao_aritmetica 'seja' selecao ('senao' cmd*)?
		 * 'fim_caso'
		 */

		indentacao();
		saida.print("switch( ");
		saida.print(visitExpressao_aritmetica(ctx.expressao_aritmetica()));
		saida.println(" ) {");

		escopos.empilhar(new TabelaDeSimbolos("caso"));

		for (Item_selecaoContext item : ctx.selecao().item_selecao()) {
			for (Numero_intervaloContext numero : item.constantes().numero_intervalo()) {
				int numero_inicial = Integer.parseInt(numero.NUM_INT(0).getText());

				if (numero.operador_unario(0) != null) {
					numero_inicial = -numero_inicial;
				}

				int numero_final = numero_inicial;
				if (numero.NUM_INT().size() > 1) {
					numero_final = Integer.parseInt(numero.NUM_INT(1).getText());

					if (numero.operador_unario(1) != null) {
						numero_final = -numero_final;
					}
				}

				for (; numero_inicial <= numero_final; numero_inicial++) {
					indentacao();
					saida.println("case " + numero_inicial + ":");
				}

				escopos.empilhar(new TabelaDeSimbolos("selecao"));
				for (CmdContext comando : item.cmd()) {
					visitCmd(comando);
				}
				indentacao();
				saida.println("break;");
				escopos.desempilhar();
			}
		}

		indentacao();
		saida.println("default:");
		escopos.empilhar(new TabelaDeSimbolos("default"));
		for (CmdContext comando : ctx.cmd()) {
			visitCmd(comando);
		}
		escopos.desempilhar();

		indentacao();
		saida.println("}");

		return null;
	}

	@Override
	public String visitExpressao(ExpressaoContext ctx) {
		/* expressao: termo_logico (operador_logico_nivel_1 termo_logico)* */

		String retorno = "";
		for (Termo_logicoContext termo : ctx.termo_logico()) {
			retorno += visitTermo_logico(termo);
			retorno += " || ";
		}
		retorno = retorno.substring(0, retorno.lastIndexOf(" || "));
		return retorno;
	}

	@Override
	public String visitTermo_logico(Termo_logicoContext ctx) {
		/* termo_logico: fator_logico (operador_logico_nivel_2 fator_logico)* */
		String retorno = "";
		for (Fator_logicoContext fatorLogico : ctx.fator_logico()) {
			retorno += visitFator_logico(fatorLogico);
			retorno += " && ";
		}
		retorno = retorno.substring(0, retorno.lastIndexOf(" && "));
		return retorno;
	}

	@Override
	public String visitFator_logico(Fator_logicoContext ctx) {
		/* fator_logico: 'nao' ? parcela_logica */

		String retorno = "";
		if (ctx.getText().startsWith("nao")) {
			retorno += "!";
		}

		retorno += visitParcela_logica(ctx.parcela_logica());
		return retorno;
	}

	@Override
	public String visitParcela_logica(Parcela_logicaContext ctx) {
		/* parcela_logica: ('verdadeiro' | 'falso')| expressao_relacional */

		if (ctx.getText().equals("verdadeiro")) {
			return "true";
		} else if (ctx.getText().equals("falso")) {
			return "false";
		} else {
			return visitExpressao_relacional(ctx.expressao_relacional());
		}
	}

	@Override
	public String visitExpressao_relacional(Expressao_relacionalContext ctx) {
		/*
		 * expressao_relacional: expressao_aritmetica (operador_relacional
		 * expressao_aritmetica)?
		 */

		String retorno = "";
		retorno += ctx.expressao_aritmetica(0).getText();
		if (ctx.operador_relacional() != null) {
			retorno += operadorRelacionalLA2C(ctx.operador_relacional().getText());
			retorno += visitExpressao_aritmetica(ctx.expressao_aritmetica(1));
		}
		return retorno;
	}

	@Override
	public String visitExpressao_aritmetica(Expressao_aritmeticaContext ctx) {
		/* expressao_aritmetica: termo (operador_nivel_1 termo)* */

		String retorno = visitTermo(ctx.termo(0));
		for (int i = 1; i < ctx.termo().size(); i++) {
			retorno += ctx.operador_nivel_1(i - 1);
			retorno += visitTermo(ctx.termo(i));
		}
		return retorno;
	}

	@Override
	public String visitTermo(TermoContext ctx) {
		/* termo: fator (operador_nivel_2 fator)* */

		String retorno = visitFator(ctx.fator(0));
		for (int i = 1; i < ctx.fator().size(); i++) {
			retorno += ctx.operador_nivel_2(i - 1);
			retorno += visitFator(ctx.fator(i));
		}
		return retorno;
	}

	@Override
	public String visitFator(FatorContext ctx) {
		/* fator: parcela (operador_nivel_3 parcela)* */

		String retorno = visitParcela(ctx.parcela(0));
		for (int i = 1; i < ctx.parcela().size(); i++) {
			retorno += ctx.operador_nivel_3(i - 1);
			retorno += visitParcela(ctx.parcela(i));
		}
		return retorno;
	}

	@Override
	public String visitParcela(ParcelaContext ctx) {
		/* parcela: operador_unario ? parcela_unaria | parcela_nao_unaria */

		String retorno = "";

		if (ctx.parcela_unaria() != null) {
			if (ctx.operador_unario() != null) {
				retorno += ctx.operador_unario().getText();
			}
			retorno += visitParcela_unaria(ctx.parcela_unaria());
		} else {
			retorno += visitParcela_nao_unaria(ctx.parcela_nao_unaria());
		}

		return retorno;
	}

	@Override
	public String visitParcela_unaria(Parcela_unariaContext ctx) {
		/*
		 * parcela_unaria: '^' ? identificador | IDENT '(' expressao (',' expressao)*
		 * ')' | NUM_INT | NUM_REAL | '(' expressao ')'
		 */

		String retorno = "";

		if (ctx.getText().startsWith("^")) {
			retorno += "*";
		}

		if (ctx.identificador() != null) {
			retorno += visitIdentificador(ctx.identificador());
		} else if (ctx.IDENT() != null) {
			retorno += ctx.IDENT().getText();
			for (ExpressaoContext expressao : ctx.expressao()) {
				retorno += visitExpressao(expressao);
				retorno += ",";
			}
			if (ctx.expressao().size() > 0) {
				retorno = retorno.substring(0, retorno.lastIndexOf(","));
			}
		} else if (ctx.getText().startsWith("(")) {
			retorno += "(" + visitExpressao(ctx.expressao(0)) + ")";
		} else {
			retorno += ctx.NUM_INT() != null ? ctx.NUM_INT().getText() : ctx.NUM_REAL().getText();
		}

		return retorno;
	}

	@Override
	public String visitParcela_nao_unaria(Parcela_nao_unariaContext ctx) {
		/* parcela_nao_unaria: '&' identificador | CADEIA */
		if (ctx.identificador() != null) {
			return "&" + visitIdentificador(ctx.identificador());
		} else {
			return ctx.CADEIA().getText();
		}
	}

	@Override
	public String visitIdentificador(IdentificadorContext ctx) {
		/* identificador: identificador1=IDENT ('.' IDENT)* dimensao */
		return ctx.getText();
	}

	@Override
	public String visitTipo_estendido(Tipo_estendidoContext ctx) {
		/* tipo_estendido: '^' ? tipo_basico_identificador */
		return ((ctx.getText().startsWith("^")) ? "*" : "")
				+ visitTipo_basico_identificador(ctx.tipo_basico_identificador());
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
			tipo = "char";
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
			tipo = "%lf";
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

	private String operadorRelacionalLA2C(String operador) {
		switch (operador) {
		case "<>":
			operador = "!=";
			break;
		case "=":
			operador = "==";
			break;
		default:
			break;
		}
		return operador;
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
