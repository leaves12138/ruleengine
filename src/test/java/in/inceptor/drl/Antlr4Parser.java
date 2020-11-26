package in.inceptor.drl;

import org.antlr.v4.Tool;

public class Antlr4Parser {
    public static void main(String[] args){
        Tool.main(new String[]{"-package","io.inceptor.drl.parser","/home/transwarp/gitlab/inceptor-drools/src/main/java/io/inceptor/drl/parser/DrlParser.g4"});
    }
}
