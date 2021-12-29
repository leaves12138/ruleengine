package io.inceptor.drl.drl;

import io.inceptor.drl.drl.agenda.Activation;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.fact.Fact;
import io.inceptor.drl.util.DrlSession;
import org.mvel2.integration.VariableResolverFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class ParallelEntry extends BaseRuleEntry{
    protected ExecutorService service;

    @Override
    public Entry init(Rule rule, String packageName, Set<DefinedFunction> definedFunctions, Set<GlobalImport> globalImports , List<DeclaredClass> declaredClasses , Set<JavaImportClass> javaImportClasses, Set<String> staticImports, Map<String, Datasource> dataSources, ParsedDrlFile parsedDrlFile, DrlSession session, VariableResolverFactory global) {
        Entry entry = super.init(rule,packageName,definedFunctions,globalImports,declaredClasses,javaImportClasses,staticImports,dataSources,parsedDrlFile,session,global);
        service = Executors.newScheduledThreadPool(session.getParallelSize());

        return entry;
    }

    @Override
    public void accept(Fact o) {
        Rule rule = ruleHead;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        //submit all when task
        while (rule != null) {
            final Rule ruleIn = rule;
            FutureTask futureTask = getFutureTask(() -> ruleIn.accept(o, agenda), atomicInteger);
            service.execute(futureTask);
            rule = rule.next;
        }

        while (atomicInteger.get() != 0 || agenda.remainSize() > 0) {
            if (!agenda.hasReady()) {
                LockSupport.parkNanos(10);
            } else {
                Activation activation = agenda.getOneActivationByPriority();
                if (activation != null) {
                    FutureTask futureTask = getFutureTask(activation, atomicInteger);
                    service.execute(futureTask);
                } else throw new RuntimeException("activation is null");
            }
        }
    }

    private FutureTask getFutureTask(Runnable runnable, AtomicInteger atomicInteger) {
        atomicInteger.incrementAndGet();
        return new FutureTask(() -> {
            try {
                runnable.run();
            }  catch (Exception ex) {
                throw ex;
            } finally {
                atomicInteger.decrementAndGet();
            }
        }, null);
    }


    @Override
    public void accept(List<Fact> os) {
        Rule rule = ruleHead;
        AtomicInteger atomicInteger = new AtomicInteger(0);

        while (rule != null) {
            final Rule ruleIn = rule;
            FutureTask futureTask = getFutureTask(() -> ruleIn.accept(os, agenda), atomicInteger);
            service.execute(futureTask);
            rule = rule.next;
        }

        while (atomicInteger.get() != 0 || agenda.remainSize() > 0) {
            if (!agenda.hasReady()) {
                LockSupport.parkNanos(10);
            } else {
                Activation activation = agenda.getOneActivationByPriority();
                if (activation != null) {
                    FutureTask futureTask = getFutureTask(activation, atomicInteger);
                    service.execute(futureTask);
                } else throw new RuntimeException("activation is null");
            }
        }
    }
}
