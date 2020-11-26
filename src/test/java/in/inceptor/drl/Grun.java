package in.inceptor.drl;

import org.antlr.v4.gui.TestRig;

public class Grun {
    public static void main(String[] args) throws Exception{
//        TestRig.main(new String[]{"/home/transwarp/gitlab/antlrPractice/target/classes/tests/antlrg/Hello","r","-tree"});
        System.setProperty("user.dir", "/home/transwarp/gitlab/inceptor-drools/src/main/resources");
//        System.setProperty("user.dir", "/home/transwarp/gitlab/antlrPractice/target/classes/tests/antlrg");
        TestRig.main(new String[]{"Drl","tokens","-tokens","test.drl"});
    }
}
