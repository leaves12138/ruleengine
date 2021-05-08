parser grammar DrlParser;

@head{
    public enum{}
}

options {tokenVocab = DrlLexer;}

file : pack importDecls declares  global ? functions rules ;

pack : PACKAGE qualifiedName SEP?;

importDecls : (importDecl) * ;

declares : (declare) * ;

global : GLOBAL block ;

//glbcodelines : codeline *;

rules : (onerule) + ;

importDecl : importDrl | importDatasource | importNormal | importStatic | importGlobal;

importDrl : IMPORT DRL qualifiedName DOTDRL SEP;

importDatasource : IMPORT DATASOURCE qualifiedName SEP;

importNormal : IMPORT qualifiedName SEP;

importStatic : IMPORT STATIC qualifiedName SEP;

importGlobal : GLOBAL qualifiedName name=Identifier SEP;

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

onerule : RULE ruleName=STRING ruleFlags WHEN whenClauses THEN codelines ENDMVEL SEP? ;

ruleFlags : ruleFlag * ;

ruleFlag : flag=Identifier value=STRING  ;

whenClauses : (whenClause) * ;

whenClause : ( symbole=Identifier ':')? className=Identifier '(' conditions ')' #fromStream
           | ( symbole=Identifier ':')? className=Identifier '(' conditions ')' FROM dbName=Identifier (LIMIT NUMBER)? #fromDs
           | methodCall #fromMethodCall
           ;

conditions : (condition (',' condition)*)? ;

condition
    : compareClause   #CompareCondition
    | containClause   #ContainCondition
    | existsClause    #ExistsCondition
    | methodCall      #MethodCondition
    | '(' condition ')' #ConditionSelf
    | left=condition op=(AndAnd|OrOr) right=condition #AndOrCondition
    ;

compareClause returns[int lm]: (symbole=Identifier ':')? (fieldName=Identifier{$lm=0;}|jsonPath{$lm=1;}|methodCall{$lm=2;}) compare literal ;

containClause returns[int lm]: (symbole=Identifier ':')? (fieldName=Identifier{$lm=0;}|jsonPath{$lm=1;}|methodCall{$lm=2;}) CONTAIN '[' literal (',' literal)* ']' ;

existsClause returns[int lm]: (symbole=Identifier ':')? (fieldName=Identifier{$lm=0;}|jsonPath{$lm=1;}|methodCall{$lm=2;}) EXISTS? ;

compare
    :   Less
    |   Greater
    |   EqualEqual
    |   NotEqual
    |   LessEqual
    |   GreaterEqual
    ;

literal
    :   NUMBER                   #NumberBranch
    |   FloatingPointLiteral     #FloatBranch
    |   STRING                   #StringBranch
    |   booleanLiteral           #BooleanBranch
    |   NULL                     #NullBranch
    |   methodCall               #MethodBranch
    ;

methodCall
    :   Identifier('.' Identifier)* ( '('  methodParams ? ')' ) ?
    |   methodCall('.' Identifier)+ ( '('  methodParams ? ')' ) ?
    ;

methodParams : methodParam (',' methodParam)* ;

methodParam
    :   literal
    |   methodCall
    |   mapParams
    ;

mapParams
    :   '[' (mapParam ('.' mapParam) *) ? ']'
    ;

mapParam
    :   (literal|Identifier) ':' (literal|Identifier)
    ;
// function

functions : function * ;

function : FUNCTION  fbody;

fbody : returnType (fname=Identifier) '(' params ')' block;

params
    :   (param (',' param ) *)? ;

param
    :   type var ;

type
    :   qualifiedName;

returnType
    :   qualifiedName;

var
    :   Identifier;

block
    :   LeftBrace BLINE* RightBrace
    |   LeftBrace (BLINE? block BLINE? )+  RightBrace
    ;

jsonPath
    :   Identifier ('[' NUMBER ']')* ('.' Identifier ('[' NUMBER ']')* )* ;


// code
booleanLiteral
    :   TRUE
    |   FALSE
    ;

codelines : codeline *;

codeline : LINE ;


qualifiedName
    :   Identifier genericity? ('.' Identifier genericity?)* ('.' '*')?
//    |   FIdentifier ('.' FIdentifier)* ('.' '*')?
    ;

genericity
    :   '<' Identifier genericity? (',' (Identifier genericity?)) * '>' ;
