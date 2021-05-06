package io.inceptor.drl.drl;

import io.inceptor.drl.drl.action.Action;
import io.inceptor.drl.drl.condition.Condition;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import io.inceptor.drl.util.DrlSession;
import org.mvel2.integration.VariableResolverFactory;
import io.inceptor.drl.drl.variable.MapVariableResolverFactory;

import java.util.*;

public class Rule {
    private String text;

    private DrlSession session;

    private ParsedDrlFile parsedDrlFile;

    private String name;

    private SymbolTable symbolTable;

    private MapVariableResolverFactory ruleMapVariableResovlverFactory;

    private List<Condition> conditions;

    private Action action;

    public Rule next;

    public List<Rule> tempNexts;

    public boolean tempStop = false;

    private boolean inited = false;

    public void init(List<DeclaredClass> list, Set<JavaImportClass> javaImportClasses, Map<String, Datasource> dataSources, ParsedDrlFile parsedDrlFile, DrlSession session, VariableResolverFactory global) {
        if (!inited) {
            this.session = session;
            this.parsedDrlFile = parsedDrlFile;

            symbolTable = new SymbolTable();

            ruleMapVariableResovlverFactory = new MapVariableResolverFactory(symbolTable);

            tempNexts = new ArrayList<>(3);

            ruleMapVariableResovlverFactory.createVariable("thisrule", this);

            for (Datasource datasource : dataSources.values()) {
                ruleMapVariableResovlverFactory.createVariable(datasource.getName(), datasource);
            }

            ruleMapVariableResovlverFactory.setNextFactory(global);

            for (Condition condition : conditions) {
                condition.init(list, javaImportClasses, dataSources);
            }

            action.compile(ParsedDrlFile.classImportResolverFactory.getImportedClasses());

            inited = true;
        }

        if (next != null) {
            next.init(list, javaImportClasses, dataSources, parsedDrlFile, session, global);
        }
    }

    public void accept(Object o) {
        if (shouldIInvoke()) {
            VariableResolverFactory fame = new MapVariableResolverFactory();
            fame.setNextFactory(ruleMapVariableResovlverFactory);
            boolean pass = true;
            for (Condition condition : conditions) {
                if (!condition.evaluate(o, fame)) {
                    pass = false;
                    break;
                }
            }

            if (pass)
                action.invoke(fame);

            for (Rule tempNext : tempNexts)
                tempNext.accept(o);
        }

        if (next != null && !tempStop) {
            next.accept(o);
        }

        tempNexts.clear();

        tempStop = false;
    }

    private boolean shouldIInvoke() {
        return parsedDrlFile.shouldIInvoke(this);
    }

    //prepare for cep
    public void accept(List<Object> os) {
        if (shouldIInvoke()) {
            List<MapVariableResolverFactory> frames = new LinkedList<>();
            MapVariableResolverFactory mapVariableResolverFactory = new MapVariableResolverFactory();
            mapVariableResolverFactory.setNextFactory(ruleMapVariableResovlverFactory);
            frames.add(mapVariableResolverFactory);


            for (Condition condition : conditions) {
                frames = condition.evaluate(os, frames);
            }

            for (MapVariableResolverFactory frame : frames) {
                action.invoke(frame);
            }

        }

        if (next != null && !tempStop) {
            next.accept(os);
        }

        tempNexts.clear();

        tempStop = false;
    }

    public void fireRuleFile(String fileName) {
        ParsedDrlFile drlFile = session.fetchParsedDrlFile(fileName);
        drlFile.getDeclaredClasses().addAll(parsedDrlFile.getDeclaredClasses());
        List<DeclaredClass> ori = drlFile.getDeclaredClasses();
        List<DeclaredClass> des = parsedDrlFile.getDeclaredClasses();
        outer:
        for (DeclaredClass d : des) {
            for (DeclaredClass o : ori) {
                if (o.getJVMFullName().equals(d.getJVMFullName()))
                    continue outer;
            }
            ori.add(d);
        }

        if (!drlFile.isInited())
            drlFile.init(session);
        Rule tempRuleHead = drlFile.getRuleHead();
        tempNexts.add(tempRuleHead);
    }

    public void stopThisOne() {
        tempStop = true;
    }

    public boolean removeRuleOnce(String ruleName) {
        return parsedDrlFile.removeOnce(ruleName);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Condition> getClassCondition() {
        return conditions;
    }

    public void setClassCondition(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

}
