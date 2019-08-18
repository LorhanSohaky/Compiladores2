package la.linguagem.AnalisadorLA;

import la.linguagem.ANTLR.laBaseVisitor;
import la.linguagem.ANTLR.laParser;
import la.linguagem.ANTLR.laParser.IdentificadorContext;
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

		String tipo = ctx.tipo().getText();
		tipo = tipo.startsWith("^") ? tipo.substring(1) : tipo;

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
				pilhaDeTabelas.topo().adicionarSimbolo(variavel.getText(), tipo);
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

}
