package analisadorMarkdown;

import java.util.HashMap;
import java.util.List;

import antlr.marktexBaseVisitor;

public class AnalisadorSemantico extends marktexBaseVisitor<Object> {

	SaidaParser saida = SaidaParser.getInstance();
	PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
	HashMap<String, List<EntradaTabelaDeSimbolos>> tabelaDeReferencias= new HashMap<String, List<EntradaTabelaDeSimbolos>>();

}
