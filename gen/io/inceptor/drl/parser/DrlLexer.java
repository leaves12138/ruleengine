// Generated from /home/transwarp/gitlab/inceptor-drools/src/main/java/io/inceptor/drl/parser/DrlLexer.g4 by ANTLR 4.9.1
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
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NULL=1, TRUE=2, FALSE=3, RULE=4, DECLARE=5, DATASOURCE=6, END=7, WHEN=8, 
		THEN=9, GLOBAL=10, PACKAGE=11, IMPORT=12, EXISTS=13, AndAnd=14, OrOr=15, 
		DRL=16, DOTDRL=17, FROM=18, LIMIT=19, SEP=20, DOT=21, STAR=22, AT=23, 
		LeftParen=24, RightParen=25, LeftBracket=26, RightBracket=27, Colon=28, 
		Comma=29, Equal=30, COMPARE=31, CONTAIN=32, Identifier=33, STRING=34, 
		NUMBER=35, WS=36, BlockComment=37, LineComment=38, FloatingPointLiteral=39, 
		ENDMVEL=40, LINE=41, WS2=42;
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
			"THEN", "GLOBAL", "PACKAGE", "IMPORT", "EXISTS", "AndAnd", "OrOr", "DRL", 
			"DOTDRL", "FROM", "LIMIT", "SEP", "DOT", "STAR", "AT", "LeftParen", "RightParen", 
			"LeftBracket", "RightBracket", "Colon", "Comma", "Equal", "COMPARE", 
			"CONTAIN", "Identifier", "STRING", "NUMBER", "WS", "BlockComment", "LineComment", 
			"FloatingPointLiteral", "Letter", "Digit", "ENDMVEL", "LINE", "WS2"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'null'", "'true'", "'false'", "'rule'", "'declare'", "'datasource'", 
			"'end'", "'when'", "'then'", "'global'", "'package'", "'import'", "'exists'", 
			null, null, "'drl'", "'.drl'", "'from'", "'limit'", "';'", "'.'", "'*'", 
			"'@'", "'('", "')'", "'['", "']'", "':'", "','", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NULL", "TRUE", "FALSE", "RULE", "DECLARE", "DATASOURCE", "END", 
			"WHEN", "THEN", "GLOBAL", "PACKAGE", "IMPORT", "EXISTS", "AndAnd", "OrOr", 
			"DRL", "DOTDRL", "FROM", "LIMIT", "SEP", "DOT", "STAR", "AT", "LeftParen", 
			"RightParen", "LeftBracket", "RightBracket", "Colon", "Comma", "Equal", 
			"COMPARE", "CONTAIN", "Identifier", "STRING", "NUMBER", "WS", "BlockComment", 
			"LineComment", "FloatingPointLiteral", "ENDMVEL", "LINE", "WS2"
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
		case 33:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			setText(getText().substring(1,getText().length()-1).replace("\\\"","\""));
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2,\u016d\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\5\17\u00b9\n\17"+
		"\3\20\3\20\3\20\3\20\5\20\u00bf\n\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34"+
		"\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u00f4"+
		"\n \3!\3!\3!\3!\3!\3!\3!\3!\5!\u00fe\n!\3\"\3\"\3\"\7\"\u0103\n\"\f\""+
		"\16\"\u0106\13\"\3#\3#\3#\3#\7#\u010c\n#\f#\16#\u010f\13#\3#\3#\3#\3$"+
		"\6$\u0115\n$\r$\16$\u0116\3%\6%\u011a\n%\r%\16%\u011b\3%\3%\3&\3&\3&\3"+
		"&\7&\u0124\n&\f&\16&\u0127\13&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\7\'\u0132"+
		"\n\'\f\'\16\'\u0135\13\'\3\'\3\'\3(\3(\3(\6(\u013c\n(\r(\16(\u013d\3("+
		"\3(\6(\u0142\n(\r(\16(\u0143\3(\3(\6(\u0148\n(\r(\16(\u0149\5(\u014c\n"+
		"(\3)\3)\3*\3*\3+\3+\3+\3+\3+\3+\3+\5+\u0159\n+\3+\3+\3,\3,\7,\u015f\n"+
		",\f,\16,\u0162\13,\3,\3,\3-\7-\u0167\n-\f-\16-\u016a\13-\3-\3-\5\u010d"+
		"\u0125\u0160\2.\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30\r\32"+
		"\16\34\17\36\20 \21\"\22$\23&\24(\25*\26,\27.\30\60\31\62\32\64\33\66"+
		"\348\35:\36<\37> @!B\"D#F$H%J&L\'N(P)R\2T\2V*X+Z,\4\2\3\t\4\2>>@@\5\2"+
		"\13\f\17\17\"\"\4\2\f\f\17\17\3\2\63;\6\2&&C\\aac|\3\2\62;\4\2\f\f\"\""+
		"\2\u017f\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2"+
		"\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3"+
		"\2\2\2\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2"+
		"$\3\2\2\2\2&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60"+
		"\3\2\2\2\2\62\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2"+
		"\2<\3\2\2\2\2>\3\2\2\2\2@\3\2\2\2\2B\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\2H"+
		"\3\2\2\2\2J\3\2\2\2\2L\3\2\2\2\2N\3\2\2\2\2P\3\2\2\2\3V\3\2\2\2\3X\3\2"+
		"\2\2\3Z\3\2\2\2\4\\\3\2\2\2\6a\3\2\2\2\bf\3\2\2\2\nl\3\2\2\2\fq\3\2\2"+
		"\2\16y\3\2\2\2\20\u0084\3\2\2\2\22\u0088\3\2\2\2\24\u008d\3\2\2\2\26\u0094"+
		"\3\2\2\2\30\u009d\3\2\2\2\32\u00a5\3\2\2\2\34\u00ac\3\2\2\2\36\u00b8\3"+
		"\2\2\2 \u00be\3\2\2\2\"\u00c0\3\2\2\2$\u00c4\3\2\2\2&\u00c9\3\2\2\2(\u00ce"+
		"\3\2\2\2*\u00d4\3\2\2\2,\u00d6\3\2\2\2.\u00d8\3\2\2\2\60\u00da\3\2\2\2"+
		"\62\u00dc\3\2\2\2\64\u00de\3\2\2\2\66\u00e0\3\2\2\28\u00e2\3\2\2\2:\u00e4"+
		"\3\2\2\2<\u00e6\3\2\2\2>\u00e8\3\2\2\2@\u00f3\3\2\2\2B\u00fd\3\2\2\2D"+
		"\u00ff\3\2\2\2F\u0107\3\2\2\2H\u0114\3\2\2\2J\u0119\3\2\2\2L\u011f\3\2"+
		"\2\2N\u012d\3\2\2\2P\u014b\3\2\2\2R\u014d\3\2\2\2T\u014f\3\2\2\2V\u0158"+
		"\3\2\2\2X\u015c\3\2\2\2Z\u0168\3\2\2\2\\]\7p\2\2]^\7w\2\2^_\7n\2\2_`\7"+
		"n\2\2`\5\3\2\2\2ab\7v\2\2bc\7t\2\2cd\7w\2\2de\7g\2\2e\7\3\2\2\2fg\7h\2"+
		"\2gh\7c\2\2hi\7n\2\2ij\7u\2\2jk\7g\2\2k\t\3\2\2\2lm\7t\2\2mn\7w\2\2no"+
		"\7n\2\2op\7g\2\2p\13\3\2\2\2qr\7f\2\2rs\7g\2\2st\7e\2\2tu\7n\2\2uv\7c"+
		"\2\2vw\7t\2\2wx\7g\2\2x\r\3\2\2\2yz\7f\2\2z{\7c\2\2{|\7v\2\2|}\7c\2\2"+
		"}~\7u\2\2~\177\7q\2\2\177\u0080\7w\2\2\u0080\u0081\7t\2\2\u0081\u0082"+
		"\7e\2\2\u0082\u0083\7g\2\2\u0083\17\3\2\2\2\u0084\u0085\7g\2\2\u0085\u0086"+
		"\7p\2\2\u0086\u0087\7f\2\2\u0087\21\3\2\2\2\u0088\u0089\7y\2\2\u0089\u008a"+
		"\7j\2\2\u008a\u008b\7g\2\2\u008b\u008c\7p\2\2\u008c\23\3\2\2\2\u008d\u008e"+
		"\7v\2\2\u008e\u008f\7j\2\2\u008f\u0090\7g\2\2\u0090\u0091\7p\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\u0093\b\n\2\2\u0093\25\3\2\2\2\u0094\u0095\7i\2\2"+
		"\u0095\u0096\7n\2\2\u0096\u0097\7q\2\2\u0097\u0098\7d\2\2\u0098\u0099"+
		"\7c\2\2\u0099\u009a\7n\2\2\u009a\u009b\3\2\2\2\u009b\u009c\b\13\2\2\u009c"+
		"\27\3\2\2\2\u009d\u009e\7r\2\2\u009e\u009f\7c\2\2\u009f\u00a0\7e\2\2\u00a0"+
		"\u00a1\7m\2\2\u00a1\u00a2\7c\2\2\u00a2\u00a3\7i\2\2\u00a3\u00a4\7g\2\2"+
		"\u00a4\31\3\2\2\2\u00a5\u00a6\7k\2\2\u00a6\u00a7\7o\2\2\u00a7\u00a8\7"+
		"r\2\2\u00a8\u00a9\7q\2\2\u00a9\u00aa\7t\2\2\u00aa\u00ab\7v\2\2\u00ab\33"+
		"\3\2\2\2\u00ac\u00ad\7g\2\2\u00ad\u00ae\7z\2\2\u00ae\u00af\7k\2\2\u00af"+
		"\u00b0\7u\2\2\u00b0\u00b1\7v\2\2\u00b1\u00b2\7u\2\2\u00b2\35\3\2\2\2\u00b3"+
		"\u00b4\7(\2\2\u00b4\u00b9\7(\2\2\u00b5\u00b6\7c\2\2\u00b6\u00b7\7p\2\2"+
		"\u00b7\u00b9\7f\2\2\u00b8\u00b3\3\2\2\2\u00b8\u00b5\3\2\2\2\u00b9\37\3"+
		"\2\2\2\u00ba\u00bb\7~\2\2\u00bb\u00bf\7~\2\2\u00bc\u00bd\7q\2\2\u00bd"+
		"\u00bf\7t\2\2\u00be\u00ba\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf!\3\2\2\2\u00c0"+
		"\u00c1\7f\2\2\u00c1\u00c2\7t\2\2\u00c2\u00c3\7n\2\2\u00c3#\3\2\2\2\u00c4"+
		"\u00c5\7\60\2\2\u00c5\u00c6\7f\2\2\u00c6\u00c7\7t\2\2\u00c7\u00c8\7n\2"+
		"\2\u00c8%\3\2\2\2\u00c9\u00ca\7h\2\2\u00ca\u00cb\7t\2\2\u00cb\u00cc\7"+
		"q\2\2\u00cc\u00cd\7o\2\2\u00cd\'\3\2\2\2\u00ce\u00cf\7n\2\2\u00cf\u00d0"+
		"\7k\2\2\u00d0\u00d1\7o\2\2\u00d1\u00d2\7k\2\2\u00d2\u00d3\7v\2\2\u00d3"+
		")\3\2\2\2\u00d4\u00d5\7=\2\2\u00d5+\3\2\2\2\u00d6\u00d7\7\60\2\2\u00d7"+
		"-\3\2\2\2\u00d8\u00d9\7,\2\2\u00d9/\3\2\2\2\u00da\u00db\7B\2\2\u00db\61"+
		"\3\2\2\2\u00dc\u00dd\7*\2\2\u00dd\63\3\2\2\2\u00de\u00df\7+\2\2\u00df"+
		"\65\3\2\2\2\u00e0\u00e1\7]\2\2\u00e1\67\3\2\2\2\u00e2\u00e3\7_\2\2\u00e3"+
		"9\3\2\2\2\u00e4\u00e5\7<\2\2\u00e5;\3\2\2\2\u00e6\u00e7\7.\2\2\u00e7="+
		"\3\2\2\2\u00e8\u00e9\7?\2\2\u00e9?\3\2\2\2\u00ea\u00eb\7?\2\2\u00eb\u00f4"+
		"\7?\2\2\u00ec\u00ed\7@\2\2\u00ed\u00f4\7?\2\2\u00ee\u00ef\7>\2\2\u00ef"+
		"\u00f4\7?\2\2\u00f0\u00f1\7#\2\2\u00f1\u00f4\7?\2\2\u00f2\u00f4\t\2\2"+
		"\2\u00f3\u00ea\3\2\2\2\u00f3\u00ec\3\2\2\2\u00f3\u00ee\3\2\2\2\u00f3\u00f0"+
		"\3\2\2\2\u00f3\u00f2\3\2\2\2\u00f4A\3\2\2\2\u00f5\u00f6\7k\2\2\u00f6\u00fe"+
		"\7p\2\2\u00f7\u00f8\7p\2\2\u00f8\u00f9\7q\2\2\u00f9\u00fa\7v\2\2\u00fa"+
		"\u00fb\7\"\2\2\u00fb\u00fc\7k\2\2\u00fc\u00fe\7p\2\2\u00fd\u00f5\3\2\2"+
		"\2\u00fd\u00f7\3\2\2\2\u00feC\3\2\2\2\u00ff\u0104\5R)\2\u0100\u0103\5"+
		"R)\2\u0101\u0103\5T*\2\u0102\u0100\3\2\2\2\u0102\u0101\3\2\2\2\u0103\u0106"+
		"\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105E\3\2\2\2\u0106"+
		"\u0104\3\2\2\2\u0107\u010d\7$\2\2\u0108\u0109\7^\2\2\u0109\u010c\7$\2"+
		"\2\u010a\u010c\13\2\2\2\u010b\u0108\3\2\2\2\u010b\u010a\3\2\2\2\u010c"+
		"\u010f\3\2\2\2\u010d\u010e\3\2\2\2\u010d\u010b\3\2\2\2\u010e\u0110\3\2"+
		"\2\2\u010f\u010d\3\2\2\2\u0110\u0111\7$\2\2\u0111\u0112\b#\3\2\u0112G"+
		"\3\2\2\2\u0113\u0115\5T*\2\u0114\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116"+
		"\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117I\3\2\2\2\u0118\u011a\t\3\2\2"+
		"\u0119\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c"+
		"\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011e\b%\4\2\u011eK\3\2\2\2\u011f\u0120"+
		"\7\61\2\2\u0120\u0121\7,\2\2\u0121\u0125\3\2\2\2\u0122\u0124\13\2\2\2"+
		"\u0123\u0122\3\2\2\2\u0124\u0127\3\2\2\2\u0125\u0126\3\2\2\2\u0125\u0123"+
		"\3\2\2\2\u0126\u0128\3\2\2\2\u0127\u0125\3\2\2\2\u0128\u0129\7,\2\2\u0129"+
		"\u012a\7\61\2\2\u012a\u012b\3\2\2\2\u012b\u012c\b&\4\2\u012cM\3\2\2\2"+
		"\u012d\u012e\7\61\2\2\u012e\u012f\7\61\2\2\u012f\u0133\3\2\2\2\u0130\u0132"+
		"\n\4\2\2\u0131\u0130\3\2\2\2\u0132\u0135\3\2\2\2\u0133\u0131\3\2\2\2\u0133"+
		"\u0134\3\2\2\2\u0134\u0136\3\2\2\2\u0135\u0133\3\2\2\2\u0136\u0137\b\'"+
		"\4\2\u0137O\3\2\2\2\u0138\u0139\5T*\2\u0139\u013b\7\60\2\2\u013a\u013c"+
		"\5T*\2\u013b\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013b\3\2\2\2\u013d"+
		"\u013e\3\2\2\2\u013e\u014c\3\2\2\2\u013f\u0141\t\5\2\2\u0140\u0142\5T"+
		"*\2\u0141\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0141\3\2\2\2\u0143"+
		"\u0144\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0147\7\60\2\2\u0146\u0148\5"+
		"T*\2\u0147\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u0147\3\2\2\2\u0149"+
		"\u014a\3\2\2\2\u014a\u014c\3\2\2\2\u014b\u0138\3\2\2\2\u014b\u013f\3\2"+
		"\2\2\u014cQ\3\2\2\2\u014d\u014e\t\6\2\2\u014eS\3\2\2\2\u014f\u0150\t\7"+
		"\2\2\u0150U\3\2\2\2\u0151\u0152\7g\2\2\u0152\u0153\7p\2\2\u0153\u0159"+
		"\7f\2\2\u0154\u0155\7g\2\2\u0155\u0156\7p\2\2\u0156\u0157\7f\2\2\u0157"+
		"\u0159\7\f\2\2\u0158\u0151\3\2\2\2\u0158\u0154\3\2\2\2\u0159\u015a\3\2"+
		"\2\2\u015a\u015b\b+\5\2\u015bW\3\2\2\2\u015c\u0160\n\b\2\2\u015d\u015f"+
		"\13\2\2\2\u015e\u015d\3\2\2\2\u015f\u0162\3\2\2\2\u0160\u0161\3\2\2\2"+
		"\u0160\u015e\3\2\2\2\u0161\u0163\3\2\2\2\u0162\u0160\3\2\2\2\u0163\u0164"+
		"\7\f\2\2\u0164Y\3\2\2\2\u0165\u0167\t\b\2\2\u0166\u0165\3\2\2\2\u0167"+
		"\u016a\3\2\2\2\u0168\u0166\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u016b\3\2"+
		"\2\2\u016a\u0168\3\2\2\2\u016b\u016c\b-\4\2\u016c[\3\2\2\2\27\2\3\u00b8"+
		"\u00be\u00f3\u00fd\u0102\u0104\u010b\u010d\u0116\u011b\u0125\u0133\u013d"+
		"\u0143\u0149\u014b\u0158\u0160\u0168\6\4\3\2\3#\2\b\2\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}