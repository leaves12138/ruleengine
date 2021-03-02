parser grammar DsParser;

options {tokenVocab = DsLexer;}

declares : (declare) + ;

declare : DECLARE Identifier field+ END ;

field : TYPE             ':'       STRING                            #TypeBranch
       | URL             ':'       STRING                            #URLBranch
       | USERNAME        ':'       STRING                            #UserNameBranch
       | PASSWORD        ':'       STRING                            #PasswordBranch
       | PORT            ':'       NUMBER                            #PortBranch
       | HOST            ':'       STRING                            #HostBranch
       | ISCLUSTER       ':'       booleanLiteral                    #IsClusterBranch
       | CLUSTERIPS      ':'       STRING                            #ClusterIpsBranch
       | name=STRING     ':'       (value=STRING|value=NUMBER)       #UserDefineBranch
       ;

booleanLiteral
    :   TRUE
    |   FALSE
    ;

