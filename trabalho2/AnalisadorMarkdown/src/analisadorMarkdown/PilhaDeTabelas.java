package analisadorMarkdown;

import java.util.LinkedList;
import java.util.List;

public class PilhaDeTabelas {

	private LinkedList<TabelaDeSimbolos> pilha;

	public PilhaDeTabelas() {
		pilha = new LinkedList<TabelaDeSimbolos>();
	}

	public void empilhar(TabelaDeSimbolos ts) {
		pilha.push(ts);
	}

	public TabelaDeSimbolos topo() {
		return pilha.peek();
	}

	public boolean existeSimbolo(String simbolo) {
		for (TabelaDeSimbolos ts : pilha) {
			if (ts.existeSimbolo(simbolo)) {
				return true;
			}
		}
		return false;
	}

	public String getTipoDeDado(String simbolo) {
		for (TabelaDeSimbolos tabela : pilha) {
			if (tabela.existeSimbolo(simbolo)) {
				return tabela.getTipoDeDado(simbolo);
			}
		}
		return null;
	}

	public void desempilhar() {
		pilha.pop();
	}

	public List<TabelaDeSimbolos> getTodasTabelas() {
		return pilha;
	}

	public int size() {
		return pilha.size();
	}
}
