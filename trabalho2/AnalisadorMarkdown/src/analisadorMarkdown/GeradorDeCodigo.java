package analisadorMarkdown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import antlr.laBaseVisitor;
import antlr.laParser.ProgramaContext;

public class GeradorDeCodigo extends laBaseVisitor<String> {
	SaidaParser saida = SaidaParser.getInstance();
	PilhaDeTabelas escopos = new PilhaDeTabelas();
	HashMap<String, List<EntradaTabelaDeSimbolos>> tabelaDeReferencias= new HashMap<String, List<EntradaTabelaDeSimbolos>>();
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

}
