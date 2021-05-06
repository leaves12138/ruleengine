package io.inceptor.drl.drl.condition.inner;

import org.mvel2.integration.VariableResolverFactory;

public class AndCondition implements InnerCondition {
    public InnerCondition leftCondition;
    public InnerCondition rightCondition;

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
