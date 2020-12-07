// Generated from /home/transwarp/gitlab/inceptor-drools/src/main/java/io/inceptor/drl/parser/DsLexer.g4 by ANTLR 4.8
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
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DECLARE=1, END=2, URL=3, USERNAME=4, PASSWORD=5, TYPE=6, Colon=7, PORT=8, 
		HOST=9, Identifier=10, STRING=11, NUMBER=12, WS=13, BlockComment=14, LineComment=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"DECLARE", "END", "URL", "USERNAME", "PASSWORD", "TYPE", "Colon", "PORT", 
			"HOST", "Identifier", "STRING", "NUMBER", "Letter", "Digit", "WS", "BlockComment", 
			"LineComment"
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
		case 10:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21\u0095\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\7\13\\\n\13\f\13\16\13_\13\13\3\f\3\f\3\f\3\f\7\fe\n\f"+
		"\f\f\16\fh\13\f\3\f\3\f\3\f\3\r\6\rn\n\r\r\r\16\ro\3\16\3\16\3\17\3\17"+
		"\3\20\6\20w\n\20\r\20\16\20x\3\20\3\20\3\21\3\21\3\21\3\21\7\21\u0081"+
		"\n\21\f\21\16\21\u0084\13\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\7\22\u008f\n\22\f\22\16\22\u0092\13\22\3\22\3\22\4f\u0082\2\23\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\2\35\2\37\17"+
		"!\20#\21\3\2\6\6\2&&C\\aac|\3\2\62;\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2"+
		"\u009a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2\5-\3"+
		"\2\2\2\7\61\3\2\2\2\t\65\3\2\2\2\13>\3\2\2\2\rG\3\2\2\2\17L\3\2\2\2\21"+
		"N\3\2\2\2\23S\3\2\2\2\25X\3\2\2\2\27`\3\2\2\2\31m\3\2\2\2\33q\3\2\2\2"+
		"\35s\3\2\2\2\37v\3\2\2\2!|\3\2\2\2#\u008a\3\2\2\2%&\7f\2\2&\'\7g\2\2\'"+
		"(\7e\2\2()\7n\2\2)*\7c\2\2*+\7t\2\2+,\7g\2\2,\4\3\2\2\2-.\7g\2\2./\7p"+
		"\2\2/\60\7f\2\2\60\6\3\2\2\2\61\62\7w\2\2\62\63\7t\2\2\63\64\7n\2\2\64"+
		"\b\3\2\2\2\65\66\7w\2\2\66\67\7u\2\2\678\7g\2\289\7t\2\29:\7p\2\2:;\7"+
		"c\2\2;<\7o\2\2<=\7g\2\2=\n\3\2\2\2>?\7r\2\2?@\7c\2\2@A\7u\2\2AB\7u\2\2"+
		"BC\7y\2\2CD\7q\2\2DE\7t\2\2EF\7f\2\2F\f\3\2\2\2GH\7v\2\2HI\7{\2\2IJ\7"+
		"r\2\2JK\7g\2\2K\16\3\2\2\2LM\7<\2\2M\20\3\2\2\2NO\7r\2\2OP\7q\2\2PQ\7"+
		"t\2\2QR\7v\2\2R\22\3\2\2\2ST\7j\2\2TU\7q\2\2UV\7u\2\2VW\7v\2\2W\24\3\2"+
		"\2\2X]\5\33\16\2Y\\\5\33\16\2Z\\\5\35\17\2[Y\3\2\2\2[Z\3\2\2\2\\_\3\2"+
		"\2\2][\3\2\2\2]^\3\2\2\2^\26\3\2\2\2_]\3\2\2\2`f\7$\2\2ab\7^\2\2be\7$"+
		"\2\2ce\13\2\2\2da\3\2\2\2dc\3\2\2\2eh\3\2\2\2fg\3\2\2\2fd\3\2\2\2gi\3"+
		"\2\2\2hf\3\2\2\2ij\7$\2\2jk\b\f\2\2k\30\3\2\2\2ln\5\35\17\2ml\3\2\2\2"+
		"no\3\2\2\2om\3\2\2\2op\3\2\2\2p\32\3\2\2\2qr\t\2\2\2r\34\3\2\2\2st\t\3"+
		"\2\2t\36\3\2\2\2uw\t\4\2\2vu\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2yz\3"+
		"\2\2\2z{\b\20\3\2{ \3\2\2\2|}\7\61\2\2}~\7,\2\2~\u0082\3\2\2\2\177\u0081"+
		"\13\2\2\2\u0080\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0083\3\2\2\2\u0082"+
		"\u0080\3\2\2\2\u0083\u0085\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0086\7,"+
		"\2\2\u0086\u0087\7\61\2\2\u0087\u0088\3\2\2\2\u0088\u0089\b\21\3\2\u0089"+
		"\"\3\2\2\2\u008a\u008b\7\61\2\2\u008b\u008c\7\61\2\2\u008c\u0090\3\2\2"+
		"\2\u008d\u008f\n\5\2\2\u008e\u008d\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e"+
		"\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092\u0090\3\2\2\2\u0093"+
		"\u0094\b\22\3\2\u0094$\3\2\2\2\13\2[]dfox\u0082\u0090\4\3\f\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}