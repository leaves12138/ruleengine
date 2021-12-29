parser grammar DrlParser;

@head{
    public enum{}
}

options {tokenVocab = DrlLexer;}

file : pack dialect? importDecls declares functions global ? rules ;

dialect : DIALECT STRING SEP?;

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

whenClause : methodCall #fromMethodCall
           | ( symbole=Identifier ':')? className=Identifier '(' conditions ')' #fromStream
           | ( symbole=Identifier ':')? className=Identifier '(' conditions ')' FROM dbName=Identifier (LIMIT NUMBER)? #fromDs
//           | methodCall #fromMethodCall
           ;

conditions : (condition (',' condition)*)? ;

condition
    : compareClause   #CompareCondition
    | containClause   #ContainCondition
    | containsClause  #ContainsCondition
    | methodCall      #MethodCondition
    | existsClause    #ExistsCondition
    | '(' condition ')' #ConditionSelf
    | left=condition op=(AndAnd|OrOr) right=condition #AndOrCondition
    ;

compareClause returns[int lm]: (symbole=Identifier ':')? (fieldName=Identifier{$lm=0;}|jsonPath{$lm=1;}|methodCall{$lm=2;}) compare literal ;

containClause returns[int lm]: (symbole=Identifier ':')? (fieldName=Identifier{$lm=0;}|jsonPath{$lm=1;}|methodCall{$lm=2;}) CONTAIN '[' literal (',' literal)* ']' ;

containsClause returns[int lm]: (symbole=Identifier ':')? (fieldName=Identifier{$lm=0;}|methodCall{$lm=2;}) NOT? CONTAINS1 literal ;

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

methodCall returns[boolean hasParams = false, boolean not = false]
    :   (NEG {$not = true;} )? name=Identifier('.' Identifier)+
    |   (NEG {$not = true;} )? Identifier('.' Identifier)* ( '('  methodParams ? ')' {$hasParams = true;} ) ?
    |   methodCall('.' Identifier)+ ( '('  methodParams ? ')' {$hasParams = true;} ) ?
    ;

methodParams : methodParam (',' methodParam)* ;

methodParam
    :   Identifier
    |   literal
    |   methodCall
    |   mapParams
    ;

mapParams
    :   '[' (mapParam ('.' mapParam) *) ? ']'
    ;

mapParam
    :   mapkey ':' mapvalue
    ;

mapkey :   literal | Identifier ;

mapvalue :  literal | Identifier | mapParams;
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
    :   (Identifier|RULE|FUNCTION) genericity? ('.' (Identifier|RULE|FUNCTION) genericity?)* ('.' '*')?
//    |   FIdentifier ('.' FIdentifier)* ('.' '*')?
    ;

genericity
    :   '<' Identifier genericity? (',' (Identifier genericity?)) * '>' ;
