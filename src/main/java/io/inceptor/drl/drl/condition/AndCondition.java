package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.symboltable.SymbolTable;

public class AndCondition implements Condition {
    public Condition leftCondition;
    public Condition rightCondition;

    public boolean evaluate(Object o, SymbolTable symbolTable) {
        return leftCondition.evaluate(o, symbolTable) && rightCondition.evaluate(o, symbolTable);
    }

    public void init(Class c) {
        leftCondition.init(c);
        rightCondition.init(c);
    }

    public String getSql() {
        return "(" + leftCondition.getSql() + " AND " + rightCondition.getSql() + ")";
    }

    public String getSql(SymbolTable symbolTable) {
        return "(" + leftCondition.getSql(symbolTable) + " OR " + rightCondition.getSql(symbolTable) + ")";
    }
}
