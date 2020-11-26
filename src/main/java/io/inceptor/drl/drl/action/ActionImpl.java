package io.inceptor.drl.drl.action;

import io.inceptor.drl.drl.symboltable.SymbolTable;
import org.mvel2.MVEL;

import java.io.Serializable;

public class ActionImpl implements Action {
    private String code;

    private Serializable compiledCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void compile(SymbolTable symbolTable) {
        compiledCode = MVEL.compileExpression(code, symbolTable);
    }

    public void invoke(SymbolTable symbolTable) {
        MVEL.executeExpression(compiledCode, symbolTable);
    }
}
