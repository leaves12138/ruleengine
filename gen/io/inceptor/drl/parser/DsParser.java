// Generated from /home/transwarp/gitlab/inceptor-drools/src/main/java/io/inceptor/drl/parser/DsParser.g4 by ANTLR 4.8
package io.inceptor.drl.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DECLARE=1, END=2, URL=3, USERNAME=4, PASSWORD=5, TYPE=6, Colon=7, PORT=8, 
		HOST=9, Identifier=10, STRING=11, NUMBER=12, WS=13, BlockComment=14, LineComment=15;
	public static final int
		RULE_declares = 0, RULE_declare = 1, RULE_field = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"declares", "declare", "field"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'declare'", "'end'", "'url'", "'username'", "'password'", "'type'", 
			"':'", "'port'", "'host'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DECLARE", "END", "URL", "USERNAME", "PASSWORD", "TYPE", "Colon", 
			"PORT", "HOST", "Identifier", "STRING", "NUMBER", "WS", "BlockComment", 
			"LineComment"
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
	public String getGrammarFileName() { return "DsParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class DeclaresContext extends ParserRuleContext {
		public List<DeclareContext> declare() {
			return getRuleContexts(DeclareContext.class);
		}
		public DeclareContext declare(int i) {
			return getRuleContext(DeclareContext.class,i);
		}
		public DeclaresContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declares; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).enterDeclares(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).exitDeclares(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DsParserVisitor ) return ((DsParserVisitor<? extends T>)visitor).visitDeclares(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaresContext declares() throws RecognitionException {
		DeclaresContext _localctx = new DeclaresContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_declares);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(6);
				declare();
				}
				}
				setState(9); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DECLARE );
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

	public static class DeclareContext extends ParserRuleContext {
		public TerminalNode DECLARE() { return getToken(DsParser.DECLARE, 0); }
		public TerminalNode Identifier() { return getToken(DsParser.Identifier, 0); }
		public TerminalNode END() { return getToken(DsParser.END, 0); }
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public DeclareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).enterDeclare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).exitDeclare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DsParserVisitor ) return ((DsParserVisitor<? extends T>)visitor).visitDeclare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclareContext declare() throws RecognitionException {
		DeclareContext _localctx = new DeclareContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declare);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11);
			match(DECLARE);
			setState(12);
			match(Identifier);
			setState(14); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(13);
				field();
				}
				}
				setState(16); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << URL) | (1L << USERNAME) | (1L << PASSWORD) | (1L << TYPE) | (1L << PORT) | (1L << HOST) | (1L << STRING))) != 0) );
			setState(18);
			match(END);
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

	public static class FieldContext extends ParserRuleContext {
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
	 
		public FieldContext() { }
		public void copyFrom(FieldContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeBranchContext extends FieldContext {
		public TerminalNode TYPE() { return getToken(DsParser.TYPE, 0); }
		public TerminalNode Colon() { return getToken(DsParser.Colon, 0); }
		public TerminalNode STRING() { return getToken(DsParser.STRING, 0); }
		public TypeBranchContext(FieldContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).enterTypeBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).exitTypeBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DsParserVisitor ) return ((DsParserVisitor<? extends T>)visitor).visitTypeBranch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class URLBranchContext extends FieldContext {
		public TerminalNode URL() { return getToken(DsParser.URL, 0); }
		public TerminalNode Colon() { return getToken(DsParser.Colon, 0); }
		public TerminalNode STRING() { return getToken(DsParser.STRING, 0); }
		public URLBranchContext(FieldContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).enterURLBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).exitURLBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DsParserVisitor ) return ((DsParserVisitor<? extends T>)visitor).visitURLBranch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class HostBranchContext extends FieldContext {
		public TerminalNode HOST() { return getToken(DsParser.HOST, 0); }
		public TerminalNode Colon() { return getToken(DsParser.Colon, 0); }
		public TerminalNode STRING() { return getToken(DsParser.STRING, 0); }
		public HostBranchContext(FieldContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).enterHostBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).exitHostBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DsParserVisitor ) return ((DsParserVisitor<? extends T>)visitor).visitHostBranch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UserDefineBranchContext extends FieldContext {
		public Token name;
		public Token value;
		public TerminalNode Colon() { return getToken(DsParser.Colon, 0); }
		public List<TerminalNode> STRING() { return getTokens(DsParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(DsParser.STRING, i);
		}
		public TerminalNode NUMBER() { return getToken(DsParser.NUMBER, 0); }
		public UserDefineBranchContext(FieldContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).enterUserDefineBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).exitUserDefineBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DsParserVisitor ) return ((DsParserVisitor<? extends T>)visitor).visitUserDefineBranch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PasswordBranchContext extends FieldContext {
		public TerminalNode PASSWORD() { return getToken(DsParser.PASSWORD, 0); }
		public TerminalNode Colon() { return getToken(DsParser.Colon, 0); }
		public TerminalNode STRING() { return getToken(DsParser.STRING, 0); }
		public PasswordBranchContext(FieldContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).enterPasswordBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).exitPasswordBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DsParserVisitor ) return ((DsParserVisitor<? extends T>)visitor).visitPasswordBranch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UserNameBranchContext extends FieldContext {
		public TerminalNode USERNAME() { return getToken(DsParser.USERNAME, 0); }
		public TerminalNode Colon() { return getToken(DsParser.Colon, 0); }
		public TerminalNode STRING() { return getToken(DsParser.STRING, 0); }
		public UserNameBranchContext(FieldContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).enterUserNameBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).exitUserNameBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DsParserVisitor ) return ((DsParserVisitor<? extends T>)visitor).visitUserNameBranch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PortBranchContext extends FieldContext {
		public TerminalNode PORT() { return getToken(DsParser.PORT, 0); }
		public TerminalNode Colon() { return getToken(DsParser.Colon, 0); }
		public TerminalNode NUMBER() { return getToken(DsParser.NUMBER, 0); }
		public PortBranchContext(FieldContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).enterPortBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DsParserListener ) ((DsParserListener)listener).exitPortBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DsParserVisitor ) return ((DsParserVisitor<? extends T>)visitor).visitPortBranch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_field);
		try {
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				_localctx = new TypeBranchContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				match(TYPE);
				setState(21);
				match(Colon);
				setState(22);
				match(STRING);
				}
				break;
			case URL:
				_localctx = new URLBranchContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(23);
				match(URL);
				setState(24);
				match(Colon);
				setState(25);
				match(STRING);
				}
				break;
			case USERNAME:
				_localctx = new UserNameBranchContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(26);
				match(USERNAME);
				setState(27);
				match(Colon);
				setState(28);
				match(STRING);
				}
				break;
			case PASSWORD:
				_localctx = new PasswordBranchContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(29);
				match(PASSWORD);
				setState(30);
				match(Colon);
				setState(31);
				match(STRING);
				}
				break;
			case PORT:
				_localctx = new PortBranchContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(32);
				match(PORT);
				setState(33);
				match(Colon);
				setState(34);
				match(NUMBER);
				}
				break;
			case HOST:
				_localctx = new HostBranchContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(35);
				match(HOST);
				setState(36);
				match(Colon);
				setState(37);
				match(STRING);
				}
				break;
			case STRING:
				_localctx = new UserDefineBranchContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(38);
				((UserDefineBranchContext)_localctx).name = match(STRING);
				setState(39);
				match(Colon);
				setState(42);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case STRING:
					{
					setState(40);
					((UserDefineBranchContext)_localctx).value = match(STRING);
					}
					break;
				case NUMBER:
					{
					setState(41);
					((UserDefineBranchContext)_localctx).value = match(NUMBER);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21\61\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\3\2\6\2\n\n\2\r\2\16\2\13\3\3\3\3\3\3\6\3\21\n\3\r\3\16\3"+
		"\22\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4-\n\4\5\4/\n\4\3\4\2\2\5\2\4\6\2\2\2"+
		"\66\2\t\3\2\2\2\4\r\3\2\2\2\6.\3\2\2\2\b\n\5\4\3\2\t\b\3\2\2\2\n\13\3"+
		"\2\2\2\13\t\3\2\2\2\13\f\3\2\2\2\f\3\3\2\2\2\r\16\7\3\2\2\16\20\7\f\2"+
		"\2\17\21\5\6\4\2\20\17\3\2\2\2\21\22\3\2\2\2\22\20\3\2\2\2\22\23\3\2\2"+
		"\2\23\24\3\2\2\2\24\25\7\4\2\2\25\5\3\2\2\2\26\27\7\b\2\2\27\30\7\t\2"+
		"\2\30/\7\r\2\2\31\32\7\5\2\2\32\33\7\t\2\2\33/\7\r\2\2\34\35\7\6\2\2\35"+
		"\36\7\t\2\2\36/\7\r\2\2\37 \7\7\2\2 !\7\t\2\2!/\7\r\2\2\"#\7\n\2\2#$\7"+
		"\t\2\2$/\7\16\2\2%&\7\13\2\2&\'\7\t\2\2\'/\7\r\2\2()\7\r\2\2),\7\t\2\2"+
		"*-\7\r\2\2+-\7\16\2\2,*\3\2\2\2,+\3\2\2\2-/\3\2\2\2.\26\3\2\2\2.\31\3"+
		"\2\2\2.\34\3\2\2\2.\37\3\2\2\2.\"\3\2\2\2.%\3\2\2\2.(\3\2\2\2/\7\3\2\2"+
		"\2\6\13\22,.";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}