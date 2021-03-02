package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.symboltable.SymbolTable;
import org.mvel2.integration.VariableResolverFactory;

public class AndCondition implements Condition {
    public Condition leftCondition;
    public Condition rightCondition;

    public boolean evaluate(Object o, VariableResolverFactory variableResolverFactory) {
        return leftCondition.evaluate(o, variableResolverFactory) && rightCondition.evaluate(o, variableResolverFactory);
    }

    public void init(Class c) {
        leftCondition.init(c);
        rightCondition.init(c);
    }

    public String getSql() {
        return "(" + leftCondition.getSql() + " AND " + rightCondition.getSql() + ")";
    }

    public String getSql(VariableResolverFactory variableResolverFactory) {
        return "(" + leftCondition.getSql(variableResolverFactory) + " OR " + rightCondition.getSql(variableResolverFactory) + ")";
    }
}
