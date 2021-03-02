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
		Colon=9, PORT=10, HOST=11, CLUSTERIPS=12, Identifier=13, STRING=14, NUMBER=15, 
		WS=16, BlockComment=17, LineComment=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"DECLARE", "END", "URL", "TRUE", "FALSE", "USERNAME", "PASSWORD", "TYPE", 
			"Colon", "PORT", "HOST", "CLUSTERIPS", "Identifier", "STRING", "NUMBER", 
			"Letter", "Digit", "WS", "BlockComment", "LineComment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'declare'", "'end'", "'url'", "'true'", "'false'", "'username'", 
			"'password'", "'type'", "':'", "'port'", "'host'", "'cluster-addresses'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DECLARE", "END", "URL", "TRUE", "FALSE", "USERNAME", "PASSWORD", 
			"TYPE", "Colon", "PORT", "HOST", "CLUSTERIPS", "Identifier", "STRING", 
			"NUMBER", "WS", "BlockComment", "LineComment"
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
		case 13:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u00b8\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\7\16\177\n\16\f\16\16\16\u0082\13\16\3\17"+
		"\3\17\3\17\3\17\7\17\u0088\n\17\f\17\16\17\u008b\13\17\3\17\3\17\3\17"+
		"\3\20\6\20\u0091\n\20\r\20\16\20\u0092\3\21\3\21\3\22\3\22\3\23\6\23\u009a"+
		"\n\23\r\23\16\23\u009b\3\23\3\23\3\24\3\24\3\24\3\24\7\24\u00a4\n\24\f"+
		"\24\16\24\u00a7\13\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\7\25"+
		"\u00b2\n\25\f\25\16\25\u00b5\13\25\3\25\3\25\4\u0089\u00a5\2\26\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\2#\2%\22\'\23)\24\3\2\6\6\2&&C\\aac|\3\2\62;\5\2\13\f\17\17\"\"\4\2"+
		"\f\f\17\17\2\u00bd\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5\63\3\2\2\2\7\67\3\2\2\2"+
		"\t;\3\2\2\2\13@\3\2\2\2\rF\3\2\2\2\17O\3\2\2\2\21X\3\2\2\2\23]\3\2\2\2"+
		"\25_\3\2\2\2\27d\3\2\2\2\31i\3\2\2\2\33{\3\2\2\2\35\u0083\3\2\2\2\37\u0090"+
		"\3\2\2\2!\u0094\3\2\2\2#\u0096\3\2\2\2%\u0099\3\2\2\2\'\u009f\3\2\2\2"+
		")\u00ad\3\2\2\2+,\7f\2\2,-\7g\2\2-.\7e\2\2./\7n\2\2/\60\7c\2\2\60\61\7"+
		"t\2\2\61\62\7g\2\2\62\4\3\2\2\2\63\64\7g\2\2\64\65\7p\2\2\65\66\7f\2\2"+
		"\66\6\3\2\2\2\678\7w\2\289\7t\2\29:\7n\2\2:\b\3\2\2\2;<\7v\2\2<=\7t\2"+
		"\2=>\7w\2\2>?\7g\2\2?\n\3\2\2\2@A\7h\2\2AB\7c\2\2BC\7n\2\2CD\7u\2\2DE"+
		"\7g\2\2E\f\3\2\2\2FG\7w\2\2GH\7u\2\2HI\7g\2\2IJ\7t\2\2JK\7p\2\2KL\7c\2"+
		"\2LM\7o\2\2MN\7g\2\2N\16\3\2\2\2OP\7r\2\2PQ\7c\2\2QR\7u\2\2RS\7u\2\2S"+
		"T\7y\2\2TU\7q\2\2UV\7t\2\2VW\7f\2\2W\20\3\2\2\2XY\7v\2\2YZ\7{\2\2Z[\7"+
		"r\2\2[\\\7g\2\2\\\22\3\2\2\2]^\7<\2\2^\24\3\2\2\2_`\7r\2\2`a\7q\2\2ab"+
		"\7t\2\2bc\7v\2\2c\26\3\2\2\2de\7j\2\2ef\7q\2\2fg\7u\2\2gh\7v\2\2h\30\3"+
		"\2\2\2ij\7e\2\2jk\7n\2\2kl\7w\2\2lm\7u\2\2mn\7v\2\2no\7g\2\2op\7t\2\2"+
		"pq\7/\2\2qr\7c\2\2rs\7f\2\2st\7f\2\2tu\7t\2\2uv\7g\2\2vw\7u\2\2wx\7u\2"+
		"\2xy\7g\2\2yz\7u\2\2z\32\3\2\2\2{\u0080\5!\21\2|\177\5!\21\2}\177\5#\22"+
		"\2~|\3\2\2\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3"+
		"\2\2\2\u0081\34\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0089\7$\2\2\u0084\u0085"+
		"\7^\2\2\u0085\u0088\7$\2\2\u0086\u0088\13\2\2\2\u0087\u0084\3\2\2\2\u0087"+
		"\u0086\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u008a\3\2\2\2\u0089\u0087\3\2"+
		"\2\2\u008a\u008c\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008d\7$\2\2\u008d"+
		"\u008e\b\17\2\2\u008e\36\3\2\2\2\u008f\u0091\5#\22\2\u0090\u008f\3\2\2"+
		"\2\u0091\u0092\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093 "+
		"\3\2\2\2\u0094\u0095\t\2\2\2\u0095\"\3\2\2\2\u0096\u0097\t\3\2\2\u0097"+
		"$\3\2\2\2\u0098\u009a\t\4\2\2\u0099\u0098\3\2\2\2\u009a\u009b\3\2\2\2"+
		"\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e"+
		"\b\23\3\2\u009e&\3\2\2\2\u009f\u00a0\7\61\2\2\u00a0\u00a1\7,\2\2\u00a1"+
		"\u00a5\3\2\2\2\u00a2\u00a4\13\2\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3"+
		"\2\2\2\u00a5\u00a6\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7"+
		"\u00a5\3\2\2\2\u00a8\u00a9\7,\2\2\u00a9\u00aa\7\61\2\2\u00aa\u00ab\3\2"+
		"\2\2\u00ab\u00ac\b\24\3\2\u00ac(\3\2\2\2\u00ad\u00ae\7\61\2\2\u00ae\u00af"+
		"\7\61\2\2\u00af\u00b3\3\2\2\2\u00b0\u00b2\n\5\2\2\u00b1\u00b0\3\2\2\2"+
		"\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b6"+
		"\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b7\b\25\3\2\u00b7*\3\2\2\2\13\2"+
		"~\u0080\u0087\u0089\u0092\u009b\u00a5\u00b3\4\3\17\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}