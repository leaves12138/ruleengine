parser grammar DrlParser;

@head{
    public enum{}
}

options {tokenVocab = DrlLexer;}

file : pack importDecls declares rules ;

pack : PACKAGE qualifiedName SEP;

importDecls : (importDecl) * ;

declares : (declare) * ;

rules : (onerule) + ;

importDecl : importDrl | importDatasource | importNormal ;

importDrl : IMPORT DRL qualifiedName DOTDRL SEP;

importDatasource : IMPORT DATASOURCE qualifiedName SEP;

importNormal : IMPORT qualifiedName SEP;

declare : (annotation)* DECLARE Identifier fields END ;

fields : (field) + ;

field : (annotation)* Identifier ':' qualifiedName ;

annotation returns [boolean hasValue = false, boolean isPairs = false]: '@' Identifier ('(' ( elementValuePairs {$isPairs = true;}| elementValue)? ')' {$hasValue = true;}) ?;

elementValuePairs
    :   elementValuePair (',' elementValuePair)*
    ;

elementValuePair
    :   Identifier '=' elementValue
    ;

elementValue
    :    STRING
    |    NUMBER
    |    FloatingPointLiteral
    |    booleanLiteral
    ;

onerule : RULE ruleName=STRING WHEN whenClauses THEN codelines ENDMVEL ;

whenClauses : (whenClause) * ;

whenClause : ( symbole=Identifier ':')? className=Identifier '(' conditions ')' #fromStream
           | ( symbole=Identifier ':')? className=Identifier '(' conditions ')' FROM dbName=Identifier #fromDs
           ;

conditions : (condition (',' condition)*)? ;

condition
    : compareClause   #CompareCondition
    | containClause   #ContainCondition
    | '(' condition ')' #ConditionSelf
    | left=condition op=(AndAnd|OrOr) right=condition #AndOrCondition
    ;

compareClause returns[boolean lm]: (Identifier{$lm=false;}|methodCall{$lm=true;}) COMPARE literal ;

containClause returns[boolean lm]: (Identifier{$lm=false;}|methodCall{$lm=true;}) CONTAIN '[' literal| (',' literal)* ']' ;

literal
    :   NUMBER                   #NumberBranch
    |   FloatingPointLiteral     #FloatBranch
    |   STRING                   #StringBranch
    |   booleanLiteral           #BooleanBranch
    |   NULL                     #NullBranch
    |   methodCall               #MethodBranch
    ;

methodCall
    :   Identifier('.' Identifier)* ( '(' (Identifier)* ')' ) ?
    ;

booleanLiteral
    :   TRUE
    |   FALSE
    ;

codelines : codeline *;

codeline : LINE ;


qualifiedName
    :   Identifier ('.' Identifier)* (('.' '*')| ('[' NUMBER ']'))?
    ;


