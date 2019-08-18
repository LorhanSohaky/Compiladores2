package la.linguagem.AnalisadorLA;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import la.linguagem.ANTLR.laLexer;
import la.linguagem.ANTLR.laParser;
import la.linguagem.ANTLR.laParser.ProgramaContext;

public class App {
	private static String inputFileName;
	private static String outputFileName;

	public static void main(String[] args) throws IOException {
		inputFileName = args[0];
		outputFileName = args[1];

		if (inputFileName.contains("semantico")) {
			AnalisarSemantica();
		} else if (inputFileName.contains("sem_erros")) {
			GerarCodigo();
		} else if (inputFileName.contains("sintatico")) {
			AnalisarGramatica();
		}

	}

	private static void AnalisarGramatica() throws IOException {
		SaidaParser out = SaidaParser.getInstance();

		File initialFile = new File(inputFileName);
		InputStream casoDeTesteEntrada = new FileInputStream(initialFile);
		CharStream cs;
		cs = CharStreams.fromStream(casoDeTesteEntrada);
		laLexer lexer = new laLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		try {
			laParser parser = new laParser(tokens);

			parser.addErrorListener(new T1ErrorListener(out));
			parser.programa();

		} catch (ParseCancellationException pce) {
			if (pce.getMessage() != null) {
				out.println(pce.getMessage());
			}
		}

		out.println("Fim da compilacao");

		FileWriter fw;

		fw = new FileWriter(outputFileName);
		fw.write(out.toString());
		fw.close();
	}

	private static void AnalisarSemantica() throws IOException {
		SaidaParser out = SaidaParser.getInstance();

		File initialFile = new File(inputFileName);
		InputStream casoDeTesteEntrada = new FileInputStream(initialFile);
		CharStream cs;
		cs = CharStreams.fromStream(casoDeTesteEntrada);
		laLexer lexer = new laLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		laParser parser = new laParser(tokens);
		ProgramaContext arvore = parser.programa();

		AnalisadorSemantico analisador = new AnalisadorSemantico();
		analisador.visitPrograma(arvore);

		out.println("Fim da compilacao");

		FileWriter fw;

		fw = new FileWriter(outputFileName);
		fw.write(out.toString());
		fw.close();
	}

	private static void GerarCodigo() {
	}
}
