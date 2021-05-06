package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.DeclaredClass;
import io.inceptor.drl.drl.JavaImportClass;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.variable.MapVariableResolverFactory;
import io.inceptor.drl.exceptions.ParseDrlRuntimeException;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MethodCondition implements Condition {

    private String statement;

    private Serializable exeutable;

    @Override
    public void init(List<DeclaredClass> list, Set<JavaImportClass> javaImportClasses, Map<String, Datasource> datasources) {
        if (statement == null) {
            throw new ParseDrlRuntimeException("statement is null");
        }
        exeutable = MVEL.compileExpression(statement);
    }

    @Override
    public List<MapVariableResolverFactory> evaluate(List<Object> os, List<MapVariableResolverFactory> vars) {
        List<MapVariableResolverFactory> rm = new LinkedList<>();
        for (MapVariableResolverFactory var : vars) {
            Object re = MVEL.executeExpression(exeutable, var);
            if (re instanceof Boolean) {
                if (!(boolean)re)
                    rm.add(var);
            }
        }
        vars.removeAll(rm);
        return vars;
    }

    @Override
    public boolean evaluate(Object o, VariableResolverFactory var) {
        Object re = MVEL.executeExpression(statement, var);

        if (re instanceof Boolean) {
            return (boolean)re;
        }

        return true;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
