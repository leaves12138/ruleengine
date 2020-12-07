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
       | name=STRING     ':'       (value=STRING|value=NUMBER)       #UserDefineBranch
       ;

