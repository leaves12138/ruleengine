package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.condition.inner.InnerResult;
import io.inceptor.drl.drl.variable.MapVariableResolverFactory;
import io.vertx.core.Handler;
import org.mvel2.integration.VariableResolverFactory;

import java.util.List;

public interface ClassResult {

    boolean addResult(MapVariableResolverFactory variableResolverFactory);

    boolean addFutureResult(InnerResult result, MapVariableResolverFactory variableResolverFactory);

    boolean isFuture();

    boolean isComplete();

//    ClassResult onComplete(Handler<Boolean> handler);

    List<MapVariableResolverFactory> frames();

    ClassResult doneAdd(boolean b);

    void setNext(ClassResult classResult);
}
