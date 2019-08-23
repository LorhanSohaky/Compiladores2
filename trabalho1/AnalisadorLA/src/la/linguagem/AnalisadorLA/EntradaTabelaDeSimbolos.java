package la.linguagem.AnalisadorLA;

public class EntradaTabelaDeSimbolos {
	private String simbolo, tipoDeDado, tipoDoToken;

	public EntradaTabelaDeSimbolos(String simbolo, String tipoDeDado, String tipoDoToken) {
		this.simbolo = simbolo;
		this.tipoDeDado = tipoDeDado;
		this.tipoDoToken = tipoDoToken;
	}

	public String getSimbolo() {
		return this.simbolo;
	}

	public String getTipoDeDado() {
		return this.tipoDeDado;
	}

	public String getTipoDoToken() {
		return this.tipoDoToken;
	}

	@Override
	public String toString() {
		return this.simbolo + "(" + this.tipoDeDado + ")";
	}
}
