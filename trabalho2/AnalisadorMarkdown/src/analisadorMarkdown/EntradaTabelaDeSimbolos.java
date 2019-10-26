package analisadorMarkdown;

public class EntradaTabelaDeSimbolos {
	private String simbolo, tipoDeDado;

	public EntradaTabelaDeSimbolos(String simbolo, String tipoDeDado) {
		this.simbolo = simbolo;
		this.tipoDeDado = tipoDeDado;
	}

	public String getSimbolo() {
		return this.simbolo;
	}

	public String getTipoDeDado() {
		return this.tipoDeDado;
	}

	@Override
	public String toString() {
		return this.simbolo + "(" + this.tipoDeDado + ")";
	}
}
