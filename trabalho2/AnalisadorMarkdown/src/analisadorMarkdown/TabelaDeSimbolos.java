package analisadorMarkdown;

import java.util.ArrayList;
import java.util.List;

public class TabelaDeSimbolos {
	private String escopo;
	private List<EntradaTabelaDeSimbolos> simbolos;

	public TabelaDeSimbolos(String escopo) {
		simbolos = new ArrayList<EntradaTabelaDeSimbolos>();
		this.escopo = escopo;
	}

	public void adicionarSimbolo(String simbolo, String tipoDeDado) {
		simbolos.add(new EntradaTabelaDeSimbolos(simbolo, tipoDeDado));
	}

	public void adicionarSimbolos(List<String> simbolos, String tipoDeDado) {
		for (String simbolo : simbolos) {
			this.simbolos.add(new EntradaTabelaDeSimbolos(simbolo, tipoDeDado));
		}
	}

	public boolean existeSimbolo(String simbolo) {
		for (EntradaTabelaDeSimbolos etds : simbolos) {
			if (etds.getSimbolo().equals(simbolo)) {
				return true;
			}
		}
		return false;
	}

	public String getTipoDeDado(String simbolo) {
		for (EntradaTabelaDeSimbolos etds : simbolos) {
			if (etds.getSimbolo().equals(simbolo)) {
				return etds.getTipoDeDado();
			}
		}
		return "false";
	}

	public String getEscopo() {
		return this.escopo;
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
