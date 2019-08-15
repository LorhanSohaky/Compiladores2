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

public class App {
	public static void main(String[] args) throws IOException {
		SaidaParser out = new SaidaParser();

		File initialFile = new File(args[0]);
		InputStream casoDeTesteEntrada = new FileInputStream(initialFile);
		CharStream cs;
		cs = CharStreams.fromStream(casoDeTesteEntrada);
		laLexer lexer = new laLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		try{
			laParser parser = new laParser(tokens);

			parser.addErrorListener(new T1ErrorListener(out));
			parser.programa();
			if (!out.isModificado()) {
				/*
				* out.println("Tabela de simbolos:");
				* 
				* TabelaDeSimbolos.imprimirTabela(out); System.out.print(out);
				*/
			}
		}catch(ParseCancellationException pce) {
			if(pce.getMessage() != null) {
				out.println(pce.getMessage ());
			}
		}

		out.println("Fim da compilacao");

		FileWriter fw;

		fw = new FileWriter(args[1]);
		fw.write(out.toString());
		fw.close();

	}
}
