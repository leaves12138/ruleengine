package io.inceptor.drl.drl.staticclass;

import io.inceptor.drl.annotation.DrlFunction;

public class StaticFunction {

    @DrlFunction
    public static boolean eval(boolean b) {
        return b;
    }

    @DrlFunction
    public static String getOne() {
        return "1";
    }

    @DrlFunction
    public static Integer getIntOne() {
        return 1;
    }
}
