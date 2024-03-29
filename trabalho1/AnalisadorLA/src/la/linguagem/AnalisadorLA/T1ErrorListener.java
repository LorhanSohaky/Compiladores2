package la.linguagem.AnalisadorLA;

import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

public class T1ErrorListener implements ANTLRErrorListener {

	SaidaParser sp;

	public T1ErrorListener(SaidaParser sp) {
		this.sp = sp;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> rcgnzr, Object o, int i, int i1, String string, RecognitionException re) {
		Token token = (Token) o;
		String msg;
		if (!sp.isModificado()) {
			if (token.getText() == "<EOF>") {
				msg = "EOF";
			} else {
				msg = token.getText();
			}
			sp.println("Linha " + i + ": erro sintatico proximo a " + msg);
		}
	}

	@Override
	public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean bln, BitSet bitset, ATNConfigSet atncs) {
		if (!sp.isModificado()) {
			sp.println("Ambiguidade: linha " + i + ":" + i1);
		}
	}

	@Override
	public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitset, ATNConfigSet atncs) {
	}

	@Override
	public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atncs) {
	}
}
