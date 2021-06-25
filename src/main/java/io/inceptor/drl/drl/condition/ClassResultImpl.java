package io.inceptor.drl.drl.condition;

import io.inceptor.drl.drl.condition.inner.InnerResult;
import io.inceptor.drl.drl.variable.MapVariableResolverFactory;
import io.inceptor.drl.exceptions.FutureNotCompleteException;
import io.vertx.core.Handler;
import org.mvel2.integration.VariableResolverFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ClassResultImpl implements ClassResult {
    private boolean future = false;
    private boolean doneAdd = false;
    private List<MapVariableResolverFactory> resultList = new LinkedList<>();
//    private List<FutureVariableFactory> futureList = new LinkedList<>();
    private AtomicInteger futureSize = new AtomicInteger(0);
    private AtomicInteger doneFuture = new AtomicInteger(0);
    public ClassResult next;
//    private Handler<Boolean> handler;

    @Override
    public boolean addResult(MapVariableResolverFactory variableResolverFactory) {
        return resultList.add(variableResolverFactory);
    }

    @Override
    public boolean addFutureResult(InnerResult result, MapVariableResolverFactory variableResolverFactory) {
        assert result.isFuture();
        if (!future)
            future = true;
        futureSize.incrementAndGet();
        result.getFuture().onComplete(re -> {
            if (re.result()) {
                resultList.add(variableResolverFactory);
            }
            doneFuture.incrementAndGet();
//            if (doneFuture.get() == futureSize.get()) {
//                if (doneAdd && handler != null) {
//                    handler.handle(true);
//                }
//            }
        });
        return true;
    }

    @Override
    public boolean isFuture() {
        return future;
    }

//    @Override
//    public ClassResult onComplete(Handler<Boolean> handler) {
//        this.handler = handler;
//        if (doneAdd && futureSize.get() == doneFuture.get()) {
//            handler.handle(true);
//        }
//        return this;
//    }

    @Override
    public List<MapVariableResolverFactory> frames() {
        if (isComplete())
            return resultList;
        throw new FutureNotCompleteException();
    }

    @Override
    public ClassResult doneAdd(boolean b) {
        doneAdd = b;
        return this;
    }

    @Override
    public void setNext(ClassResult classResult) {
        next = classResult;
    }

    @Override
    public boolean isComplete() {
        if (!future)
            return true;
        return doneAdd && futureSize.get() == doneFuture.get();
    }

//    class FutureVariableFactory {
//        private InnerResult result;
//        private VariableResolverFactory variableResolverFactory;
//
//        public FutureVariableFactory(InnerResult result, VariableResolverFactory variableResolverFactory) {
//            this.result = result;
//            this.variableResolverFactory = variableResolverFactory;
//        }
//    }
}
