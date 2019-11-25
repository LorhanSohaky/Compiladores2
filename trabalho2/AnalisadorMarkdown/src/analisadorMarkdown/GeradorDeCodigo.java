package analisadorMarkdown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import antlr.marktexBaseVisitor;
import antlr.marktexParser.ContentContext;
import antlr.marktexParser.DocumentContext;

public class GeradorDeCodigo extends marktexBaseVisitor<String> {
	SaidaParser saida = SaidaParser.getInstance();
	PilhaDeTabelas escopos = new PilhaDeTabelas();
	HashMap<String, List<EntradaTabelaDeSimbolos>> tabelaDeReferencias= new HashMap<String, List<EntradaTabelaDeSimbolos>>();
	ArrayList<String> tabelaDeTipos = new ArrayList<String>();

	@Override
  public String visitDocument(DocumentContext ctx) {
	String tipo = ctx.configs().type().document_type().getText();
	String titulo = ctx.configs().title().STRING().getText().replaceAll("\"", "");
	List<String> autores = ctx.configs().author().stream().map(item -> item.STRING().getText().replaceAll("\"", "")).collect(Collectors.toList());
	String data = ctx.configs().date().DATE().getText();

	saida.println("\\documentclass[");

	if (tipo.equals("article")){
		saida.println("	article,");
	}

	saida.println("	12pt,\n" + 
    		"	openright,\n" + 
    		"	oneside,\n" + 
    		"	a4paper,\n" + 
    		"	english,\n" + 
    		"	french,\n" + 
    		"	spanish,\n" + 
    		"	brazil\n" + 
    		"	]{abntex2}\n" + 
    		"\n" + 
    		"\\usepackage{lmodern}\n" + 
    		"\\usepackage[T1]{fontenc}\n" + 
    		"\\usepackage[utf8]{inputenc}\n" + 
    		"\\usepackage{indentfirst}\n" + 
    		"\\usepackage{color}\n" + 
    		"\\usepackage{graphicx}\n" + 
    		"\\usepackage{microtype}\n" + 
    		"\n" + 
    		"\\usepackage[brazilian,hyperpageref]{backref}\n" + 
    		"\\usepackage[alf]{abntex2cite}\n" + 
    		"\n" + 
    		"% --- \n" + 
    		"% CONFIGURAÇÕES DE PACOTES\n" + 
    		"% --- \n" + 
    		"\n" + 
    		"\\renewcommand{\\backrefpagesname}{Citado na(s) página(s):~}\n" + 
    		"\\renewcommand{\\backref}{}\n" + 
    		"\\renewcommand*{\\backrefalt}[4]{\n" + 
    		"	\\ifcase #1 %\n" + 
    		"		Nenhuma citação no texto.%\n" + 
    		"	\\or\n" + 
    		"		Citado na página #2.%\n" + 
    		"	\\else\n" + 
    		"		Citado #1 vezes nas páginas #2.%\n" + 
    		"	\\fi}%\n");
	
	saida.println("\\titulo{" + titulo + "}");

	saida.print("\\autor{");
	while(autores.size()>0){
		String autor = autores.remove(0);
		saida.print(autor);
		if(autores.size()>0){
			saida.print((" \\and "));
		}
		
	}
	saida.print("}\n");
	saida.println("\\data{"+ data +"}");
	saida.println("\n" + 
    		"\\definecolor{blue}{RGB}{41,5,195}\n" + 
    		"\n" + 
    		"\\makeatletter\n" + 
    		"\\hypersetup{\n" + 
    		"		pdftitle={\\@title}, \n" + 
    		"		pdfauthor={\\@author},\n" + 
    		"    	pdfsubject={\\imprimirpreambulo},\n" + 
    		"	    pdfcreator={LaTeX with abnTeX2},\n" + 
    		"		pdfkeywords={abnt}{latex}{abntex}{abntex2}{trabalho acadêmico}, \n" + 
    		"		colorlinks=true,\n" + 
    		"    	linkcolor=blue,\n" + 
    		"    	citecolor=blue,\n" + 
    		"    	filecolor=magenta,\n" + 
    		"		urlcolor=blue,\n" + 
    		"		bookmarksdepth=4\n" + 
    		"}\n" + 
    		"\\makeatother\n" + 
    		"% --- \n" + 
    		"\n" + 
    		"\\makeatletter\n" + 
    		"\\setlength{\\@fptop}{5pt}\n" + 
    		"\\makeatother\n" + 
    		"% ---\n" + 
    		"\n" + 
    		"\\newcommand{\\quadroname}{Quadro}\n" + 
    		"\\newcommand{\\listofquadrosname}{Lista de quadros}\n" + 
    		"\n" + 
    		"\\newfloat[chapter]{quadro}{loq}{\\quadroname}\n" + 
    		"\\newlistof{listofquadros}{loq}{\\listofquadrosname}\n" + 
    		"\\newlistentry{quadro}{loq}{0}\n" + 
    		"\n" + 
    		"\\setfloatadjustment{quadro}{\\centering}\n" + 
    		"\\counterwithout{quadro}{chapter}\n" + 
    		"\\renewcommand{\\cftquadroname}{\\quadroname\\space} \n" + 
    		"\\renewcommand*{\\cftquadroaftersnum}{\\hfill--\\hfill}\n" + 
    		"\n" + 
    		"\\setfloatlocations{quadro}{hbtp}\n" + 
    		"% ---\n" + 
    		"\n" + 
    		"\\setlength{\\parindent}{1.3cm}\n" + 
    		"\n" + 
    		"\\setlength{\\parskip}{0.2cm}\n" + 
    		"\n" + 
    		"\\makeindex\n" + 
    		"\n" + 
    		"\\begin{document}\n" + 
    		"\\selectlanguage{brazil}\n" + 
			"\\frenchspacing\n");

	visitContent(ctx.content());

    saida.println("\\end{document}");

    return null;
  }

  @Override
  public String visitContent(ContentContext ctx) {
	  String textoEntrada = ctx.BODY().getText().substring(6,ctx.BODY().getText().length());
	  String textoEditado = "";
	  
	  textoEditado = replaceItalic(textoEntrada);
	  
	  saida.println(textoEditado);
	  return null;
  }
  
  private String replaceItalic(String texto) {
	  String regex = "(\\*)(\\s*\\b)([^\\*]*)(\\b\\s*)(\\*)";

	  return texto.replaceAll(regex, "\\\\textit{$3}");
  }

  private String replaceBold(String texto) {
	  String regex = "(\\*\\*)(\\s*\\b)([^\\*]*)(\\b\\s*)(\\*\\*)";

	  return texto.replaceAll(regex, "\\\\textbf{$3}");
  }
}


