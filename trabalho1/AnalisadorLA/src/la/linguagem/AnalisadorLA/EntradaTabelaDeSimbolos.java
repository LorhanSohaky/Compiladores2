package la.linguagem.AnalisadorLA;

public class EntradaTabelaDeSimbolos {
	private String nome, tipo, simbolo;

	public EntradaTabelaDeSimbolos(String nome, String tipo, String simbolo) {
		this.nome = nome;
		this.tipo = tipo;
		this.simbolo = simbolo;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}

	public String getSimbolo() {
		return simbolo;
	}

	@Override
	public String toString() {
		return nome + "(" + tipo + ")";
	}
}