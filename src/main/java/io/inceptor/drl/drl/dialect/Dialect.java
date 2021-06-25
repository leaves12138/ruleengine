package io.inceptor.drl.drl.dialect;

public enum Dialect {
    JAVA, MVEL;

    public static Dialect match(String dialect) {
        switch (dialect) {
            case "mvel" :
            case "MVEL" :
                return MVEL;

            default:
                return JAVA;
        }
    }
}
