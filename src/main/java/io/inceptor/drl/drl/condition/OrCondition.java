package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.symboltable.SymbolTable;
import org.mvel2.integration.VariableResolverFactory;

public class OrCondition implements Condition {
    public Condition leftCondition;
    public Condition rightCondition;

    public void init(Class c) {
        leftCondition.init(c);
        rightCondition.init(c);
    }

    public boolean evaluate(Object o, VariableResolverFactory variableResolverFactory) {
        return leftCondition.evaluate(o, variableResolverFactory) || rightCondition.evaluate(o, variableResolverFactory);
    }

    public String getSql() {
        return "(" + leftCondition.getSql() + " OR " + rightCondition.getSql() + ")";
    }

    public String getSql(VariableResolverFactory variableResolverFactory) {
        return "(" + leftCondition.getSql(variableResolverFactory) + " OR " + rightCondition.getSql(variableResolverFactory) + ")";
    }
}
