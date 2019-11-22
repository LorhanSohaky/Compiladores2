// Generated from AnalisadorMarkdown/src/antlr/marktex.g4 by ANTLR 4.7.2
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class marktexLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		STRING=10, DATE=11, COMENTARIO=12, WS=13, BODY=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"STRING", "DATE", "COMENTARIO", "WS", "BODY"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+++++'", "'type'", "':'", "'article'", "'monography'", "'title'", 
			"'author'", "'date'", "'header-includes'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "STRING", 
			"DATE", "COMENTARIO", "WS", "BODY"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public marktexLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "marktex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20\u0097\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\7\13d\n\13\f\13\16\13g\13\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\7"+
		"\rz\n\r\f\r\16\r}\13\r\3\r\3\r\3\r\3\r\3\r\3\16\6\16\u0085\n\16\r\16\16"+
		"\16\u0086\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\6\17\u0092\n\17"+
		"\r\17\16\17\u0093\3\17\3\17\3\u0093\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\3\2\6\5\2\f\f\17\17$$\3\2\62;"+
		"\4\2\f\f\17\17\5\2\13\f\17\17\"\"\2\u009a\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\3\37\3\2\2\2\5%\3\2\2\2\7*\3\2\2\2\t,\3\2\2\2\13\64\3\2\2"+
		"\2\r?\3\2\2\2\17E\3\2\2\2\21L\3\2\2\2\23Q\3\2\2\2\25a\3\2\2\2\27j\3\2"+
		"\2\2\31u\3\2\2\2\33\u0084\3\2\2\2\35\u008a\3\2\2\2\37 \7-\2\2 !\7-\2\2"+
		"!\"\7-\2\2\"#\7-\2\2#$\7-\2\2$\4\3\2\2\2%&\7v\2\2&\'\7{\2\2\'(\7r\2\2"+
		"()\7g\2\2)\6\3\2\2\2*+\7<\2\2+\b\3\2\2\2,-\7c\2\2-.\7t\2\2./\7v\2\2/\60"+
		"\7k\2\2\60\61\7e\2\2\61\62\7n\2\2\62\63\7g\2\2\63\n\3\2\2\2\64\65\7o\2"+
		"\2\65\66\7q\2\2\66\67\7p\2\2\678\7q\2\289\7i\2\29:\7t\2\2:;\7c\2\2;<\7"+
		"r\2\2<=\7j\2\2=>\7{\2\2>\f\3\2\2\2?@\7v\2\2@A\7k\2\2AB\7v\2\2BC\7n\2\2"+
		"CD\7g\2\2D\16\3\2\2\2EF\7c\2\2FG\7w\2\2GH\7v\2\2HI\7j\2\2IJ\7q\2\2JK\7"+
		"t\2\2K\20\3\2\2\2LM\7f\2\2MN\7c\2\2NO\7v\2\2OP\7g\2\2P\22\3\2\2\2QR\7"+
		"j\2\2RS\7g\2\2ST\7c\2\2TU\7f\2\2UV\7g\2\2VW\7t\2\2WX\7/\2\2XY\7k\2\2Y"+
		"Z\7p\2\2Z[\7e\2\2[\\\7n\2\2\\]\7w\2\2]^\7f\2\2^_\7g\2\2_`\7u\2\2`\24\3"+
		"\2\2\2ae\7$\2\2bd\n\2\2\2cb\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fh\3"+
		"\2\2\2ge\3\2\2\2hi\7$\2\2i\26\3\2\2\2jk\t\3\2\2kl\t\3\2\2lm\7\61\2\2m"+
		"n\t\3\2\2no\t\3\2\2op\7\61\2\2pq\t\3\2\2qr\t\3\2\2rs\t\3\2\2st\t\3\2\2"+
		"t\30\3\2\2\2uv\7}\2\2vw\7}\2\2w{\3\2\2\2xz\n\4\2\2yx\3\2\2\2z}\3\2\2\2"+
		"{y\3\2\2\2{|\3\2\2\2|~\3\2\2\2}{\3\2\2\2~\177\7\177\2\2\177\u0080\7\177"+
		"\2\2\u0080\u0081\3\2\2\2\u0081\u0082\b\r\2\2\u0082\32\3\2\2\2\u0083\u0085"+
		"\t\5\2\2\u0084\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\b\16\2\2\u0089\34\3\2\2"+
		"\2\u008a\u008b\7,\2\2\u008b\u008c\7,\2\2\u008c\u008d\7,\2\2\u008d\u008e"+
		"\7,\2\2\u008e\u008f\7,\2\2\u008f\u0091\3\2\2\2\u0090\u0092\13\2\2\2\u0091"+
		"\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\3\2\2\2\u0093\u0091\3\2"+
		"\2\2\u0094\u0095\3\2\2\2\u0095\u0096\7\2\2\3\u0096\36\3\2\2\2\7\2e{\u0086"+
		"\u0093\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}