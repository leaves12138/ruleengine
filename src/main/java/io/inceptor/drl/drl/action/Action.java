package io.inceptor.drl.drl.action;

import io.inceptor.drl.drl.symboltable.SymbolTable;
import org.mvel2.integration.VariableResolverFactory;

import java.util.Map;

public interface Action {
    String getCode();

    void setCode(String code);

    void compile(Map<String, Object> imports);

    void invoke(VariableResolverFactory variableResolverFactory);
}
