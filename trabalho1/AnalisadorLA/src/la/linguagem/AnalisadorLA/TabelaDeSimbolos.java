package la.linguagem.AnalisadorLA;

import java.util.ArrayList;
import java.util.List;

public class TabelaDeSimbolos {
	private String escopo;
	private List<EntradaTabelaDeSimbolos> simbolos;

	public TabelaDeSimbolos(String escopo) {
		simbolos = new ArrayList<EntradaTabelaDeSimbolos>();
		this.escopo = escopo;
	}

	public void adicionarSimbolo(String nome, String tipo, String simbolo) {
		simbolos.add(new EntradaTabelaDeSimbolos(nome, tipo, simbolo));
	}

	public void adicionarSimbolos(List<String> nomes, String tipo, String simbolo) {
		for (String s : nomes) {
			simbolos.add(new EntradaTabelaDeSimbolos(s, tipo, simbolo));
		}
	}

	public boolean existeSimbolo(String nome) {
		for (EntradaTabelaDeSimbolos etds : simbolos) {
			if (etds.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}

	public String getTipo(String nome) {
		for (EntradaTabelaDeSimbolos etds : simbolos) {
			if (etds.getNome().equals(nome)) {
				return etds.getTipo();
			}
		}
		return "false";
	}

	public String getEscopo() {
		return this.escopo;
	}

	public String getSimbolo(String nome) {
		for (EntradaTabelaDeSimbolos etds : simbolos) {
			if (etds.getNome().equals(nome)) {
				return etds.getSimbolo();
			}
		}
		return "false";
	}

	@Override
	public String toString() {
		String ret = "Escopo: " + escopo;
		for (EntradaTabelaDeSimbolos etds : simbolos) {
			ret += "\n   " + etds;
		}
		return ret;
	}
}