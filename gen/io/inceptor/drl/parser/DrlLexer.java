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
		THEN=9, PACKAGE=10, IMPORT=11, EXISTS=12, AndAnd=13, OrOr=14, DRL=15, 
		DOTDRL=16, FROM=17, SEP=18, DOT=19, STAR=20, AT=21, LeftParen=22, RightParen=23, 
		LeftBracket=24, RightBracket=25, Colon=26, Comma=27, Equal=28, COMPARE=29, 
		CONTAIN=30, Identifier=31, STRING=32, NUMBER=33, WS=34, BlockComment=35, 
		LineComment=36, FloatingPointLiteral=37, ENDMVEL=38, LINE=39, WS2=40;
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
			"THEN", "PACKAGE", "IMPORT", "EXISTS", "AndAnd", "OrOr", "DRL", "DOTDRL", 
			"FROM", "SEP", "DOT", "STAR", "AT", "LeftParen", "RightParen", "LeftBracket", 
			"RightBracket", "Colon", "Comma", "Equal", "COMPARE", "CONTAIN", "Identifier", 
			"STRING", "NUMBER", "WS", "BlockComment", "LineComment", "FloatingPointLiteral", 
			"Letter", "Digit", "ENDMVEL", "LINE", "WS2"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'null'", "'true'", "'false'", "'rule'", "'declare'", "'datasource'", 
			"'end'", "'when'", "'then'", "'package'", "'import'", "'exists'", null, 
			null, "'drl'", "'.drl'", "'from'", "';'", "'.'", "'*'", "'@'", "'('", 
			"')'", "'['", "']'", "':'", "','", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NULL", "TRUE", "FALSE", "RULE", "DECLARE", "DATASOURCE", "END", 
			"WHEN", "THEN", "PACKAGE", "IMPORT", "EXISTS", "AndAnd", "OrOr", "DRL", 
			"DOTDRL", "FROM", "SEP", "DOT", "STAR", "AT", "LeftParen", "RightParen", 
			"LeftBracket", "RightBracket", "Colon", "Comma", "Equal", "COMPARE", 
			"CONTAIN", "Identifier", "STRING", "NUMBER", "WS", "BlockComment", "LineComment", 
			"FloatingPointLiteral", "ENDMVEL", "LINE", "WS2"
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
		case 31:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2*\u015a\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\5"+
		"\16\u00ac\n\16\3\17\3\17\3\17\3\17\5\17\u00b2\n\17\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33"+
		"\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36"+
		"\u00e1\n\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u00eb\n\37\3"+
		" \3 \3 \7 \u00f0\n \f \16 \u00f3\13 \3!\3!\3!\3!\7!\u00f9\n!\f!\16!\u00fc"+
		"\13!\3!\3!\3!\3\"\6\"\u0102\n\"\r\"\16\"\u0103\3#\6#\u0107\n#\r#\16#\u0108"+
		"\3#\3#\3$\3$\3$\3$\7$\u0111\n$\f$\16$\u0114\13$\3$\3$\3$\3$\3$\3%\3%\3"+
		"%\3%\7%\u011f\n%\f%\16%\u0122\13%\3%\3%\3&\3&\3&\6&\u0129\n&\r&\16&\u012a"+
		"\3&\3&\6&\u012f\n&\r&\16&\u0130\3&\3&\6&\u0135\n&\r&\16&\u0136\5&\u0139"+
		"\n&\3\'\3\'\3(\3(\3)\3)\3)\3)\3)\3)\3)\5)\u0146\n)\3)\3)\3*\3*\7*\u014c"+
		"\n*\f*\16*\u014f\13*\3*\3*\3+\7+\u0154\n+\f+\16+\u0157\13+\3+\3+\5\u00fa"+
		"\u0112\u014d\2,\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30\r\32"+
		"\16\34\17\36\20 \21\"\22$\23&\24(\25*\26,\27.\30\60\31\62\32\64\33\66"+
		"\348\35:\36<\37> @!B\"D#F$H%J&L\'N\2P\2R(T)V*\4\2\3\t\4\2>>@@\5\2\13\f"+
		"\17\17\"\"\4\2\f\f\17\17\3\2\63;\6\2&&C\\aac|\3\2\62;\4\2\f\f\"\"\2\u016c"+
		"\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2"+
		"\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2"+
		"\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2"+
		"\2\2\2&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2"+
		"\2\2\62\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\2<\3\2"+
		"\2\2\2>\3\2\2\2\2@\3\2\2\2\2B\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\2H\3\2\2\2"+
		"\2J\3\2\2\2\2L\3\2\2\2\3R\3\2\2\2\3T\3\2\2\2\3V\3\2\2\2\4X\3\2\2\2\6]"+
		"\3\2\2\2\bb\3\2\2\2\nh\3\2\2\2\fm\3\2\2\2\16u\3\2\2\2\20\u0080\3\2\2\2"+
		"\22\u0084\3\2\2\2\24\u0089\3\2\2\2\26\u0090\3\2\2\2\30\u0098\3\2\2\2\32"+
		"\u009f\3\2\2\2\34\u00ab\3\2\2\2\36\u00b1\3\2\2\2 \u00b3\3\2\2\2\"\u00b7"+
		"\3\2\2\2$\u00bc\3\2\2\2&\u00c1\3\2\2\2(\u00c3\3\2\2\2*\u00c5\3\2\2\2,"+
		"\u00c7\3\2\2\2.\u00c9\3\2\2\2\60\u00cb\3\2\2\2\62\u00cd\3\2\2\2\64\u00cf"+
		"\3\2\2\2\66\u00d1\3\2\2\28\u00d3\3\2\2\2:\u00d5\3\2\2\2<\u00e0\3\2\2\2"+
		">\u00ea\3\2\2\2@\u00ec\3\2\2\2B\u00f4\3\2\2\2D\u0101\3\2\2\2F\u0106\3"+
		"\2\2\2H\u010c\3\2\2\2J\u011a\3\2\2\2L\u0138\3\2\2\2N\u013a\3\2\2\2P\u013c"+
		"\3\2\2\2R\u0145\3\2\2\2T\u0149\3\2\2\2V\u0155\3\2\2\2XY\7p\2\2YZ\7w\2"+
		"\2Z[\7n\2\2[\\\7n\2\2\\\5\3\2\2\2]^\7v\2\2^_\7t\2\2_`\7w\2\2`a\7g\2\2"+
		"a\7\3\2\2\2bc\7h\2\2cd\7c\2\2de\7n\2\2ef\7u\2\2fg\7g\2\2g\t\3\2\2\2hi"+
		"\7t\2\2ij\7w\2\2jk\7n\2\2kl\7g\2\2l\13\3\2\2\2mn\7f\2\2no\7g\2\2op\7e"+
		"\2\2pq\7n\2\2qr\7c\2\2rs\7t\2\2st\7g\2\2t\r\3\2\2\2uv\7f\2\2vw\7c\2\2"+
		"wx\7v\2\2xy\7c\2\2yz\7u\2\2z{\7q\2\2{|\7w\2\2|}\7t\2\2}~\7e\2\2~\177\7"+
		"g\2\2\177\17\3\2\2\2\u0080\u0081\7g\2\2\u0081\u0082\7p\2\2\u0082\u0083"+
		"\7f\2\2\u0083\21\3\2\2\2\u0084\u0085\7y\2\2\u0085\u0086\7j\2\2\u0086\u0087"+
		"\7g\2\2\u0087\u0088\7p\2\2\u0088\23\3\2\2\2\u0089\u008a\7v\2\2\u008a\u008b"+
		"\7j\2\2\u008b\u008c\7g\2\2\u008c\u008d\7p\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u008f\b\n\2\2\u008f\25\3\2\2\2\u0090\u0091\7r\2\2\u0091\u0092\7c\2\2"+
		"\u0092\u0093\7e\2\2\u0093\u0094\7m\2\2\u0094\u0095\7c\2\2\u0095\u0096"+
		"\7i\2\2\u0096\u0097\7g\2\2\u0097\27\3\2\2\2\u0098\u0099\7k\2\2\u0099\u009a"+
		"\7o\2\2\u009a\u009b\7r\2\2\u009b\u009c\7q\2\2\u009c\u009d\7t\2\2\u009d"+
		"\u009e\7v\2\2\u009e\31\3\2\2\2\u009f\u00a0\7g\2\2\u00a0\u00a1\7z\2\2\u00a1"+
		"\u00a2\7k\2\2\u00a2\u00a3\7u\2\2\u00a3\u00a4\7v\2\2\u00a4\u00a5\7u\2\2"+
		"\u00a5\33\3\2\2\2\u00a6\u00a7\7(\2\2\u00a7\u00ac\7(\2\2\u00a8\u00a9\7"+
		"c\2\2\u00a9\u00aa\7p\2\2\u00aa\u00ac\7f\2\2\u00ab\u00a6\3\2\2\2\u00ab"+
		"\u00a8\3\2\2\2\u00ac\35\3\2\2\2\u00ad\u00ae\7~\2\2\u00ae\u00b2\7~\2\2"+
		"\u00af\u00b0\7q\2\2\u00b0\u00b2\7t\2\2\u00b1\u00ad\3\2\2\2\u00b1\u00af"+
		"\3\2\2\2\u00b2\37\3\2\2\2\u00b3\u00b4\7f\2\2\u00b4\u00b5\7t\2\2\u00b5"+
		"\u00b6\7n\2\2\u00b6!\3\2\2\2\u00b7\u00b8\7\60\2\2\u00b8\u00b9\7f\2\2\u00b9"+
		"\u00ba\7t\2\2\u00ba\u00bb\7n\2\2\u00bb#\3\2\2\2\u00bc\u00bd\7h\2\2\u00bd"+
		"\u00be\7t\2\2\u00be\u00bf\7q\2\2\u00bf\u00c0\7o\2\2\u00c0%\3\2\2\2\u00c1"+
		"\u00c2\7=\2\2\u00c2\'\3\2\2\2\u00c3\u00c4\7\60\2\2\u00c4)\3\2\2\2\u00c5"+
		"\u00c6\7,\2\2\u00c6+\3\2\2\2\u00c7\u00c8\7B\2\2\u00c8-\3\2\2\2\u00c9\u00ca"+
		"\7*\2\2\u00ca/\3\2\2\2\u00cb\u00cc\7+\2\2\u00cc\61\3\2\2\2\u00cd\u00ce"+
		"\7]\2\2\u00ce\63\3\2\2\2\u00cf\u00d0\7_\2\2\u00d0\65\3\2\2\2\u00d1\u00d2"+
		"\7<\2\2\u00d2\67\3\2\2\2\u00d3\u00d4\7.\2\2\u00d49\3\2\2\2\u00d5\u00d6"+
		"\7?\2\2\u00d6;\3\2\2\2\u00d7\u00d8\7?\2\2\u00d8\u00e1\7?\2\2\u00d9\u00da"+
		"\7@\2\2\u00da\u00e1\7?\2\2\u00db\u00dc\7>\2\2\u00dc\u00e1\7?\2\2\u00dd"+
		"\u00de\7#\2\2\u00de\u00e1\7?\2\2\u00df\u00e1\t\2\2\2\u00e0\u00d7\3\2\2"+
		"\2\u00e0\u00d9\3\2\2\2\u00e0\u00db\3\2\2\2\u00e0\u00dd\3\2\2\2\u00e0\u00df"+
		"\3\2\2\2\u00e1=\3\2\2\2\u00e2\u00e3\7k\2\2\u00e3\u00eb\7p\2\2\u00e4\u00e5"+
		"\7p\2\2\u00e5\u00e6\7q\2\2\u00e6\u00e7\7v\2\2\u00e7\u00e8\7\"\2\2\u00e8"+
		"\u00e9\7k\2\2\u00e9\u00eb\7p\2\2\u00ea\u00e2\3\2\2\2\u00ea\u00e4\3\2\2"+
		"\2\u00eb?\3\2\2\2\u00ec\u00f1\5N\'\2\u00ed\u00f0\5N\'\2\u00ee\u00f0\5"+
		"P(\2\u00ef\u00ed\3\2\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1"+
		"\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2A\3\2\2\2\u00f3\u00f1\3\2\2\2"+
		"\u00f4\u00fa\7$\2\2\u00f5\u00f6\7^\2\2\u00f6\u00f9\7$\2\2\u00f7\u00f9"+
		"\13\2\2\2\u00f8\u00f5\3\2\2\2\u00f8\u00f7\3\2\2\2\u00f9\u00fc\3\2\2\2"+
		"\u00fa\u00fb\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00fd\3\2\2\2\u00fc\u00fa"+
		"\3\2\2\2\u00fd\u00fe\7$\2\2\u00fe\u00ff\b!\3\2\u00ffC\3\2\2\2\u0100\u0102"+
		"\5P(\2\u0101\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0101\3\2\2\2\u0103"+
		"\u0104\3\2\2\2\u0104E\3\2\2\2\u0105\u0107\t\3\2\2\u0106\u0105\3\2\2\2"+
		"\u0107\u0108\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010a"+
		"\3\2\2\2\u010a\u010b\b#\4\2\u010bG\3\2\2\2\u010c\u010d\7\61\2\2\u010d"+
		"\u010e\7,\2\2\u010e\u0112\3\2\2\2\u010f\u0111\13\2\2\2\u0110\u010f\3\2"+
		"\2\2\u0111\u0114\3\2\2\2\u0112\u0113\3\2\2\2\u0112\u0110\3\2\2\2\u0113"+
		"\u0115\3\2\2\2\u0114\u0112\3\2\2\2\u0115\u0116\7,\2\2\u0116\u0117\7\61"+
		"\2\2\u0117\u0118\3\2\2\2\u0118\u0119\b$\4\2\u0119I\3\2\2\2\u011a\u011b"+
		"\7\61\2\2\u011b\u011c\7\61\2\2\u011c\u0120\3\2\2\2\u011d\u011f\n\4\2\2"+
		"\u011e\u011d\3\2\2\2\u011f\u0122\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121"+
		"\3\2\2\2\u0121\u0123\3\2\2\2\u0122\u0120\3\2\2\2\u0123\u0124\b%\4\2\u0124"+
		"K\3\2\2\2\u0125\u0126\5P(\2\u0126\u0128\7\60\2\2\u0127\u0129\5P(\2\u0128"+
		"\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2"+
		"\2\2\u012b\u0139\3\2\2\2\u012c\u012e\t\5\2\2\u012d\u012f\5P(\2\u012e\u012d"+
		"\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131"+
		"\u0132\3\2\2\2\u0132\u0134\7\60\2\2\u0133\u0135\5P(\2\u0134\u0133\3\2"+
		"\2\2\u0135\u0136\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137"+
		"\u0139\3\2\2\2\u0138\u0125\3\2\2\2\u0138\u012c\3\2\2\2\u0139M\3\2\2\2"+
		"\u013a\u013b\t\6\2\2\u013bO\3\2\2\2\u013c\u013d\t\7\2\2\u013dQ\3\2\2\2"+
		"\u013e\u013f\7g\2\2\u013f\u0140\7p\2\2\u0140\u0146\7f\2\2\u0141\u0142"+
		"\7g\2\2\u0142\u0143\7p\2\2\u0143\u0144\7f\2\2\u0144\u0146\7\f\2\2\u0145"+
		"\u013e\3\2\2\2\u0145\u0141\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u0148\b)"+
		"\5\2\u0148S\3\2\2\2\u0149\u014d\n\b\2\2\u014a\u014c\13\2\2\2\u014b\u014a"+
		"\3\2\2\2\u014c\u014f\3\2\2\2\u014d\u014e\3\2\2\2\u014d\u014b\3\2\2\2\u014e"+
		"\u0150\3\2\2\2\u014f\u014d\3\2\2\2\u0150\u0151\7\f\2\2\u0151U\3\2\2\2"+
		"\u0152\u0154\t\b\2\2\u0153\u0152\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153"+
		"\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0158\3\2\2\2\u0157\u0155\3\2\2\2\u0158"+
		"\u0159\b+\4\2\u0159W\3\2\2\2\27\2\3\u00ab\u00b1\u00e0\u00ea\u00ef\u00f1"+
		"\u00f8\u00fa\u0103\u0108\u0112\u0120\u012a\u0130\u0136\u0138\u0145\u014d"+
		"\u0155\6\4\3\2\3!\2\b\2\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}