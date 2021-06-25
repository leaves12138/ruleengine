package io.inceptor.drl.drl.condition.inner;

import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import io.inceptor.drl.drl.fact.Fact;
import io.inceptor.drl.exceptions.ParseDrlRuntimeException;
import io.vertx.core.Future;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;

import java.io.Serializable;
import java.util.*;

public class InnerMethodCondition implements InnerCondition {

    private String statement;

    private Serializable exeutable;

    private LinkedList<String> params = new LinkedList<>();

    private Map<Box, InnerResult> cachedResults = new HashMap<>();

    private boolean not = false;

    private boolean inited = false;

    @Override
    public InnerResult evaluate(Fact o, VariableResolverFactory variableResolverFactory) {

        Box box = new Box(params.size());

        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            if (variableResolverFactory.isResolveable(param)) {
                Object c = variableResolverFactory.getVariableResolver(param).getValue();
                box.put(i, c);
            } else {
                box.put(i, param);
            }
        }

        if (cachedResults.containsKey(box)) {
            return cachedResults.get(box);
        }

        Object re = MVEL.executeExpression(exeutable, variableResolverFactory);
        if (re instanceof Boolean) {
            InnerResult result = InnerResult.valueof((boolean)re);
            cachedResults.put(box , result);
            return result;
        }

        if (re instanceof Future) {
            InnerResult result = InnerResult.valueof((Future<Boolean>) re);
            cachedResults.put(box, result);
            return result;
        }

        cachedResults.put(box, InnerResult.trueResult);
        return InnerResult.trueResult;
    }

    @Override
    public void init(Class c) {

        if (inited)
            return;

        if (statement == null) {
            throw new ParseDrlRuntimeException("statement is null");
        }
        exeutable = MVEL.compileExpression(statement);

        inited = true;
    }

    @Override
    public void clear() {
        cachedResults.clear();
    }

    @Override
    public List<SymbolClassName> getAllSymbolClassNames() {
        return Collections.emptyList();
    }


    @Override
    public String getSql() {
        return null;
    }

    @Override
    public String getSql(VariableResolverFactory variableResolverFactory) {
        return null;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public void addParam(String param) {
        if (!this.params.contains(param))
            this.params.add(param);
    }

    public void setNot() {
        not = true;
    }

    class Box {
        private int size;
        private Object[] objects;

        private Box(int size) {
            this.size = size;
            objects = new Object[size];
        }

        public void put(int i, Object c) {
            objects[i] = c;
        }

        @Override
        public int hashCode() {

            int code = 0;
            for (int i = 0; i < this.size; i++) {
                Object o = objects[i];
                if (o != null)
                    code += o.hashCode();
            }
            return code;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Box))
                return false;

            Box b = (Box) o;

            if (this.size != b.size)
                return false;

            for (int i = 0; i < this.size; i++)
                if (this.objects[i] != null) {
                    if (!this.objects[i].equals(b.objects[i]))
                        return false;
                } else {
                    if (b.objects[i] != null)
                        return false;
                }
            return true;
        }
    }
}
