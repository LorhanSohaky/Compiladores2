package analisadorMarkdown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import antlr.marktexBaseVisitor;
import antlr.marktexParser.DocumentContext;

public class GeradorDeCodigo extends marktexBaseVisitor<String> {
	SaidaParser saida = SaidaParser.getInstance();
	PilhaDeTabelas escopos = new PilhaDeTabelas();
	HashMap<String, List<EntradaTabelaDeSimbolos>> tabelaDeReferencias= new HashMap<String, List<EntradaTabelaDeSimbolos>>();
	ArrayList<String> tabelaDeTipos = new ArrayList<String>();

	@Override
  public String visitDocument(DocumentContext ctx) {
    saida.println("#include <stdio.h>");
    saida.println("#include <stdlib.h>");
    saida.println("#include <string.h>");
    saida.println("#include <stdbool.h>");

    super.visitChildren(ctx);

    return null;
  }

}
