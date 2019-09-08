/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package la.linguagem.AnalisadorLA;

public class SaidaParser {

	private StringBuffer conteudo;
	boolean modificado;
	private static SaidaParser instance = null;

	public SaidaParser() {
		conteudo = new StringBuffer();
		modificado = false;
	}

	public static SaidaParser getInstance() {
		if (SaidaParser.instance == null) {
			instance = new SaidaParser();
		}
		return instance;

	}

	public void println(String texto) {
		if (!modificado)
			modificado = true;
		conteudo.append(texto);
		conteudo.append("\n");
	}

	public void print(String texto) {
		if (!modificado)
			modificado = true;
		conteudo.append(texto);
	}

	public boolean isModificado() {
		return modificado;
	}

	@Override
	public String toString() {
		return conteudo.toString();
	}
}