package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.DeclaredClass;
import io.inceptor.drl.drl.JavaImportClass;
import io.inceptor.drl.drl.condition.inner.InnerMethodCondition;
import io.inceptor.drl.drl.condition.inner.InnerResult;
import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.fact.Fact;
import io.inceptor.drl.drl.variable.MapVariableResolverFactory;
import io.inceptor.drl.exceptions.ParseDrlRuntimeException;
import javassist.runtime.Inner;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;

import java.io.Serializable;
import java.util.*;

public class MethodCondition implements Condition {

    private InnerMethodCondition innerMethodCondition;

    public MethodCondition(InnerMethodCondition innerMethodCondition) {
        this.innerMethodCondition = innerMethodCondition;
    }

    @Override
    public void init(List<DeclaredClass> list, Set<JavaImportClass> javaImportClasses, Map<String, Datasource> datasources) {
        innerMethodCondition.init(null);
    }

    @Override
    public ClassResult evaluate(List<Fact> os, List<MapVariableResolverFactory> vars) {
        ClassResult classResult = new ClassResultImpl();
        for (MapVariableResolverFactory var : vars) {
            InnerResult result = innerMethodCondition.evaluate(null, var);
            if (!result.isFuture()) {
                if (!result.pass()) {
                    continue;
                }
            }

            if (result.isFuture()) {
                classResult.addFutureResult(result, var);
            }

            classResult.addResult(var);
        }
        return classResult;
    }

    @Override
    public boolean evaluate(Fact o, VariableResolverFactory var) {
        return innerMethodCondition.evaluate(null, var).pass();
    }

    @Override
    public List<SymbolClassName> getAllSymbolClassNames() {
        return Collections.emptyList();
    }

    @Override
    public String getClassName() {
        return "[METHOD]";
    }

    @Override
    public void clear() {
        innerMethodCondition.clear();
    }

    public void setNot() {
        innerMethodCondition.setNot();
    }
}
