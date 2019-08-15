package la.linguagem.AnalisadorLA;

import java.util.LinkedList;
import java.util.List;

public class PilhaDeTabelas {

	private LinkedList<TabelaDeSimbolos> pilha;
	private SaidaParser saida;

	public PilhaDeTabelas() {
		pilha = new LinkedList<TabelaDeSimbolos>();
		saida = SaidaParser.getInstance();
	}

	public void empilhar(TabelaDeSimbolos ts) {
		pilha.push(ts);
	}

	public TabelaDeSimbolos topo() {
		return pilha.peek();
	}

	public boolean existeSimbolo(String nome) {
		for (TabelaDeSimbolos ts : pilha) {
			if (ts.existeSimbolo(nome)) {
				return true;
			}
		}
		return false;
	}

	public void desempilhar() {
		TabelaDeSimbolos ret = pilha.pop();
		// saida.println(ret.toString());
	}

	public List<TabelaDeSimbolos> getTodasTabelas() {
		return pilha;
	}
}