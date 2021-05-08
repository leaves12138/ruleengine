package io.inceptor.drl.drl.action;

import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import io.inceptor.drl.drl.dialect.Dialect;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import io.inceptor.drl.drl.variable.MapVariableResolverFactory;
import org.mvel2.integration.VariableResolverFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Action {
    String getCode();

    void setCode(String code);

    void compile(Map<String, Object> imports);

    void compile(String packageName, String ruleName, Set<String> imports, Set<String> staticImports, List<SymbolClassName> classNameList);

    void invoke(VariableResolverFactory variableResolverFactory);

    Dialect getDialect();
}
