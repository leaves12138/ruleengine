package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.symboltable.SymbolTable;

public interface Condition {
    //    String getClassName();
    boolean evaluate(Object o, SymbolTable symbolTable);

    void init(Class c);

    String getSql();

    String getSql(SymbolTable symbolTable);

    String EQUAL = "==";

    String GREATEREQUAL = ">=";

    String LESSQUAL = "<=";

    String NOTEUQAL = "!=";

    String LESS = "<";

    String GREATER = ">";

    String IN = "in";

    String NOTIN = "not in";

    String METHOD_EQUALS = "equals";

    String METHOD_COMPARETO = "compareTo";
}
