lexer grammar DsLexer;


DECLARE : 'declare' ;

END : 'end' ;

URL : 'url' ;

TRUE : 'true' ;

FALSE : 'false' ;

USERNAME : 'username' ;

PASSWORD : 'password' ;

TYPE : 'type' ;

Colon : ':';

PORT : 'port' ;

HOST : 'host' ;

ISCLUSTER : 'isCluster' ;

CLUSTERIPS : 'cluster-addresses' ;

Identifier
    :   Letter (Letter|Digit)*
    ;

STRING : '"' ('\\"'|.)*? '"' {setText(getText().substring(1,getText().length()-1));};

NUMBER :   Digit+ ;

fragment
Letter : [$a-zA-Z_] ;

fragment
Digit : [0-9]
   ;

WS  :   [ \t\r\n]+ -> skip ;

BlockComment: '/*' .*? '*/' -> skip;

LineComment: '//' ~ [\r\n]* -> skip;