package analisadorMarkdown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import antlr.marktexBaseVisitor;
import antlr.marktexParser.ContentContext;
import antlr.marktexParser.DocumentContext;
import antlr.marktexParser.ReferencesContext;

public class AnalisadorSemantico extends marktexBaseVisitor<String> {

	SaidaParser saida = SaidaParser.getInstance();
	HashMap<String, List<Integer>> tabelaDeEscopos= new HashMap<String, List<Integer>>();
	List<String> tabelaDeReferencias= new ArrayList<String>();

	@Override
	public String visitDocument(DocumentContext ctx) {
		visitConfigs(ctx.configs());
		if(ctx.references()!=null){
			visitReferences(ctx.references());
		}
		visitContent(ctx.content());
		return null;
	}


	@Override
	public String visitContent(ContentContext ctx) {
		String texto = ctx.BODY().getText().substring(6,ctx.BODY().getText().length());
		String linhas[] = texto.split("\\r?\\n");

		verifyReferences(linhas);
		verifyHeading1(linhas);
		verifyHeading2(linhas);
		verifyHeading3(linhas);

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
			String msgError = "Erro na linha "+(i+1)+": "+linhas[i]+" deveria estar dentro de um Heading1";
			if(linhas[i].matches(regex)){
				if(!tabelaDeEscopos.containsKey("H1")){
					saida.println(msgError);
				} else{
					final int line = i;
					Predicate<Integer> byLine = item -> item < line;
					Stream<Integer> items = tabelaDeEscopos.get("H1").stream().filter(byLine);
					List<Integer> list = items.collect(Collectors.toList());
					if(list.size()==0){
						saida.println(msgError);

					}
				}
				addOrUpdateItem("H2",i);
			}
		}
		return linhas;
	}

	private String[] verifyHeading3(String linhas[]){
		String regex = "^#{3}\\s(.*)";
		for(int i = 0; i < linhas.length; i++){
			String msgError= "Erro na linha "+(i+1)+": "+linhas[i]+" deveria estar dentro de um Heading2";
			if(linhas[i].matches(regex)){
				if(!tabelaDeEscopos.containsKey("H2")){
					saida.println(msgError);
				} else{
					final int line = i;
					Predicate<Integer> byLine = item -> item < line;
					Stream<Integer> items = tabelaDeEscopos.get("H2").stream().filter(byLine);
					List<Integer> list = items.collect(Collectors.toList());
					if(list.size()==0){
						saida.println(msgError);

					}
				}
				addOrUpdateItem("H3",i);
			}
		}
		return linhas;
	}

	private String[] verifyReferences(String linhas[]){
		String regex = "\\[\\@(.*)\\]";
		final Pattern pattern = Pattern.compile(regex);
		for(int i = 0; i < linhas.length; i++){
			Matcher matcher = pattern.matcher(linhas[i]);
			if(matcher.find()){
				if(!tabelaDeReferencias.contains(matcher.group(1))){
					String msgError= "Erro na linha "+(i+1)+": referência "+matcher.group(1)+" não foi encontrada";
					saida.println(msgError);
				}
			}
		}
		return linhas;
	}

	@Override
	public String visitReferences(ReferencesContext ctx) {
		for( int i =0; i < ctx.referencias.size(); i++){
			String alias = ctx.referencias.get(i).ALIAS().getText();
			if(tabelaDeReferencias.contains(alias)){
				String msgError= "Erro na linha "+(i+1)+": a referência "+ alias  +" já foi declarada anteriormente";
				saida.println(msgError);
			}else{
				tabelaDeReferencias.add(alias);
			}
		}
		return null;
	}
}
