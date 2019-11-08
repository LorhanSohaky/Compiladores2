package analisadorMarkdown;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import antlr.marktexLexer;
import antlr.marktexParser;
import antlr.marktexParser.DocumentContext;

public class App {
	private static String inputFileName;
	private static String outputFileName;

	public static void main(String[] args) throws IOException {
		inputFileName = args[0];
		outputFileName = args[1];

		boolean geracaoDecodigo = false;

		SaidaParser out = SaidaParser.getInstance();

		File initialFile = new File(inputFileName);
		InputStream casoDeTesteEntrada = new FileInputStream(initialFile);
		CharStream cs;
		cs = CharStreams.fromStream(casoDeTesteEntrada);
		marktexLexer lexer = new marktexLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		try {
			/* Análise gramatical */
			marktexParser parser = new marktexParser(tokens);
			parser.addErrorListener(new T1ErrorListener(out));
			DocumentContext arvore = parser.document();

			/* Análise semântica */
			if (!out.isModificado()) {
				AnalisadorSemantico analisador = new AnalisadorSemantico();
				analisador.visitDocument(arvore);

			}

			/* Geração de código */
			if (!out.isModificado()) {
				geracaoDecodigo = true;
				GeradorDeCodigo gerador = new GeradorDeCodigo();
				gerador.visitDocument(arvore);
			}

		} catch (ParseCancellationException pce) {
			if (pce.getMessage() != null) {
				out.println(pce.getMessage());
			}
		}

		if (!geracaoDecodigo) {
			out.println("Fim da compilacao");
		}

		FileWriter fw;

		fw = new FileWriter(outputFileName);
		fw.write(out.toString());
		fw.close();

	}
}
