lexer grammar DrlLexer;


//key words

NULL : 'null' ;

TRUE : 'true' ;

FALSE : 'false' ;

RULE : 'rule' ;

DECLARE : 'declare' ;

DATASOURCE : 'datasource' ;

END : 'end' ;

WHEN : 'when' ;

THEN : 'then'   -> mode(MVEL) ;

GLOBAL : 'global' ;

PACKAGE : 'package' ;

IMPORT : 'import';

EXISTS : 'exists';

AndAnd: '&&' | 'and';

OrOr: '||' | 'or';

DRL : 'drl' ;

DOTDRL : '.drl' ;

FROM : 'from';
//symbols

LIMIT : 'limit';

FUNCTION : 'function'  ;

STATIC : 'static' ;

SEP : ';' ;

DOT : '.' ;

STAR : '*' ;

AT : '@';

LeftParen: '(';

RightParen: ')';

LeftBracket: '[';

RightBracket: ']' ;

LeftBrace: '{' -> pushMode(Block);

RightBrace: '}';

Colon: ':';

Comma: ',';

Equal: '=';

Less : '<' ;

Greater : '>' ;

EqualEqual: '==';

NotEqual: '!=';

LessEqual: '<=';

GreaterEqual: '>=';


//conditions
//COMPARE : '=='
//        | '>='
//        | '<='
//        | '!='
//        | '<'
//        | '>'
//        ;
CONTAIN : 'in'
        | 'not in'
        ;

//combines
Identifier
    :   Letter (Letter|Digit)*
    ;

STRING : '"' ('\\"'|.)*? '"' {setText(getText().substring(1,getText().length()-1).replace("\\\"","\""));};

NUMBER :   Digit+ ;

WS  :   [ \t\r\n]+ -> skip ;

BlockComment: '/*' .*? '*/' -> skip;

LineComment: '//' ~ [\r\n]* -> skip;

FloatingPointLiteral
    :   Digit '.' Digit+
    |   [1-9] Digit+ '.' Digit+
    ;


fragment
Letter : [$a-zA-Z_] ;

fragment
Digit : [0-9]
   ;


mode MVEL;
ENDMVEL : ('end' | 'end\n') -> mode(DEFAULT_MODE) ;
LINE : ~[ \n].*? '\n';
WS2 : [ \n]* -> skip;

//mode Function;

//FIdentifier
//    :   Letter (Letter|Digit)*
//    ;

//FLeftBrace: '{' -> pushMode(Block);

//FRightBrace : '}' -> popMode;


mode Block;

BLINE : (~['{''}'] )* ;

BSTRING : '"' ('\\"'|.)*? '"' {setText(getText().substring(1,getText().length()-1).replace("\\\"","\""));};

BLeftBrace: '{' -> pushMode(Block), type(LeftBrace);

BRightBrace: '}' -> popMode, type(RightBrace);




