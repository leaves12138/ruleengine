package in.inceptor.drl;

import org.antlr.v4.Tool;

import java.net.URL;

public class Altlr4Lexer {
    public static void main(String[] args){
        Tool.main(new String[]{"-visitor","-package","io.inceptor.drl.parser","/home/transwarp/gitlab/inceptor-drools/src/main/java/io/inceptor/drl/parser/DrlLexer.g4"});
    }
}
