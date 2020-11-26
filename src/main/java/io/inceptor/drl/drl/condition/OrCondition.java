package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.symboltable.SymbolTable;

public class OrCondition implements Condition {
    public Condition leftCondition;
    public Condition rightCondition;

    public void init(Class c) {
        leftCondition.init(c);
        rightCondition.init(c);
    }

    public boolean evaluate(Object o, SymbolTable symbolTable) {
        return leftCondition.evaluate(o, symbolTable) || rightCondition.evaluate(o, symbolTable);
    }

    public String getSql() {
        return "(" + leftCondition.getSql() + " OR " + rightCondition.getSql() + ")";
    }

    public String getSql(SymbolTable symbolTable) {
        return "(" + leftCondition.getSql(symbolTable) + " OR " + rightCondition.getSql(symbolTable) + ")";
    }
}
