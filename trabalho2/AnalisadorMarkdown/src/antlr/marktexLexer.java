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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, STRING=16, 
		DATE=17, COMENTARIO=18, WS=19, BODY=20, ALIAS=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "STRING", "DATE", 
			"COMENTARIO", "WS", "BODY", "ALIAS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+++++'", "'type'", "':'", "'article'", "'monography'", "'title'", 
			"'author'", "'date'", "'header-includes'", "'@misc'", "'{'", "','", "'='", 
			"'year'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "STRING", "DATE", "COMENTARIO", "WS", "BODY", 
			"ALIAS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u00c1\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\7\21\u0085"+
		"\n\21\f\21\16\21\u0088\13\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\7\23\u009b\n\23\f\23\16\23"+
		"\u009e\13\23\3\23\3\23\3\23\3\23\3\23\3\24\6\24\u00a6\n\24\r\24\16\24"+
		"\u00a7\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\6\25\u00b3\n\25\r"+
		"\25\16\25\u00b4\3\25\3\25\3\25\3\25\3\25\3\25\3\26\6\26\u00be\n\26\r\26"+
		"\16\26\u00bf\3\u00b4\2\27\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27\3\2\7\5\2\f\f"+
		"\17\17$$\3\2\62;\4\2\f\f\17\17\5\2\13\f\17\17\"\"\6\2\62;C\\aac|\2\u00c5"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2\5\63\3\2"+
		"\2\2\78\3\2\2\2\t:\3\2\2\2\13B\3\2\2\2\rM\3\2\2\2\17S\3\2\2\2\21Z\3\2"+
		"\2\2\23_\3\2\2\2\25o\3\2\2\2\27u\3\2\2\2\31w\3\2\2\2\33y\3\2\2\2\35{\3"+
		"\2\2\2\37\u0080\3\2\2\2!\u0082\3\2\2\2#\u008b\3\2\2\2%\u0096\3\2\2\2\'"+
		"\u00a5\3\2\2\2)\u00ab\3\2\2\2+\u00bd\3\2\2\2-.\7-\2\2./\7-\2\2/\60\7-"+
		"\2\2\60\61\7-\2\2\61\62\7-\2\2\62\4\3\2\2\2\63\64\7v\2\2\64\65\7{\2\2"+
		"\65\66\7r\2\2\66\67\7g\2\2\67\6\3\2\2\289\7<\2\29\b\3\2\2\2:;\7c\2\2;"+
		"<\7t\2\2<=\7v\2\2=>\7k\2\2>?\7e\2\2?@\7n\2\2@A\7g\2\2A\n\3\2\2\2BC\7o"+
		"\2\2CD\7q\2\2DE\7p\2\2EF\7q\2\2FG\7i\2\2GH\7t\2\2HI\7c\2\2IJ\7r\2\2JK"+
		"\7j\2\2KL\7{\2\2L\f\3\2\2\2MN\7v\2\2NO\7k\2\2OP\7v\2\2PQ\7n\2\2QR\7g\2"+
		"\2R\16\3\2\2\2ST\7c\2\2TU\7w\2\2UV\7v\2\2VW\7j\2\2WX\7q\2\2XY\7t\2\2Y"+
		"\20\3\2\2\2Z[\7f\2\2[\\\7c\2\2\\]\7v\2\2]^\7g\2\2^\22\3\2\2\2_`\7j\2\2"+
		"`a\7g\2\2ab\7c\2\2bc\7f\2\2cd\7g\2\2de\7t\2\2ef\7/\2\2fg\7k\2\2gh\7p\2"+
		"\2hi\7e\2\2ij\7n\2\2jk\7w\2\2kl\7f\2\2lm\7g\2\2mn\7u\2\2n\24\3\2\2\2o"+
		"p\7B\2\2pq\7o\2\2qr\7k\2\2rs\7u\2\2st\7e\2\2t\26\3\2\2\2uv\7}\2\2v\30"+
		"\3\2\2\2wx\7.\2\2x\32\3\2\2\2yz\7?\2\2z\34\3\2\2\2{|\7{\2\2|}\7g\2\2}"+
		"~\7c\2\2~\177\7t\2\2\177\36\3\2\2\2\u0080\u0081\7\177\2\2\u0081 \3\2\2"+
		"\2\u0082\u0086\7$\2\2\u0083\u0085\n\2\2\2\u0084\u0083\3\2\2\2\u0085\u0088"+
		"\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0089\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0089\u008a\7$\2\2\u008a\"\3\2\2\2\u008b\u008c\t\3\2\2"+
		"\u008c\u008d\t\3\2\2\u008d\u008e\7\61\2\2\u008e\u008f\t\3\2\2\u008f\u0090"+
		"\t\3\2\2\u0090\u0091\7\61\2\2\u0091\u0092\t\3\2\2\u0092\u0093\t\3\2\2"+
		"\u0093\u0094\t\3\2\2\u0094\u0095\t\3\2\2\u0095$\3\2\2\2\u0096\u0097\7"+
		"}\2\2\u0097\u0098\7}\2\2\u0098\u009c\3\2\2\2\u0099\u009b\n\4\2\2\u009a"+
		"\u0099\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d\u009f\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a0\7\177\2\2\u00a0"+
		"\u00a1\7\177\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\b\23\2\2\u00a3&\3\2\2"+
		"\2\u00a4\u00a6\t\5\2\2\u00a5\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a5"+
		"\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\b\24\2\2"+
		"\u00aa(\3\2\2\2\u00ab\u00ac\7,\2\2\u00ac\u00ad\7,\2\2\u00ad\u00ae\7,\2"+
		"\2\u00ae\u00af\7,\2\2\u00af\u00b0\7,\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00b3"+
		"\13\2\2\2\u00b2\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2"+
		"\u00b4\u00b2\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\7,\2\2\u00b7\u00b8"+
		"\7,\2\2\u00b8\u00b9\7,\2\2\u00b9\u00ba\7,\2\2\u00ba\u00bb\7,\2\2\u00bb"+
		"*\3\2\2\2\u00bc\u00be\t\6\2\2\u00bd\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2"+
		"\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0,\3\2\2\2\b\2\u0086\u009c"+
		"\u00a7\u00b4\u00bf\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}