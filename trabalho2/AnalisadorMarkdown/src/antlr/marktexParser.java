// Generated from AnalisadorMarkdown/src/antlr/marktex.g4 by ANTLR 4.7.2
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class marktexParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		STRING=10;
	public static final int
		RULE_document = 0, RULE_configs = 1, RULE_type = 2, RULE_title = 3, RULE_author = 4, 
		RULE_date = 5, RULE_header_includes = 6, RULE_content = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"document", "configs", "type", "title", "author", "date", "header_includes", 
			"content"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'---'", "'type'", "':'", "'article'", "'monography'", "'title'", 
			"'author'", "'date'", "'header-includes'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "STRING"
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

	@Override
	public String getGrammarFileName() { return "marktex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public marktexParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class DocumentContext extends ParserRuleContext {
		public ConfigsContext configs() {
			return getRuleContext(ConfigsContext.class,0);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public DocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_document; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).enterDocument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).exitDocument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof marktexVisitor ) return ((marktexVisitor<? extends T>)visitor).visitDocument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DocumentContext document() throws RecognitionException {
		DocumentContext _localctx = new DocumentContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_document);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			configs();
			setState(17);
			content();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConfigsContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public AuthorContext author() {
			return getRuleContext(AuthorContext.class,0);
		}
		public DateContext date() {
			return getRuleContext(DateContext.class,0);
		}
		public Header_includesContext header_includes() {
			return getRuleContext(Header_includesContext.class,0);
		}
		public ConfigsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_configs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).enterConfigs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).exitConfigs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof marktexVisitor ) return ((marktexVisitor<? extends T>)visitor).visitConfigs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConfigsContext configs() throws RecognitionException {
		ConfigsContext _localctx = new ConfigsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_configs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			match(T__0);
			setState(20);
			type();
			setState(21);
			title();
			setState(22);
			author();
			setState(23);
			date();
			setState(24);
			header_includes();
			setState(25);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof marktexVisitor ) return ((marktexVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(T__1);
			setState(28);
			match(T__2);
			setState(29);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__4) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TitleContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(marktexParser.STRING, 0); }
		public TitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).enterTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).exitTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof marktexVisitor ) return ((marktexVisitor<? extends T>)visitor).visitTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_title);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			match(T__5);
			setState(32);
			match(T__2);
			setState(33);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AuthorContext extends ParserRuleContext {
		public AuthorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_author; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).enterAuthor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).exitAuthor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof marktexVisitor ) return ((marktexVisitor<? extends T>)visitor).visitAuthor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AuthorContext author() throws RecognitionException {
		AuthorContext _localctx = new AuthorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_author);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(T__6);
			setState(36);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DateContext extends ParserRuleContext {
		public DateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).enterDate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).exitDate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof marktexVisitor ) return ((marktexVisitor<? extends T>)visitor).visitDate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DateContext date() throws RecognitionException {
		DateContext _localctx = new DateContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_date);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(T__7);
			setState(39);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Header_includesContext extends ParserRuleContext {
		public Header_includesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header_includes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).enterHeader_includes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).exitHeader_includes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof marktexVisitor ) return ((marktexVisitor<? extends T>)visitor).visitHeader_includes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Header_includesContext header_includes() throws RecognitionException {
		Header_includesContext _localctx = new Header_includesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_header_includes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(T__8);
			setState(42);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContentContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(marktexParser.STRING, 0); }
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marktexListener ) ((marktexListener)listener).exitContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof marktexVisitor ) return ((marktexVisitor<? extends T>)visitor).visitContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_content);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\f\61\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\3\3\2\6"+
		"\7\2(\2\22\3\2\2\2\4\25\3\2\2\2\6\35\3\2\2\2\b!\3\2\2\2\n%\3\2\2\2\f("+
		"\3\2\2\2\16+\3\2\2\2\20.\3\2\2\2\22\23\5\4\3\2\23\24\5\20\t\2\24\3\3\2"+
		"\2\2\25\26\7\3\2\2\26\27\5\6\4\2\27\30\5\b\5\2\30\31\5\n\6\2\31\32\5\f"+
		"\7\2\32\33\5\16\b\2\33\34\7\3\2\2\34\5\3\2\2\2\35\36\7\4\2\2\36\37\7\5"+
		"\2\2\37 \t\2\2\2 \7\3\2\2\2!\"\7\b\2\2\"#\7\5\2\2#$\7\f\2\2$\t\3\2\2\2"+
		"%&\7\t\2\2&\'\7\5\2\2\'\13\3\2\2\2()\7\n\2\2)*\7\5\2\2*\r\3\2\2\2+,\7"+
		"\13\2\2,-\7\5\2\2-\17\3\2\2\2./\7\f\2\2/\21\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}