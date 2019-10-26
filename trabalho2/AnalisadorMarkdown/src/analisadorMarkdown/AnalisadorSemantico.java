package analisadorMarkdown;

import java.util.HashMap;
import java.util.List;

import antlr.laBaseVisitor;

public class AnalisadorSemantico extends laBaseVisitor<Object> {

	SaidaParser saida = SaidaParser.getInstance();
	PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
	HashMap<String, List<EntradaTabelaDeSimbolos>> tabelaDeReferencias= new HashMap<String, List<EntradaTabelaDeSimbolos>>();

}
