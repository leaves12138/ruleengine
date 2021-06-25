package io.inceptor.drl.drl;

import io.inceptor.drl.compiler.javacompiler.InMemoryJavaCompiler;
import io.inceptor.drl.drl.agenda.Activation;
import io.inceptor.drl.drl.agenda.Agenda;
import io.inceptor.drl.drl.agenda.AgendaImpl;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.dialect.Dialect;
import io.inceptor.drl.drl.fact.Fact;
import io.inceptor.drl.exceptions.InitializationException;
import io.inceptor.drl.util.DrlSession;
import org.mvel2.integration.VariableResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.LockSupport;

public class RuleEntry {
    static Logger log = LoggerFactory.getLogger(RuleEntry.class);
    protected Rule ruleHead;
    protected Agenda agenda;

    public RuleEntry init(Rule rule, String packageName, Set<DefinedFunction> definedFunctions, Set<GlobalImport> globalImports , List<DeclaredClass> declaredClasses , Set<JavaImportClass> javaImportClasses, Set<String> staticImports, Map<String, Datasource> dataSources, ParsedDrlFile parsedDrlFile, DrlSession session, VariableResolverFactory global) {
        agenda = new AgendaImpl();

        this.ruleHead = rule;

        while (rule != null) {
            rule.init(packageName, definedFunctions, globalImports, declaredClasses, javaImportClasses, staticImports, dataSources, parsedDrlFile, session, global);
            rule = rule.next;
        }

        if (parsedDrlFile.dialect == Dialect.JAVA) {

            long t1 = System.currentTimeMillis();
            Map<String, Class<?>> compiledClasses = compileAll();
            long t2 = System.currentTimeMillis();
            System.out.println("compile cost: " + (t2 - t1) +  "ms");

            rule = ruleHead;

            while (rule != null) {
                rule.postInit(compiledClasses);
                rule = rule.next;
            }
        }
        return this;
    }


    public void accept(Fact o) {
        Rule rule = ruleHead;
        rule.accept(o, agenda);
    }

    public void accept(List<Fact> os) {
        Rule rule = ruleHead;
        rule.accept(os, agenda);

        while (agenda.hasReady()) {
            Activation activation = agenda.getOneActivationByPriority();
            activation.run();
        }

        while (agenda.remainSize() > 0) {
            while (!agenda.hasReady()) {
                LockSupport.parkNanos(10);
            }

            Activation activation = agenda.getOneActivationByPriority();
            if (activation != null)
                activation.run();
            else throw new RuntimeException("activation is null");
        }
    }

    public void clear() {
        Rule rule = ruleHead;
        while (rule != null) {
            rule.clear();
            rule = rule.next;
        }
    }

    private Map<String, Class<?>> compileAll() {
        try {
            long t1 = System.currentTimeMillis();
            InMemoryJavaCompiler inMemoryJavaCompiler = InMemoryJavaCompiler.instance();
            Map a = inMemoryJavaCompiler.compileAll();
            long t2 = System.currentTimeMillis();
            log.info("compiler java actions cost " + (t2 - t1) + "ms");
            return a;
        } catch (Exception e) {
            throw new InitializationException("error happens while compile rule code in java", e);
        }
    }
}
