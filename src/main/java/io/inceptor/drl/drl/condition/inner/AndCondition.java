package io.inceptor.drl.drl.condition.inner;

import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import org.mvel2.integration.VariableResolverFactory;

import java.util.LinkedList;
import java.util.List;

public class AndCondition implements InnerCondition {
    public InnerCondition leftCondition;
    public InnerCondition rightCondition;

    public boolean evaluate(Object o, VariableResolverFactory variableResolverFactory) {
        return leftCondition.evaluate(o, variableResolverFactory) && rightCondition.evaluate(o, variableResolverFactory);
    }

    @Override
    public List<SymbolClassName> getAllSymbolClassNames() {
        List<SymbolClassName> ls = new LinkedList<>();
        ls.addAll(leftCondition.getAllSymbolClassNames());
        ls.addAll(rightCondition.getAllSymbolClassNames());
        return ls;
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
