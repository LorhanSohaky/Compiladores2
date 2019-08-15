package la.linguagem.AnalisadorLA;

import la.linguagem.ANTLR.laBaseVisitor;

public class AnalisadorSemantico extends laBaseVisitor {

	SaidaParser saida = SaidaParser.getInstance();

	PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();

}
