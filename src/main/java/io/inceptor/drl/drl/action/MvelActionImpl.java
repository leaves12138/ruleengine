package io.inceptor.drl.drl.action;

import com.jayway.jsonpath.ParseContext;
import com.jayway.jsonpath.internal.ParseContextImpl;
import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import io.inceptor.drl.drl.dialect.Dialect;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import io.inceptor.drl.drl.variable.MapVariableResolverFactory;
import io.inceptor.drl.exceptions.CannotInvokeMethodException;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Parsed;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MvelActionImpl implements Action {
    private static ParserContext parseContext = new ParserContext();

    static {
        parseContext.setStrongTyping(true);
    }

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

    @Override
    public void compile(String packageName, String ruleName, Set<String> imports, Set<String> staticImports, List<SymbolClassName> classNameList) {
        throw new CannotInvokeMethodException();
    }

    public void invoke(VariableResolverFactory variableResolverFactory) {
        try {
            MVEL.executeExpression(compiledCode, variableResolverFactory);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void postCompile(Map<String, Class<?>> compiledClasses) {
        return;
    }

    @Override
    public Dialect getDialect() {
        return Dialect.MVEL;
    }
}
