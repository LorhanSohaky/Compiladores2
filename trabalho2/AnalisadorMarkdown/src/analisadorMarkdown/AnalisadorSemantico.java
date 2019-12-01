package analisadorMarkdown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import antlr.marktexBaseVisitor;
import antlr.marktexParser.ContentContext;

public class AnalisadorSemantico extends marktexBaseVisitor<String> {

	SaidaParser saida = SaidaParser.getInstance();
	HashMap<String, List<Integer>> tabelaDeEscopos= new HashMap<String, List<Integer>>();
	HashMap<String, List<EntradaTabelaDeSimbolos>> tabelaDeReferencias= new HashMap<String, List<EntradaTabelaDeSimbolos>>();


	@Override
	public String visitContent(ContentContext ctx) {
		String texto = ctx.BODY().getText().substring(6,ctx.BODY().getText().length());
		String linhas[] = texto.split("\\r?\\n");

		verifyHeading1(linhas);
		verifyHeading2(linhas);

		System.out.println(tabelaDeEscopos);

		return null;
	}

	void addOrUpdateItem(String item, int line){
		if(!tabelaDeEscopos.containsKey(item)){
			List<Integer> linhas = new ArrayList<Integer>();
			tabelaDeEscopos.put(item, linhas);
		}
		List<Integer> lines = tabelaDeEscopos.get(item);
		lines.add(line);
		tabelaDeEscopos.put(item, lines);
	}

	private String[] verifyHeading1(String linhas[]){
		String regex = "^#{1}\\s(.*)";
		for(int i = 0; i < linhas.length; i++){
			if(linhas[i].matches(regex)){
				addOrUpdateItem("H1",i);
			}
		}
		return linhas;
	}

	private String[] verifyHeading2(String linhas[]){
		String regex = "^#{2}\\s(.*)";
		for(int i = 0; i < linhas.length; i++){
			if(linhas[i].matches(regex)){
				if(!tabelaDeEscopos.containsKey("H1")){
					saida.println("Erro na linha "+(i+1)+": "+linhas[i]+" deveria estar dentro de um Heading1");
					return null;
				} else{
					final int line = i;
					Predicate<Integer> byLine = item -> item < line;
					Stream<Integer> items = tabelaDeEscopos.get("H1").stream().filter(byLine);
					List<Integer> list = items.collect(Collectors.toList());
					if(list.size()==0){
						saida.println("Erro na linha "+(i+1)+": "+linhas[i]+" deveria estar dentro de um Heading1");
						return null;
					}
				}
				addOrUpdateItem("H2",i);
			}
		}
		return linhas;
	}
}
