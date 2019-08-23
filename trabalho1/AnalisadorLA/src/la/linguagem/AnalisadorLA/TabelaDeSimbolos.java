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

	public void adicionarSimbolo(String simbolo, String tipoDeDado, String tipoDoToken) {
		simbolos.add(new EntradaTabelaDeSimbolos(simbolo, tipoDeDado, tipoDoToken));
	}

	public void adicionarSimbolos(List<String> simbolos, String tipoDeDado, String tipoDoToken) {
		for (String simbolo : simbolos) {
			this.simbolos.add(new EntradaTabelaDeSimbolos(simbolo, tipoDeDado, tipoDoToken));
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

	public String getTipoDoToken(String simbolo) {
		for (EntradaTabelaDeSimbolos etds : simbolos) {
			if (etds.getSimbolo().equals(simbolo)) {
				return etds.getTipoDoToken();
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
