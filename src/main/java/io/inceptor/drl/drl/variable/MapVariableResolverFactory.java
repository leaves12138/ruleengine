package io.inceptor.drl.drl.variable;

import org.mvel2.integration.VariableResolverFactory;

import java.util.Map;

public class MapVariableResolverFactory extends org.mvel2.integration.impl.MapVariableResolverFactory {
    public MapVariableResolverFactory() {
        super();
    }

    public Map<String, Object> getVarMap() {
        return variables;
    }

    public MapVariableResolverFactory(Map variables) {
        super(variables);
    }

    public MapVariableResolverFactory(Map<String, Object> variables, VariableResolverFactory nextFactory) {
        super(variables,nextFactory);
    }

    

}
