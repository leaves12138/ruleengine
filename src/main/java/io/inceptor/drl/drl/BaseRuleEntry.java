package io.inceptor.drl.drl;

import io.inceptor.drl.compiler.javacompiler.InMemoryJavaCompiler;
import io.inceptor.drl.drl.agenda.Agenda;
import io.inceptor.drl.drl.agenda.AgendaImpl;
import io.inceptor.drl.drl.bean.RuleStatus;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.dialect.Dialect;
import io.inceptor.drl.exceptions.InitializationException;
import io.inceptor.drl.util.DrlSession;
import org.mvel2.integration.VariableResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class BaseRuleEntry implements Entry {
    static Logger log = LoggerFactory.getLogger(RuleEntry.class);
    protected Rule ruleHead;
    protected Set<Rule> removed;
    protected Agenda agenda;
    protected String packageName;
    protected Set<DefinedFunction> definedFunctions;
    protected Set<GlobalImport> globalImports;
    protected List<DeclaredClass> declaredClasses;
    protected Set<JavaImportClass> javaImportClasses;
    protected Set<String> staticImports;
    protected Map<String, Datasource> dataSources;
    protected ParsedDrlFile parsedDrlFile;
    protected DrlSession session;
    protected VariableResolverFactory global;


    public Entry init(Rule rule, String packageName, Set<DefinedFunction> definedFunctions, Set<GlobalImport> globalImports , List<DeclaredClass> declaredClasses , Set<JavaImportClass> javaImportClasses, Set<String> staticImports, Map<String, Datasource> dataSources, ParsedDrlFile parsedDrlFile, DrlSession session, VariableResolverFactory global) {
        agenda = new AgendaImpl();
        removed = new HashSet<>();
        this.packageName = packageName;
        this.definedFunctions = definedFunctions;
        this.globalImports = globalImports;
        this.declaredClasses = declaredClasses;
        this.javaImportClasses = javaImportClasses;
        this.staticImports = staticImports;
        this.dataSources = dataSources;
        this.parsedDrlFile = parsedDrlFile;
        this.session = session;
        this.global = global;
        this.ruleHead = rule;

        while (rule != null) {
            rule.init(packageName, definedFunctions, globalImports, declaredClasses, javaImportClasses, staticImports, dataSources, this, session, global);
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

    public List<RuleStatus> listRunningRules() {
        Rule rule = ruleHead;
        List<RuleStatus> list = new ArrayList<>();

        for (; rule != null; rule = rule.next) {
            if (rule.isActive())
                list.add(RuleStatus.apply(rule.getName(), rule.getText(), rule.isActive() ? RuleStatus.RUNNING : RuleStatus.NOT_RUNNING, rule.getRuleStartTime(), rule.getRuleEndTime()));
        }

        return list;
    }

    public boolean removeRule(String ruleId) {
        Rule rule1 = ruleHead;
        Rule rule2 = null;
        while (rule1 != null) {
            if (rule1.getName().equals(ruleId)) {
                if (rule2 == null) {
                    ruleHead = rule1.next;
                } else {
                    rule2.next = rule1.next;
                }

                removed.add(rule1);
                break;
            }

            rule2 = rule1;
            rule1 = rule1.next;
        }

        return true;
    }

    @Override
    public boolean initAndInsertToHead(Rule rule) {
        initNewRule(rule);
        if (ruleHead == null) {
            ruleHead = rule;
        } else {
            rule.next = ruleHead;
            ruleHead = rule;
        }
        return true;
    }

    private void initNewRule(Rule rule) {
        rule.init(packageName, definedFunctions, globalImports, declaredClasses, javaImportClasses, staticImports, dataSources, this, session, global);

        if (parsedDrlFile.dialect == Dialect.JAVA) {
            Map<String, Class<?>> compiledClasses = compileAll();
            rule.postInit(compiledClasses);
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

    @Override
    public void clear() {
        Rule rule = ruleHead;
        while (rule != null) {
            rule.clear();
            rule = rule.next;
        }
    }
}
