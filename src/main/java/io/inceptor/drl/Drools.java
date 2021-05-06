package io.inceptor.drl;

import io.inceptor.drl.util.DrlSession;
import org.antlr.v4.gui.TestRig;

import java.util.Collections;

public class Drools {
    public static DrlSession getSession() {
        return new DrlSession();
    }

    public static void main(String[] args) throws Exception{


        TestRig.main(new String[0]);
    }
}
