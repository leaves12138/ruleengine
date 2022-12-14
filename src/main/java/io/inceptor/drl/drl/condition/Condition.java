package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.DeclaredClass;
import io.inceptor.drl.drl.JavaImportClass;
import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.fact.Fact;
import io.inceptor.drl.drl.variable.MapVariableResolverFactory;
import org.mvel2.integration.VariableResolverFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Condition {

    void init(List<DeclaredClass> list, Set<JavaImportClass> javaImportClasses, Map<String, Datasource> datasources);

    ClassResult evaluate(List<Fact> os, List<MapVariableResolverFactory> vars);

    boolean evaluate(Fact o, VariableResolverFactory var);

    List<SymbolClassName> getAllSymbolClassNames();

    String getClassName();

    void clear();
}
