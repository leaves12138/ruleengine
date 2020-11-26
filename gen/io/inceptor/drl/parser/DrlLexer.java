// Generated from /home/transwarp/gitlab/inceptor-drools/src/main/java/io/inceptor/drl/parser/DrlLexer.g4 by ANTLR 4.8
package io.inceptor.drl.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DrlLexer extends Lexer {
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
		MVEL=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "MVEL"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"NULL", "TRUE", "FALSE", "RULE", "DECLARE", "DATASOURCE", "END", "WHEN", 
			"THEN", "PACKAGE", "IMPORT", "AndAnd", "OrOr", "DRL", "DOTDRL", "FROM", 
			"SEP", "DOT", "STAR", "AT", "LeftParen", "RightParen", "LeftBracket", 
			"RightBracket", "Colon", "Comma", "Equal", "COMPARE", "CONTAIN", "Identifier", 
			"STRING", "NUMBER", "WS", "BlockComment", "LineComment", "FloatingPointLiteral", 
			"Letter", "Digit", "ENDMVEL", "LINE", "WS2"
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


	public DrlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DrlLexer.g4"; }

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

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 30:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			setText(getText().substring(1,getText().length()-1));
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2)\u0151\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\5\r\u00a3\n\r\3\16\3\16\3\16\3\16\5\16\u00a9"+
		"\n\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\5\35\u00d8\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\5\36\u00e2\n\36\3\37\3\37\3\37\7\37\u00e7\n\37\f\37\16\37\u00ea"+
		"\13\37\3 \3 \3 \3 \7 \u00f0\n \f \16 \u00f3\13 \3 \3 \3 \3!\6!\u00f9\n"+
		"!\r!\16!\u00fa\3\"\6\"\u00fe\n\"\r\"\16\"\u00ff\3\"\3\"\3#\3#\3#\3#\7"+
		"#\u0108\n#\f#\16#\u010b\13#\3#\3#\3#\3#\3#\3$\3$\3$\3$\7$\u0116\n$\f$"+
		"\16$\u0119\13$\3$\3$\3%\3%\3%\6%\u0120\n%\r%\16%\u0121\3%\3%\6%\u0126"+
		"\n%\r%\16%\u0127\3%\3%\6%\u012c\n%\r%\16%\u012d\5%\u0130\n%\3&\3&\3\'"+
		"\3\'\3(\3(\3(\3(\3(\3(\3(\5(\u013d\n(\3(\3(\3)\3)\7)\u0143\n)\f)\16)\u0146"+
		"\13)\3)\3)\3*\7*\u014b\n*\f*\16*\u014e\13*\3*\3*\5\u00f1\u0109\u0144\2"+
		"+\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30\r\32\16\34\17\36\20"+
		" \21\"\22$\23&\24(\25*\26,\27.\30\60\31\62\32\64\33\66\348\35:\36<\37"+
		"> @!B\"D#F$H%J&L\2N\2P\'R(T)\4\2\3\t\4\2>>@@\5\2\13\f\17\17\"\"\4\2\f"+
		"\f\17\17\3\2\63;\6\2&&C\\aac|\3\2\62;\4\2\f\f\"\"\2\u0163\2\4\3\2\2\2"+
		"\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2"+
		"\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32\3\2\2\2"+
		"\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\2&\3\2\2"+
		"\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2\2\2\62\3\2\2"+
		"\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\2<\3\2\2\2\2>\3\2\2"+
		"\2\2@\3\2\2\2\2B\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\2H\3\2\2\2\2J\3\2\2\2\3"+
		"P\3\2\2\2\3R\3\2\2\2\3T\3\2\2\2\4V\3\2\2\2\6[\3\2\2\2\b`\3\2\2\2\nf\3"+
		"\2\2\2\fk\3\2\2\2\16s\3\2\2\2\20~\3\2\2\2\22\u0082\3\2\2\2\24\u0087\3"+
		"\2\2\2\26\u008e\3\2\2\2\30\u0096\3\2\2\2\32\u00a2\3\2\2\2\34\u00a8\3\2"+
		"\2\2\36\u00aa\3\2\2\2 \u00ae\3\2\2\2\"\u00b3\3\2\2\2$\u00b8\3\2\2\2&\u00ba"+
		"\3\2\2\2(\u00bc\3\2\2\2*\u00be\3\2\2\2,\u00c0\3\2\2\2.\u00c2\3\2\2\2\60"+
		"\u00c4\3\2\2\2\62\u00c6\3\2\2\2\64\u00c8\3\2\2\2\66\u00ca\3\2\2\28\u00cc"+
		"\3\2\2\2:\u00d7\3\2\2\2<\u00e1\3\2\2\2>\u00e3\3\2\2\2@\u00eb\3\2\2\2B"+
		"\u00f8\3\2\2\2D\u00fd\3\2\2\2F\u0103\3\2\2\2H\u0111\3\2\2\2J\u012f\3\2"+
		"\2\2L\u0131\3\2\2\2N\u0133\3\2\2\2P\u013c\3\2\2\2R\u0140\3\2\2\2T\u014c"+
		"\3\2\2\2VW\7p\2\2WX\7w\2\2XY\7n\2\2YZ\7n\2\2Z\5\3\2\2\2[\\\7v\2\2\\]\7"+
		"t\2\2]^\7w\2\2^_\7g\2\2_\7\3\2\2\2`a\7h\2\2ab\7c\2\2bc\7n\2\2cd\7u\2\2"+
		"de\7g\2\2e\t\3\2\2\2fg\7t\2\2gh\7w\2\2hi\7n\2\2ij\7g\2\2j\13\3\2\2\2k"+
		"l\7f\2\2lm\7g\2\2mn\7e\2\2no\7n\2\2op\7c\2\2pq\7t\2\2qr\7g\2\2r\r\3\2"+
		"\2\2st\7f\2\2tu\7c\2\2uv\7v\2\2vw\7c\2\2wx\7u\2\2xy\7q\2\2yz\7w\2\2z{"+
		"\7t\2\2{|\7e\2\2|}\7g\2\2}\17\3\2\2\2~\177\7g\2\2\177\u0080\7p\2\2\u0080"+
		"\u0081\7f\2\2\u0081\21\3\2\2\2\u0082\u0083\7y\2\2\u0083\u0084\7j\2\2\u0084"+
		"\u0085\7g\2\2\u0085\u0086\7p\2\2\u0086\23\3\2\2\2\u0087\u0088\7v\2\2\u0088"+
		"\u0089\7j\2\2\u0089\u008a\7g\2\2\u008a\u008b\7p\2\2\u008b\u008c\3\2\2"+
		"\2\u008c\u008d\b\n\2\2\u008d\25\3\2\2\2\u008e\u008f\7r\2\2\u008f\u0090"+
		"\7c\2\2\u0090\u0091\7e\2\2\u0091\u0092\7m\2\2\u0092\u0093\7c\2\2\u0093"+
		"\u0094\7i\2\2\u0094\u0095\7g\2\2\u0095\27\3\2\2\2\u0096\u0097\7k\2\2\u0097"+
		"\u0098\7o\2\2\u0098\u0099\7r\2\2\u0099\u009a\7q\2\2\u009a\u009b\7t\2\2"+
		"\u009b\u009c\7v\2\2\u009c\31\3\2\2\2\u009d\u009e\7(\2\2\u009e\u00a3\7"+
		"(\2\2\u009f\u00a0\7c\2\2\u00a0\u00a1\7p\2\2\u00a1\u00a3\7f\2\2\u00a2\u009d"+
		"\3\2\2\2\u00a2\u009f\3\2\2\2\u00a3\33\3\2\2\2\u00a4\u00a5\7~\2\2\u00a5"+
		"\u00a9\7~\2\2\u00a6\u00a7\7q\2\2\u00a7\u00a9\7t\2\2\u00a8\u00a4\3\2\2"+
		"\2\u00a8\u00a6\3\2\2\2\u00a9\35\3\2\2\2\u00aa\u00ab\7f\2\2\u00ab\u00ac"+
		"\7t\2\2\u00ac\u00ad\7n\2\2\u00ad\37\3\2\2\2\u00ae\u00af\7\60\2\2\u00af"+
		"\u00b0\7f\2\2\u00b0\u00b1\7t\2\2\u00b1\u00b2\7n\2\2\u00b2!\3\2\2\2\u00b3"+
		"\u00b4\7h\2\2\u00b4\u00b5\7t\2\2\u00b5\u00b6\7q\2\2\u00b6\u00b7\7o\2\2"+
		"\u00b7#\3\2\2\2\u00b8\u00b9\7=\2\2\u00b9%\3\2\2\2\u00ba\u00bb\7\60\2\2"+
		"\u00bb\'\3\2\2\2\u00bc\u00bd\7,\2\2\u00bd)\3\2\2\2\u00be\u00bf\7B\2\2"+
		"\u00bf+\3\2\2\2\u00c0\u00c1\7*\2\2\u00c1-\3\2\2\2\u00c2\u00c3\7+\2\2\u00c3"+
		"/\3\2\2\2\u00c4\u00c5\7]\2\2\u00c5\61\3\2\2\2\u00c6\u00c7\7_\2\2\u00c7"+
		"\63\3\2\2\2\u00c8\u00c9\7<\2\2\u00c9\65\3\2\2\2\u00ca\u00cb\7.\2\2\u00cb"+
		"\67\3\2\2\2\u00cc\u00cd\7?\2\2\u00cd9\3\2\2\2\u00ce\u00cf\7?\2\2\u00cf"+
		"\u00d8\7?\2\2\u00d0\u00d1\7@\2\2\u00d1\u00d8\7?\2\2\u00d2\u00d3\7>\2\2"+
		"\u00d3\u00d8\7?\2\2\u00d4\u00d5\7#\2\2\u00d5\u00d8\7?\2\2\u00d6\u00d8"+
		"\t\2\2\2\u00d7\u00ce\3\2\2\2\u00d7\u00d0\3\2\2\2\u00d7\u00d2\3\2\2\2\u00d7"+
		"\u00d4\3\2\2\2\u00d7\u00d6\3\2\2\2\u00d8;\3\2\2\2\u00d9\u00da\7k\2\2\u00da"+
		"\u00e2\7p\2\2\u00db\u00dc\7p\2\2\u00dc\u00dd\7q\2\2\u00dd\u00de\7v\2\2"+
		"\u00de\u00df\7\"\2\2\u00df\u00e0\7k\2\2\u00e0\u00e2\7p\2\2\u00e1\u00d9"+
		"\3\2\2\2\u00e1\u00db\3\2\2\2\u00e2=\3\2\2\2\u00e3\u00e8\5L&\2\u00e4\u00e7"+
		"\5L&\2\u00e5\u00e7\5N\'\2\u00e6\u00e4\3\2\2\2\u00e6\u00e5\3\2\2\2\u00e7"+
		"\u00ea\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9?\3\2\2\2"+
		"\u00ea\u00e8\3\2\2\2\u00eb\u00f1\7$\2\2\u00ec\u00ed\7^\2\2\u00ed\u00f0"+
		"\7$\2\2\u00ee\u00f0\13\2\2\2\u00ef\u00ec\3\2\2\2\u00ef\u00ee\3\2\2\2\u00f0"+
		"\u00f3\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f4\3\2"+
		"\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f5\7$\2\2\u00f5\u00f6\b \3\2\u00f6A"+
		"\3\2\2\2\u00f7\u00f9\5N\'\2\u00f8\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa"+
		"\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fbC\3\2\2\2\u00fc\u00fe\t\3\2\2"+
		"\u00fd\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u0100"+
		"\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0102\b\"\4\2\u0102E\3\2\2\2\u0103"+
		"\u0104\7\61\2\2\u0104\u0105\7,\2\2\u0105\u0109\3\2\2\2\u0106\u0108\13"+
		"\2\2\2\u0107\u0106\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u010a\3\2\2\2\u0109"+
		"\u0107\3\2\2\2\u010a\u010c\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010d\7,"+
		"\2\2\u010d\u010e\7\61\2\2\u010e\u010f\3\2\2\2\u010f\u0110\b#\4\2\u0110"+
		"G\3\2\2\2\u0111\u0112\7\61\2\2\u0112\u0113\7\61\2\2\u0113\u0117\3\2\2"+
		"\2\u0114\u0116\n\4\2\2\u0115\u0114\3\2\2\2\u0116\u0119\3\2\2\2\u0117\u0115"+
		"\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u011a\3\2\2\2\u0119\u0117\3\2\2\2\u011a"+
		"\u011b\b$\4\2\u011bI\3\2\2\2\u011c\u011d\5N\'\2\u011d\u011f\7\60\2\2\u011e"+
		"\u0120\5N\'\2\u011f\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u011f\3\2"+
		"\2\2\u0121\u0122\3\2\2\2\u0122\u0130\3\2\2\2\u0123\u0125\t\5\2\2\u0124"+
		"\u0126\5N\'\2\u0125\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0125\3\2"+
		"\2\2\u0127\u0128\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012b\7\60\2\2\u012a"+
		"\u012c\5N\'\2\u012b\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012b\3\2"+
		"\2\2\u012d\u012e\3\2\2\2\u012e\u0130\3\2\2\2\u012f\u011c\3\2\2\2\u012f"+
		"\u0123\3\2\2\2\u0130K\3\2\2\2\u0131\u0132\t\6\2\2\u0132M\3\2\2\2\u0133"+
		"\u0134\t\7\2\2\u0134O\3\2\2\2\u0135\u0136\7g\2\2\u0136\u0137\7p\2\2\u0137"+
		"\u013d\7f\2\2\u0138\u0139\7g\2\2\u0139\u013a\7p\2\2\u013a\u013b\7f\2\2"+
		"\u013b\u013d\7\f\2\2\u013c\u0135\3\2\2\2\u013c\u0138\3\2\2\2\u013d\u013e"+
		"\3\2\2\2\u013e\u013f\b(\5\2\u013fQ\3\2\2\2\u0140\u0144\n\b\2\2\u0141\u0143"+
		"\13\2\2\2\u0142\u0141\3\2\2\2\u0143\u0146\3\2\2\2\u0144\u0145\3\2\2\2"+
		"\u0144\u0142\3\2\2\2\u0145\u0147\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u0148"+
		"\7\f\2\2\u0148S\3\2\2\2\u0149\u014b\t\b\2\2\u014a\u0149\3\2\2\2\u014b"+
		"\u014e\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014f\3\2"+
		"\2\2\u014e\u014c\3\2\2\2\u014f\u0150\b*\4\2\u0150U\3\2\2\2\27\2\3\u00a2"+
		"\u00a8\u00d7\u00e1\u00e6\u00e8\u00ef\u00f1\u00fa\u00ff\u0109\u0117\u0121"+
		"\u0127\u012d\u012f\u013c\u0144\u014c\6\4\3\2\3 \2\b\2\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}