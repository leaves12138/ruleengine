// Generated from /home/transwarp/gitlab/inceptor-drools/src/main/java/io/inceptor/drl/parser/DrlParser.g4 by ANTLR 4.8
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
public class DrlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NULL=1, TRUE=2, FALSE=3, RULE=4, DECLARE=5, DATASOURCE=6, END=7, WHEN=8, 
		THEN=9, PACKAGE=10, IMPORT=11, AndAnd=12, OrOr=13, DRL=14, DOTDRL=15, 
		FROM=16, SEP=17, DOT=18, STAR=19, AT=20, LeftParen=21, RightParen=22, 
		LeftBracket=23, RightBracket=24, Colon=25, Comma=26, Equal=27, COMPARE=28, 
		CONTAIN=29, Identifier=30, STRING=31, NUMBER=32, WS=33, BlockComment=34, 
		LineComment=35, FloatingPointLiteral=36, ENDMVEL=37, LINE=38, WS2=39;
	public static final int
		RULE_file = 0, RULE_pack = 1, RULE_importDecls = 2, RULE_declares = 3, 
		RULE_rules = 4, RULE_importDecl = 5, RULE_importDrl = 6, RULE_importDatasource = 7, 
		RULE_importNormal = 8, RULE_declare = 9, RULE_fields = 10, RULE_field = 11, 
		RULE_annotation = 12, RULE_elementValuePairs = 13, RULE_elementValuePair = 14, 
		RULE_elementValue = 15, RULE_onerule = 16, RULE_whenClauses = 17, RULE_whenClause = 18, 
		RULE_conditions = 19, RULE_condition = 20, RULE_compareClause = 21, RULE_containClause = 22, 
		RULE_literal = 23, RULE_methodCall = 24, RULE_booleanLiteral = 25, RULE_codelines = 26, 
		RULE_codeline = 27, RULE_qualifiedName = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "pack", "importDecls", "declares", "rules", "importDecl", "importDrl", 
			"importDatasource", "importNormal", "declare", "fields", "field", "annotation", 
			"elementValuePairs", "elementValuePair", "elementValue", "onerule", "whenClauses", 
			"whenClause", "conditions", "condition", "compareClause", "containClause", 
			"literal", "methodCall", "booleanLiteral", "codelines", "codeline", "qualifiedName"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'null'", "'true'", "'false'", "'rule'", "'declare'", "'datasource'", 
			"'end'", "'when'", "'then'", "'package'", "'import'", null, null, "'drl'", 
			"'.drl'", "'from'", "';'", "'.'", "'*'", "'@'", "'('", "')'", "'['", 
			"']'", "':'", "','", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NULL", "TRUE", "FALSE", "RULE", "DECLARE", "DATASOURCE", "END", 
			"WHEN", "THEN", "PACKAGE", "IMPORT", "AndAnd", "OrOr", "DRL", "DOTDRL", 
			"FROM", "SEP", "DOT", "STAR", "AT", "LeftParen", "RightParen", "LeftBracket", 
			"RightBracket", "Colon", "Comma", "Equal", "COMPARE", "CONTAIN", "Identifier", 
			"STRING", "NUMBER", "WS", "BlockComment", "LineComment", "FloatingPointLiteral", 
			"ENDMVEL", "LINE", "WS2"
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
	public String getGrammarFileName() { return "DrlParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DrlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public PackContext pack() {
			return getRuleContext(PackContext.class,0);
		}
		public ImportDeclsContext importDecls() {
			return getRuleContext(ImportDeclsContext.class,0);
		}
		public DeclaresContext declares() {
			return getRuleContext(DeclaresContext.class,0);
		}
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			pack();
			setState(59);
			importDecls();
			setState(60);
			declares();
			setState(61);
			rules();
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

	public static class PackContext extends ParserRuleContext {
		public TerminalNode PACKAGE() { return getToken(DrlParser.PACKAGE, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode SEP() { return getToken(DrlParser.SEP, 0); }
		public PackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pack; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterPack(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitPack(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitPack(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackContext pack() throws RecognitionException {
		PackContext _localctx = new PackContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_pack);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(PACKAGE);
			setState(64);
			qualifiedName();
			setState(65);
			match(SEP);
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

	public static class ImportDeclsContext extends ParserRuleContext {
		public List<ImportDeclContext> importDecl() {
			return getRuleContexts(ImportDeclContext.class);
		}
		public ImportDeclContext importDecl(int i) {
			return getRuleContext(ImportDeclContext.class,i);
		}
		public ImportDeclsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDecls; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterImportDecls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitImportDecls(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitImportDecls(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclsContext importDecls() throws RecognitionException {
		ImportDeclsContext _localctx = new ImportDeclsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_importDecls);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(67);
				importDecl();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterDeclares(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitDeclares(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitDeclares(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaresContext declares() throws RecognitionException {
		DeclaresContext _localctx = new DeclaresContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declares);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DECLARE || _la==AT) {
				{
				{
				setState(73);
				declare();
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class RulesContext extends ParserRuleContext {
		public List<OneruleContext> onerule() {
			return getRuleContexts(OneruleContext.class);
		}
		public OneruleContext onerule(int i) {
			return getRuleContext(OneruleContext.class,i);
		}
		public RulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RulesContext rules() throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_rules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(79);
				onerule();
				}
				}
				setState(82); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RULE );
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

	public static class ImportDeclContext extends ParserRuleContext {
		public ImportDrlContext importDrl() {
			return getRuleContext(ImportDrlContext.class,0);
		}
		public ImportDatasourceContext importDatasource() {
			return getRuleContext(ImportDatasourceContext.class,0);
		}
		public ImportNormalContext importNormal() {
			return getRuleContext(ImportNormalContext.class,0);
		}
		public ImportDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterImportDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitImportDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitImportDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclContext importDecl() throws RecognitionException {
		ImportDeclContext _localctx = new ImportDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_importDecl);
		try {
			setState(87);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				importDrl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				importDatasource();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
				importNormal();
				}
				break;
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

	public static class ImportDrlContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(DrlParser.IMPORT, 0); }
		public TerminalNode DRL() { return getToken(DrlParser.DRL, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode DOTDRL() { return getToken(DrlParser.DOTDRL, 0); }
		public TerminalNode SEP() { return getToken(DrlParser.SEP, 0); }
		public ImportDrlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDrl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterImportDrl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitImportDrl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitImportDrl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDrlContext importDrl() throws RecognitionException {
		ImportDrlContext _localctx = new ImportDrlContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_importDrl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(IMPORT);
			setState(90);
			match(DRL);
			setState(91);
			qualifiedName();
			setState(92);
			match(DOTDRL);
			setState(93);
			match(SEP);
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

	public static class ImportDatasourceContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(DrlParser.IMPORT, 0); }
		public TerminalNode DATASOURCE() { return getToken(DrlParser.DATASOURCE, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode SEP() { return getToken(DrlParser.SEP, 0); }
		public ImportDatasourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDatasource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterImportDatasource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitImportDatasource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitImportDatasource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDatasourceContext importDatasource() throws RecognitionException {
		ImportDatasourceContext _localctx = new ImportDatasourceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_importDatasource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(IMPORT);
			setState(96);
			match(DATASOURCE);
			setState(97);
			qualifiedName();
			setState(98);
			match(SEP);
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

	public static class ImportNormalContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(DrlParser.IMPORT, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode SEP() { return getToken(DrlParser.SEP, 0); }
		public ImportNormalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importNormal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterImportNormal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitImportNormal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitImportNormal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportNormalContext importNormal() throws RecognitionException {
		ImportNormalContext _localctx = new ImportNormalContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_importNormal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(IMPORT);
			setState(101);
			qualifiedName();
			setState(102);
			match(SEP);
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
		public TerminalNode DECLARE() { return getToken(DrlParser.DECLARE, 0); }
		public TerminalNode Identifier() { return getToken(DrlParser.Identifier, 0); }
		public FieldsContext fields() {
			return getRuleContext(FieldsContext.class,0);
		}
		public TerminalNode END() { return getToken(DrlParser.END, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public DeclareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterDeclare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitDeclare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitDeclare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclareContext declare() throws RecognitionException {
		DeclareContext _localctx = new DeclareContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_declare);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(104);
				annotation();
				}
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(110);
			match(DECLARE);
			setState(111);
			match(Identifier);
			setState(112);
			fields();
			setState(113);
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

	public static class FieldsContext extends ParserRuleContext {
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public FieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fields; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterFields(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitFields(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitFields(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldsContext fields() throws RecognitionException {
		FieldsContext _localctx = new FieldsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_fields);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(115);
				field();
				}
				}
				setState(118); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==AT || _la==Identifier );
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
		public TerminalNode Identifier() { return getToken(DrlParser.Identifier, 0); }
		public TerminalNode Colon() { return getToken(DrlParser.Colon, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(120);
				annotation();
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(126);
			match(Identifier);
			setState(127);
			match(Colon);
			setState(128);
			qualifiedName();
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

	public static class AnnotationContext extends ParserRuleContext {
		public boolean hasValue = false;
		public boolean isPairs = false;
		public TerminalNode AT() { return getToken(DrlParser.AT, 0); }
		public TerminalNode Identifier() { return getToken(DrlParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(DrlParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(DrlParser.RightParen, 0); }
		public ElementValuePairsContext elementValuePairs() {
			return getRuleContext(ElementValuePairsContext.class,0);
		}
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(AT);
			setState(131);
			match(Identifier);
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LeftParen) {
				{
				setState(132);
				match(LeftParen);
				setState(137);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Identifier:
					{
					setState(133);
					elementValuePairs();
					((AnnotationContext)_localctx).isPairs =  true;
					}
					break;
				case TRUE:
				case FALSE:
				case STRING:
				case NUMBER:
				case FloatingPointLiteral:
					{
					setState(136);
					elementValue();
					}
					break;
				case RightParen:
					break;
				default:
					break;
				}
				setState(139);
				match(RightParen);
				((AnnotationContext)_localctx).hasValue =  true;
				}
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

	public static class ElementValuePairsContext extends ParserRuleContext {
		public List<ElementValuePairContext> elementValuePair() {
			return getRuleContexts(ElementValuePairContext.class);
		}
		public ElementValuePairContext elementValuePair(int i) {
			return getRuleContext(ElementValuePairContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(DrlParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(DrlParser.Comma, i);
		}
		public ElementValuePairsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValuePairs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterElementValuePairs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitElementValuePairs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitElementValuePairs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementValuePairsContext elementValuePairs() throws RecognitionException {
		ElementValuePairsContext _localctx = new ElementValuePairsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_elementValuePairs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			elementValuePair();
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(144);
				match(Comma);
				setState(145);
				elementValuePair();
				}
				}
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ElementValuePairContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(DrlParser.Identifier, 0); }
		public TerminalNode Equal() { return getToken(DrlParser.Equal, 0); }
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public ElementValuePairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValuePair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterElementValuePair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitElementValuePair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitElementValuePair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementValuePairContext elementValuePair() throws RecognitionException {
		ElementValuePairContext _localctx = new ElementValuePairContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_elementValuePair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(Identifier);
			setState(152);
			match(Equal);
			setState(153);
			elementValue();
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

	public static class ElementValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(DrlParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(DrlParser.NUMBER, 0); }
		public TerminalNode FloatingPointLiteral() { return getToken(DrlParser.FloatingPointLiteral, 0); }
		public BooleanLiteralContext booleanLiteral() {
			return getRuleContext(BooleanLiteralContext.class,0);
		}
		public ElementValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterElementValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitElementValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitElementValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementValueContext elementValue() throws RecognitionException {
		ElementValueContext _localctx = new ElementValueContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_elementValue);
		try {
			setState(159);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(155);
				match(STRING);
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
				match(NUMBER);
				}
				break;
			case FloatingPointLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(157);
				match(FloatingPointLiteral);
				}
				break;
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 4);
				{
				setState(158);
				booleanLiteral();
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

	public static class OneruleContext extends ParserRuleContext {
		public Token ruleName;
		public TerminalNode RULE() { return getToken(DrlParser.RULE, 0); }
		public TerminalNode WHEN() { return getToken(DrlParser.WHEN, 0); }
		public WhenClausesContext whenClauses() {
			return getRuleContext(WhenClausesContext.class,0);
		}
		public TerminalNode THEN() { return getToken(DrlParser.THEN, 0); }
		public CodelinesContext codelines() {
			return getRuleContext(CodelinesContext.class,0);
		}
		public TerminalNode ENDMVEL() { return getToken(DrlParser.ENDMVEL, 0); }
		public TerminalNode STRING() { return getToken(DrlParser.STRING, 0); }
		public OneruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onerule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterOnerule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitOnerule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitOnerule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OneruleContext onerule() throws RecognitionException {
		OneruleContext _localctx = new OneruleContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_onerule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(RULE);
			setState(162);
			((OneruleContext)_localctx).ruleName = match(STRING);
			setState(163);
			match(WHEN);
			setState(164);
			whenClauses();
			setState(165);
			match(THEN);
			setState(166);
			codelines();
			setState(167);
			match(ENDMVEL);
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

	public static class WhenClausesContext extends ParserRuleContext {
		public List<WhenClauseContext> whenClause() {
			return getRuleContexts(WhenClauseContext.class);
		}
		public WhenClauseContext whenClause(int i) {
			return getRuleContext(WhenClauseContext.class,i);
		}
		public WhenClausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenClauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterWhenClauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitWhenClauses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitWhenClauses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhenClausesContext whenClauses() throws RecognitionException {
		WhenClausesContext _localctx = new WhenClausesContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_whenClauses);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Identifier) {
				{
				{
				setState(169);
				whenClause();
				}
				}
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class WhenClauseContext extends ParserRuleContext {
		public WhenClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenClause; }
	 
		public WhenClauseContext() { }
		public void copyFrom(WhenClauseContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FromDsContext extends WhenClauseContext {
		public Token symbole;
		public Token className;
		public Token dbName;
		public TerminalNode LeftParen() { return getToken(DrlParser.LeftParen, 0); }
		public ConditionsContext conditions() {
			return getRuleContext(ConditionsContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(DrlParser.RightParen, 0); }
		public TerminalNode FROM() { return getToken(DrlParser.FROM, 0); }
		public List<TerminalNode> Identifier() { return getTokens(DrlParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(DrlParser.Identifier, i);
		}
		public TerminalNode Colon() { return getToken(DrlParser.Colon, 0); }
		public FromDsContext(WhenClauseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterFromDs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitFromDs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitFromDs(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FromStreamContext extends WhenClauseContext {
		public Token symbole;
		public Token className;
		public TerminalNode LeftParen() { return getToken(DrlParser.LeftParen, 0); }
		public ConditionsContext conditions() {
			return getRuleContext(ConditionsContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(DrlParser.RightParen, 0); }
		public List<TerminalNode> Identifier() { return getTokens(DrlParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(DrlParser.Identifier, i);
		}
		public TerminalNode Colon() { return getToken(DrlParser.Colon, 0); }
		public FromStreamContext(WhenClauseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterFromStream(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitFromStream(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitFromStream(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhenClauseContext whenClause() throws RecognitionException {
		WhenClauseContext _localctx = new WhenClauseContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_whenClause);
		try {
			setState(195);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new FromStreamContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(177);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(175);
					((FromStreamContext)_localctx).symbole = match(Identifier);
					setState(176);
					match(Colon);
					}
					break;
				}
				setState(179);
				((FromStreamContext)_localctx).className = match(Identifier);
				setState(180);
				match(LeftParen);
				setState(181);
				conditions();
				setState(182);
				match(RightParen);
				}
				break;
			case 2:
				_localctx = new FromDsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(184);
					((FromDsContext)_localctx).symbole = match(Identifier);
					setState(185);
					match(Colon);
					}
					break;
				}
				setState(188);
				((FromDsContext)_localctx).className = match(Identifier);
				setState(189);
				match(LeftParen);
				setState(190);
				conditions();
				setState(191);
				match(RightParen);
				setState(192);
				match(FROM);
				setState(193);
				((FromDsContext)_localctx).dbName = match(Identifier);
				}
				break;
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

	public static class ConditionsContext extends ParserRuleContext {
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(DrlParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(DrlParser.Comma, i);
		}
		public ConditionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterConditions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitConditions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitConditions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionsContext conditions() throws RecognitionException {
		ConditionsContext _localctx = new ConditionsContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_conditions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftParen) | (1L << RightBracket) | (1L << Comma) | (1L << Identifier))) != 0)) {
				{
				setState(197);
				condition(0);
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(198);
					match(Comma);
					setState(199);
					condition(0);
					}
					}
					setState(204);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
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

	public static class ConditionContext extends ParserRuleContext {
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	 
		public ConditionContext() { }
		public void copyFrom(ConditionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ContainConditionContext extends ConditionContext {
		public ContainClauseContext containClause() {
			return getRuleContext(ContainClauseContext.class,0);
		}
		public ContainConditionContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterContainCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitContainCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitContainCondition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndOrConditionContext extends ConditionContext {
		public ConditionContext left;
		public Token op;
		public ConditionContext right;
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public TerminalNode AndAnd() { return getToken(DrlParser.AndAnd, 0); }
		public TerminalNode OrOr() { return getToken(DrlParser.OrOr, 0); }
		public AndOrConditionContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterAndOrCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitAndOrCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitAndOrCondition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompareConditionContext extends ConditionContext {
		public CompareClauseContext compareClause() {
			return getRuleContext(CompareClauseContext.class,0);
		}
		public CompareConditionContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterCompareCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitCompareCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitCompareCondition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConditionSelfContext extends ConditionContext {
		public TerminalNode LeftParen() { return getToken(DrlParser.LeftParen, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(DrlParser.RightParen, 0); }
		public ConditionSelfContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterConditionSelf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitConditionSelf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitConditionSelf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		return condition(0);
	}

	private ConditionContext condition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionContext _localctx = new ConditionContext(_ctx, _parentState);
		ConditionContext _prevctx = _localctx;
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_condition, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				_localctx = new CompareConditionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(208);
				compareClause();
				}
				break;
			case 2:
				{
				_localctx = new ContainConditionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(209);
				containClause();
				}
				break;
			case 3:
				{
				_localctx = new ConditionSelfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(210);
				match(LeftParen);
				setState(211);
				condition(0);
				setState(212);
				match(RightParen);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(221);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AndOrConditionContext(new ConditionContext(_parentctx, _parentState));
					((AndOrConditionContext)_localctx).left = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_condition);
					setState(216);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(217);
					((AndOrConditionContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==AndAnd || _la==OrOr) ) {
						((AndOrConditionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(218);
					((AndOrConditionContext)_localctx).right = condition(2);
					}
					} 
				}
				setState(223);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CompareClauseContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(DrlParser.Identifier, 0); }
		public TerminalNode COMPARE() { return getToken(DrlParser.COMPARE, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public CompareClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compareClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterCompareClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitCompareClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitCompareClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompareClauseContext compareClause() throws RecognitionException {
		CompareClauseContext _localctx = new CompareClauseContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_compareClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(Identifier);
			setState(225);
			match(COMPARE);
			setState(226);
			literal();
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

	public static class ContainClauseContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(DrlParser.Identifier, 0); }
		public TerminalNode CONTAIN() { return getToken(DrlParser.CONTAIN, 0); }
		public TerminalNode LeftBracket() { return getToken(DrlParser.LeftBracket, 0); }
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public TerminalNode RightBracket() { return getToken(DrlParser.RightBracket, 0); }
		public List<TerminalNode> Comma() { return getTokens(DrlParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(DrlParser.Comma, i);
		}
		public ContainClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_containClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterContainClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitContainClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitContainClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContainClauseContext containClause() throws RecognitionException {
		ContainClauseContext _localctx = new ContainClauseContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_containClause);
		int _la;
		try {
			setState(240);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				match(Identifier);
				setState(229);
				match(CONTAIN);
				setState(230);
				match(LeftBracket);
				setState(231);
				literal();
				}
				break;
			case RightBracket:
			case Comma:
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(232);
					match(Comma);
					setState(233);
					literal();
					}
					}
					setState(238);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(239);
				match(RightBracket);
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

	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MethodBranchContext extends LiteralContext {
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public MethodBranchContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterMethodBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitMethodBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitMethodBranch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullBranchContext extends LiteralContext {
		public TerminalNode NULL() { return getToken(DrlParser.NULL, 0); }
		public NullBranchContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterNullBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitNullBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitNullBranch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberBranchContext extends LiteralContext {
		public TerminalNode NUMBER() { return getToken(DrlParser.NUMBER, 0); }
		public NumberBranchContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterNumberBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitNumberBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitNumberBranch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringBranchContext extends LiteralContext {
		public TerminalNode STRING() { return getToken(DrlParser.STRING, 0); }
		public StringBranchContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterStringBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitStringBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitStringBranch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatBranchContext extends LiteralContext {
		public TerminalNode FloatingPointLiteral() { return getToken(DrlParser.FloatingPointLiteral, 0); }
		public FloatBranchContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterFloatBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitFloatBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitFloatBranch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanBranchContext extends LiteralContext {
		public BooleanLiteralContext booleanLiteral() {
			return getRuleContext(BooleanLiteralContext.class,0);
		}
		public BooleanBranchContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterBooleanBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitBooleanBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitBooleanBranch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_literal);
		try {
			setState(248);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				_localctx = new NumberBranchContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				match(NUMBER);
				}
				break;
			case FloatingPointLiteral:
				_localctx = new FloatBranchContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				match(FloatingPointLiteral);
				}
				break;
			case STRING:
				_localctx = new StringBranchContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(244);
				match(STRING);
				}
				break;
			case TRUE:
			case FALSE:
				_localctx = new BooleanBranchContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(245);
				booleanLiteral();
				}
				break;
			case NULL:
				_localctx = new NullBranchContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(246);
				match(NULL);
				}
				break;
			case Identifier:
				_localctx = new MethodBranchContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(247);
				methodCall();
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

	public static class MethodCallContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(DrlParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(DrlParser.Identifier, i);
		}
		public List<TerminalNode> DOT() { return getTokens(DrlParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(DrlParser.DOT, i);
		}
		public TerminalNode LeftParen() { return getToken(DrlParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(DrlParser.RightParen, 0); }
		public MethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitMethodCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitMethodCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodCallContext methodCall() throws RecognitionException {
		MethodCallContext _localctx = new MethodCallContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_methodCall);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			match(Identifier);
			setState(255);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(251);
					match(DOT);
					setState(252);
					match(Identifier);
					}
					} 
				}
				setState(257);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			setState(266);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(258);
				match(LeftParen);
				setState(262);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Identifier) {
					{
					{
					setState(259);
					match(Identifier);
					}
					}
					setState(264);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(265);
				match(RightParen);
				}
				break;
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

	public static class BooleanLiteralContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(DrlParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(DrlParser.FALSE, 0); }
		public BooleanLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterBooleanLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitBooleanLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitBooleanLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanLiteralContext booleanLiteral() throws RecognitionException {
		BooleanLiteralContext _localctx = new BooleanLiteralContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_booleanLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
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

	public static class CodelinesContext extends ParserRuleContext {
		public List<CodelineContext> codeline() {
			return getRuleContexts(CodelineContext.class);
		}
		public CodelineContext codeline(int i) {
			return getRuleContext(CodelineContext.class,i);
		}
		public CodelinesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codelines; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterCodelines(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitCodelines(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitCodelines(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodelinesContext codelines() throws RecognitionException {
		CodelinesContext _localctx = new CodelinesContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_codelines);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LINE) {
				{
				{
				setState(270);
				codeline();
				}
				}
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class CodelineContext extends ParserRuleContext {
		public TerminalNode LINE() { return getToken(DrlParser.LINE, 0); }
		public CodelineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterCodeline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitCodeline(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitCodeline(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodelineContext codeline() throws RecognitionException {
		CodelineContext _localctx = new CodelineContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_codeline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(LINE);
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

	public static class QualifiedNameContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(DrlParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(DrlParser.Identifier, i);
		}
		public List<TerminalNode> DOT() { return getTokens(DrlParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(DrlParser.DOT, i);
		}
		public TerminalNode STAR() { return getToken(DrlParser.STAR, 0); }
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).enterQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrlParserListener ) ((DrlParserListener)listener).exitQualifiedName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrlParserVisitor ) return ((DrlParserVisitor<? extends T>)visitor).visitQualifiedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_qualifiedName);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			match(Identifier);
			setState(283);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(279);
					match(DOT);
					setState(280);
					match(Identifier);
					}
					} 
				}
				setState(285);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			setState(288);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(286);
				match(DOT);
				setState(287);
				match(STAR);
				}
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 20:
			return condition_sempred((ConditionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean condition_sempred(ConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3)\u0125\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\4\7\4G\n\4\f\4\16\4J\13\4\3\5\7\5M\n\5\f\5\16\5P\13"+
		"\5\3\6\6\6S\n\6\r\6\16\6T\3\7\3\7\3\7\5\7Z\n\7\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\7\13l\n\13\f\13\16\13o\13\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\6\fw\n\f\r\f\16\fx\3\r\7\r|\n\r\f\r\16\r"+
		"\177\13\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u008c"+
		"\n\16\3\16\3\16\5\16\u0090\n\16\3\17\3\17\3\17\7\17\u0095\n\17\f\17\16"+
		"\17\u0098\13\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\5\21\u00a2\n\21"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\7\23\u00ad\n\23\f\23\16"+
		"\23\u00b0\13\23\3\24\3\24\5\24\u00b4\n\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\5\24\u00bd\n\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u00c6\n"+
		"\24\3\25\3\25\3\25\7\25\u00cb\n\25\f\25\16\25\u00ce\13\25\5\25\u00d0\n"+
		"\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00d9\n\26\3\26\3\26\3\26"+
		"\7\26\u00de\n\26\f\26\16\26\u00e1\13\26\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\7\30\u00ed\n\30\f\30\16\30\u00f0\13\30\3\30\5\30"+
		"\u00f3\n\30\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u00fb\n\31\3\32\3\32\3"+
		"\32\7\32\u0100\n\32\f\32\16\32\u0103\13\32\3\32\3\32\7\32\u0107\n\32\f"+
		"\32\16\32\u010a\13\32\3\32\5\32\u010d\n\32\3\33\3\33\3\34\7\34\u0112\n"+
		"\34\f\34\16\34\u0115\13\34\3\35\3\35\3\36\3\36\3\36\7\36\u011c\n\36\f"+
		"\36\16\36\u011f\13\36\3\36\3\36\5\36\u0123\n\36\3\36\2\3*\37\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\4\3\2\16\17\3\2"+
		"\4\5\2\u012c\2<\3\2\2\2\4A\3\2\2\2\6H\3\2\2\2\bN\3\2\2\2\nR\3\2\2\2\f"+
		"Y\3\2\2\2\16[\3\2\2\2\20a\3\2\2\2\22f\3\2\2\2\24m\3\2\2\2\26v\3\2\2\2"+
		"\30}\3\2\2\2\32\u0084\3\2\2\2\34\u0091\3\2\2\2\36\u0099\3\2\2\2 \u00a1"+
		"\3\2\2\2\"\u00a3\3\2\2\2$\u00ae\3\2\2\2&\u00c5\3\2\2\2(\u00cf\3\2\2\2"+
		"*\u00d8\3\2\2\2,\u00e2\3\2\2\2.\u00f2\3\2\2\2\60\u00fa\3\2\2\2\62\u00fc"+
		"\3\2\2\2\64\u010e\3\2\2\2\66\u0113\3\2\2\28\u0116\3\2\2\2:\u0118\3\2\2"+
		"\2<=\5\4\3\2=>\5\6\4\2>?\5\b\5\2?@\5\n\6\2@\3\3\2\2\2AB\7\f\2\2BC\5:\36"+
		"\2CD\7\23\2\2D\5\3\2\2\2EG\5\f\7\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2"+
		"\2\2I\7\3\2\2\2JH\3\2\2\2KM\5\24\13\2LK\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO"+
		"\3\2\2\2O\t\3\2\2\2PN\3\2\2\2QS\5\"\22\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2"+
		"TU\3\2\2\2U\13\3\2\2\2VZ\5\16\b\2WZ\5\20\t\2XZ\5\22\n\2YV\3\2\2\2YW\3"+
		"\2\2\2YX\3\2\2\2Z\r\3\2\2\2[\\\7\r\2\2\\]\7\20\2\2]^\5:\36\2^_\7\21\2"+
		"\2_`\7\23\2\2`\17\3\2\2\2ab\7\r\2\2bc\7\b\2\2cd\5:\36\2de\7\23\2\2e\21"+
		"\3\2\2\2fg\7\r\2\2gh\5:\36\2hi\7\23\2\2i\23\3\2\2\2jl\5\32\16\2kj\3\2"+
		"\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2np\3\2\2\2om\3\2\2\2pq\7\7\2\2qr\7 "+
		"\2\2rs\5\26\f\2st\7\t\2\2t\25\3\2\2\2uw\5\30\r\2vu\3\2\2\2wx\3\2\2\2x"+
		"v\3\2\2\2xy\3\2\2\2y\27\3\2\2\2z|\5\32\16\2{z\3\2\2\2|\177\3\2\2\2}{\3"+
		"\2\2\2}~\3\2\2\2~\u0080\3\2\2\2\177}\3\2\2\2\u0080\u0081\7 \2\2\u0081"+
		"\u0082\7\33\2\2\u0082\u0083\5:\36\2\u0083\31\3\2\2\2\u0084\u0085\7\26"+
		"\2\2\u0085\u008f\7 \2\2\u0086\u008b\7\27\2\2\u0087\u0088\5\34\17\2\u0088"+
		"\u0089\b\16\1\2\u0089\u008c\3\2\2\2\u008a\u008c\5 \21\2\u008b\u0087\3"+
		"\2\2\2\u008b\u008a\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\u008e\7\30\2\2\u008e\u0090\b\16\1\2\u008f\u0086\3\2\2\2\u008f\u0090\3"+
		"\2\2\2\u0090\33\3\2\2\2\u0091\u0096\5\36\20\2\u0092\u0093\7\34\2\2\u0093"+
		"\u0095\5\36\20\2\u0094\u0092\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3"+
		"\2\2\2\u0096\u0097\3\2\2\2\u0097\35\3\2\2\2\u0098\u0096\3\2\2\2\u0099"+
		"\u009a\7 \2\2\u009a\u009b\7\35\2\2\u009b\u009c\5 \21\2\u009c\37\3\2\2"+
		"\2\u009d\u00a2\7!\2\2\u009e\u00a2\7\"\2\2\u009f\u00a2\7&\2\2\u00a0\u00a2"+
		"\5\64\33\2\u00a1\u009d\3\2\2\2\u00a1\u009e\3\2\2\2\u00a1\u009f\3\2\2\2"+
		"\u00a1\u00a0\3\2\2\2\u00a2!\3\2\2\2\u00a3\u00a4\7\6\2\2\u00a4\u00a5\7"+
		"!\2\2\u00a5\u00a6\7\n\2\2\u00a6\u00a7\5$\23\2\u00a7\u00a8\7\13\2\2\u00a8"+
		"\u00a9\5\66\34\2\u00a9\u00aa\7\'\2\2\u00aa#\3\2\2\2\u00ab\u00ad\5&\24"+
		"\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af"+
		"\3\2\2\2\u00af%\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\7 \2\2\u00b2\u00b4"+
		"\7\33\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2"+
		"\u00b5\u00b6\7 \2\2\u00b6\u00b7\7\27\2\2\u00b7\u00b8\5(\25\2\u00b8\u00b9"+
		"\7\30\2\2\u00b9\u00c6\3\2\2\2\u00ba\u00bb\7 \2\2\u00bb\u00bd\7\33\2\2"+
		"\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf"+
		"\7 \2\2\u00bf\u00c0\7\27\2\2\u00c0\u00c1\5(\25\2\u00c1\u00c2\7\30\2\2"+
		"\u00c2\u00c3\7\22\2\2\u00c3\u00c4\7 \2\2\u00c4\u00c6\3\2\2\2\u00c5\u00b3"+
		"\3\2\2\2\u00c5\u00bc\3\2\2\2\u00c6\'\3\2\2\2\u00c7\u00cc\5*\26\2\u00c8"+
		"\u00c9\7\34\2\2\u00c9\u00cb\5*\26\2\u00ca\u00c8\3\2\2\2\u00cb\u00ce\3"+
		"\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce"+
		"\u00cc\3\2\2\2\u00cf\u00c7\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0)\3\2\2\2"+
		"\u00d1\u00d2\b\26\1\2\u00d2\u00d9\5,\27\2\u00d3\u00d9\5.\30\2\u00d4\u00d5"+
		"\7\27\2\2\u00d5\u00d6\5*\26\2\u00d6\u00d7\7\30\2\2\u00d7\u00d9\3\2\2\2"+
		"\u00d8\u00d1\3\2\2\2\u00d8\u00d3\3\2\2\2\u00d8\u00d4\3\2\2\2\u00d9\u00df"+
		"\3\2\2\2\u00da\u00db\f\3\2\2\u00db\u00dc\t\2\2\2\u00dc\u00de\5*\26\4\u00dd"+
		"\u00da\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2"+
		"\2\2\u00e0+\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e3\7 \2\2\u00e3\u00e4"+
		"\7\36\2\2\u00e4\u00e5\5\60\31\2\u00e5-\3\2\2\2\u00e6\u00e7\7 \2\2\u00e7"+
		"\u00e8\7\37\2\2\u00e8\u00e9\7\31\2\2\u00e9\u00f3\5\60\31\2\u00ea\u00eb"+
		"\7\34\2\2\u00eb\u00ed\5\60\31\2\u00ec\u00ea\3\2\2\2\u00ed\u00f0\3\2\2"+
		"\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00ee"+
		"\3\2\2\2\u00f1\u00f3\7\32\2\2\u00f2\u00e6\3\2\2\2\u00f2\u00ee\3\2\2\2"+
		"\u00f3/\3\2\2\2\u00f4\u00fb\7\"\2\2\u00f5\u00fb\7&\2\2\u00f6\u00fb\7!"+
		"\2\2\u00f7\u00fb\5\64\33\2\u00f8\u00fb\7\3\2\2\u00f9\u00fb\5\62\32\2\u00fa"+
		"\u00f4\3\2\2\2\u00fa\u00f5\3\2\2\2\u00fa\u00f6\3\2\2\2\u00fa\u00f7\3\2"+
		"\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00f9\3\2\2\2\u00fb\61\3\2\2\2\u00fc\u0101"+
		"\7 \2\2\u00fd\u00fe\7\24\2\2\u00fe\u0100\7 \2\2\u00ff\u00fd\3\2\2\2\u0100"+
		"\u0103\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u010c\3\2"+
		"\2\2\u0103\u0101\3\2\2\2\u0104\u0108\7\27\2\2\u0105\u0107\7 \2\2\u0106"+
		"\u0105\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2"+
		"\2\2\u0109\u010b\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u010d\7\30\2\2\u010c"+
		"\u0104\3\2\2\2\u010c\u010d\3\2\2\2\u010d\63\3\2\2\2\u010e\u010f\t\3\2"+
		"\2\u010f\65\3\2\2\2\u0110\u0112\58\35\2\u0111\u0110\3\2\2\2\u0112\u0115"+
		"\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\67\3\2\2\2\u0115"+
		"\u0113\3\2\2\2\u0116\u0117\7(\2\2\u01179\3\2\2\2\u0118\u011d\7 \2\2\u0119"+
		"\u011a\7\24\2\2\u011a\u011c\7 \2\2\u011b\u0119\3\2\2\2\u011c\u011f\3\2"+
		"\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u0122\3\2\2\2\u011f"+
		"\u011d\3\2\2\2\u0120\u0121\7\24\2\2\u0121\u0123\7\25\2\2\u0122\u0120\3"+
		"\2\2\2\u0122\u0123\3\2\2\2\u0123;\3\2\2\2\36HNTYmx}\u008b\u008f\u0096"+
		"\u00a1\u00ae\u00b3\u00bc\u00c5\u00cc\u00cf\u00d8\u00df\u00ee\u00f2\u00fa"+
		"\u0101\u0108\u010c\u0113\u011d\u0122";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}