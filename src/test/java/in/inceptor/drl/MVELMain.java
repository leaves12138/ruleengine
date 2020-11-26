package in.inceptor.drl;

import org.mvel2.MVEL;

public class MVELMain {
    public static void main(String[] args){
        Float.valueOf("0x0010");
        MVEL.eval("System.out.println(1);");
        MVEL.compileExpression("");
    }
}
