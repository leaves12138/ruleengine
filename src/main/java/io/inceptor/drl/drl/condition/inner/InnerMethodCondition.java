package io.inceptor.drl.drl.condition.inner;

import io.inceptor.drl.exceptions.ParseDrlRuntimeException;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;

import java.io.Serializable;

public class InnerMethodCondition implements InnerCondition {

    private String statement;

    private Serializable exeutable;

    @Override
    public boolean evaluate(Object o, VariableResolverFactory variableResolverFactory) {
        Object re = MVEL.executeExpression(exeutable, variableResolverFactory);
        if (re instanceof Boolean) {
            return (boolean)re;
        }

        return true;
    }

    @Override
    public void init(Class c) {
        if (statement == null) {
            throw new ParseDrlRuntimeException("statement is null");
        }
        exeutable = MVEL.compileExpression(statement);
    }

    @Override
    public String getSql() {
        return null;
    }

    @Override
    public String getSql(VariableResolverFactory variableResolverFactory) {
        return null;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
