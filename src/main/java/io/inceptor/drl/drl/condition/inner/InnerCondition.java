package io.inceptor.drl.drl.condition.inner;

import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import org.mvel2.integration.VariableResolverFactory;

import java.util.List;
import java.util.Map;

public interface InnerCondition {
    //    String getClassName();
    boolean evaluate(Object o, VariableResolverFactory variableResolverFactory);

    List<SymbolClassName> getAllSymbolClassNames();

    void init(Class c);

    String getSql();

    String getSql(VariableResolverFactory variableResolverFactory);

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
