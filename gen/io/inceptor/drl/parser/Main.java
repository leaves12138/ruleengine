package io.inceptor.drl.parser;

import org.antlr.v4.gui.TestRig;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] a) throws Exception {
        List<String> args = new ArrayList<String>();
        args.add("io.inceptor.drl.parser.Drl");
        args.add("file");
        args.add("-gui");
        args.add("/home/transwarp/gitlab/inceptor-drools-example/src/main/resources/drls/haiguan/test.drl");
        TestRig.main(args.toArray(new String[args.size()]));
    }
}
