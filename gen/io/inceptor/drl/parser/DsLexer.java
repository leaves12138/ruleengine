// Generated from /home/transwarp/gitlab/inceptor-drools/src/main/java/io/inceptor/drl/parser/DsLexer.g4 by ANTLR 4.9.1
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
public class DsLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DECLARE=1, END=2, URL=3, TRUE=4, FALSE=5, USERNAME=6, PASSWORD=7, TYPE=8, 
		Colon=9, PORT=10, HOST=11, ISCLUSTER=12, CLUSTERIPS=13, Identifier=14, 
		STRING=15, NUMBER=16, WS=17, BlockComment=18, LineComment=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"DECLARE", "END", "URL", "TRUE", "FALSE", "USERNAME", "PASSWORD", "TYPE", 
			"Colon", "PORT", "HOST", "ISCLUSTER", "CLUSTERIPS", "Identifier", "STRING", 
			"NUMBER", "Letter", "Digit", "WS", "BlockComment", "LineComment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'declare'", "'end'", "'url'", "'true'", "'false'", "'username'", 
			"'password'", "'type'", "':'", "'port'", "'host'", "'isCluster'", "'cluster-addresses'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DECLARE", "END", "URL", "TRUE", "FALSE", "USERNAME", "PASSWORD", 
			"TYPE", "Colon", "PORT", "HOST", "ISCLUSTER", "CLUSTERIPS", "Identifier", 
			"STRING", "NUMBER", "WS", "BlockComment", "LineComment"
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


	public DsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DsLexer.g4"; }

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
		case 14:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25\u00c4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\7\17\u008b\n\17\f\17\16\17\u008e\13\17\3\20"+
		"\3\20\3\20\3\20\7\20\u0094\n\20\f\20\16\20\u0097\13\20\3\20\3\20\3\20"+
		"\3\21\6\21\u009d\n\21\r\21\16\21\u009e\3\22\3\22\3\23\3\23\3\24\6\24\u00a6"+
		"\n\24\r\24\16\24\u00a7\3\24\3\24\3\25\3\25\3\25\3\25\7\25\u00b0\n\25\f"+
		"\25\16\25\u00b3\13\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\7\26"+
		"\u00be\n\26\f\26\16\26\u00c1\13\26\3\26\3\26\4\u0095\u00b1\2\27\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\2%\2\'\23)\24+\25\3\2\6\6\2&&C\\aac|\3\2\62;\5\2\13\f\17\17\"\""+
		"\4\2\f\f\17\17\2\u00c9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2\5\65\3\2"+
		"\2\2\79\3\2\2\2\t=\3\2\2\2\13B\3\2\2\2\rH\3\2\2\2\17Q\3\2\2\2\21Z\3\2"+
		"\2\2\23_\3\2\2\2\25a\3\2\2\2\27f\3\2\2\2\31k\3\2\2\2\33u\3\2\2\2\35\u0087"+
		"\3\2\2\2\37\u008f\3\2\2\2!\u009c\3\2\2\2#\u00a0\3\2\2\2%\u00a2\3\2\2\2"+
		"\'\u00a5\3\2\2\2)\u00ab\3\2\2\2+\u00b9\3\2\2\2-.\7f\2\2./\7g\2\2/\60\7"+
		"e\2\2\60\61\7n\2\2\61\62\7c\2\2\62\63\7t\2\2\63\64\7g\2\2\64\4\3\2\2\2"+
		"\65\66\7g\2\2\66\67\7p\2\2\678\7f\2\28\6\3\2\2\29:\7w\2\2:;\7t\2\2;<\7"+
		"n\2\2<\b\3\2\2\2=>\7v\2\2>?\7t\2\2?@\7w\2\2@A\7g\2\2A\n\3\2\2\2BC\7h\2"+
		"\2CD\7c\2\2DE\7n\2\2EF\7u\2\2FG\7g\2\2G\f\3\2\2\2HI\7w\2\2IJ\7u\2\2JK"+
		"\7g\2\2KL\7t\2\2LM\7p\2\2MN\7c\2\2NO\7o\2\2OP\7g\2\2P\16\3\2\2\2QR\7r"+
		"\2\2RS\7c\2\2ST\7u\2\2TU\7u\2\2UV\7y\2\2VW\7q\2\2WX\7t\2\2XY\7f\2\2Y\20"+
		"\3\2\2\2Z[\7v\2\2[\\\7{\2\2\\]\7r\2\2]^\7g\2\2^\22\3\2\2\2_`\7<\2\2`\24"+
		"\3\2\2\2ab\7r\2\2bc\7q\2\2cd\7t\2\2de\7v\2\2e\26\3\2\2\2fg\7j\2\2gh\7"+
		"q\2\2hi\7u\2\2ij\7v\2\2j\30\3\2\2\2kl\7k\2\2lm\7u\2\2mn\7E\2\2no\7n\2"+
		"\2op\7w\2\2pq\7u\2\2qr\7v\2\2rs\7g\2\2st\7t\2\2t\32\3\2\2\2uv\7e\2\2v"+
		"w\7n\2\2wx\7w\2\2xy\7u\2\2yz\7v\2\2z{\7g\2\2{|\7t\2\2|}\7/\2\2}~\7c\2"+
		"\2~\177\7f\2\2\177\u0080\7f\2\2\u0080\u0081\7t\2\2\u0081\u0082\7g\2\2"+
		"\u0082\u0083\7u\2\2\u0083\u0084\7u\2\2\u0084\u0085\7g\2\2\u0085\u0086"+
		"\7u\2\2\u0086\34\3\2\2\2\u0087\u008c\5#\22\2\u0088\u008b\5#\22\2\u0089"+
		"\u008b\5%\23\2\u008a\u0088\3\2\2\2\u008a\u0089\3\2\2\2\u008b\u008e\3\2"+
		"\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\36\3\2\2\2\u008e\u008c"+
		"\3\2\2\2\u008f\u0095\7$\2\2\u0090\u0091\7^\2\2\u0091\u0094\7$\2\2\u0092"+
		"\u0094\13\2\2\2\u0093\u0090\3\2\2\2\u0093\u0092\3\2\2\2\u0094\u0097\3"+
		"\2\2\2\u0095\u0096\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0098\3\2\2\2\u0097"+
		"\u0095\3\2\2\2\u0098\u0099\7$\2\2\u0099\u009a\b\20\2\2\u009a \3\2\2\2"+
		"\u009b\u009d\5%\23\2\u009c\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009c"+
		"\3\2\2\2\u009e\u009f\3\2\2\2\u009f\"\3\2\2\2\u00a0\u00a1\t\2\2\2\u00a1"+
		"$\3\2\2\2\u00a2\u00a3\t\3\2\2\u00a3&\3\2\2\2\u00a4\u00a6\t\4\2\2\u00a5"+
		"\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2"+
		"\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\b\24\3\2\u00aa(\3\2\2\2\u00ab\u00ac"+
		"\7\61\2\2\u00ac\u00ad\7,\2\2\u00ad\u00b1\3\2\2\2\u00ae\u00b0\13\2\2\2"+
		"\u00af\u00ae\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b1\u00af"+
		"\3\2\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b5\7,\2\2\u00b5"+
		"\u00b6\7\61\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\b\25\3\2\u00b8*\3\2\2"+
		"\2\u00b9\u00ba\7\61\2\2\u00ba\u00bb\7\61\2\2\u00bb\u00bf\3\2\2\2\u00bc"+
		"\u00be\n\5\2\2\u00bd\u00bc\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf\u00bd\3\2"+
		"\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2"+
		"\u00c3\b\26\3\2\u00c3,\3\2\2\2\13\2\u008a\u008c\u0093\u0095\u009e\u00a7"+
		"\u00b1\u00bf\4\3\20\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}