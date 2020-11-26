package io.inceptor.drl.drl.action;

import io.inceptor.drl.drl.symboltable.SymbolTable;

public interface Action {
    String getCode();

    void setCode(String code);

    void compile(SymbolTable symbolTable);

    void invoke(SymbolTable symbolTable);
}
