package io.inceptor.drl.drl.symboltable;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable extends HashMap<String, Object> {
    public SymbolTable() {
        super();
    }

    public SymbolTable(Map map) {
        super();
        this.putAll(map);
    }
}
