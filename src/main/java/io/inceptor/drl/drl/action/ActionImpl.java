package io.inceptor.drl.drl.action;

import io.inceptor.drl.drl.symboltable.SymbolTable;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;

import java.io.Serializable;
import java.util.Map;

public class ActionImpl implements Action {
    private String code;

    private Serializable compiledCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void compile(Map<String, Object> imports) {
        compiledCode = MVEL.compileExpression(code, imports);
    }

    public void invoke(VariableResolverFactory variableResolverFactory) {
        MVEL.executeExpression(compiledCode, variableResolverFactory);
    }
}
