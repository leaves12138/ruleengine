package io.inceptor.drl.drl;

import io.inceptor.drl.drl.action.Action;
import io.inceptor.drl.drl.condition.ClassCondition;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import io.inceptor.drl.util.DrlSession;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.ClassImportResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rule {
    private String text;

    private DrlSession session;

    private ParsedDrlFile parsedDrlFile;

    private String name;

    private SymbolTable symbolTable;

    private MapVariableResolverFactory ruleMapVariableResovlverFactory;

    private List<ClassCondition> classConditions;

    private Action action;

    public Rule next;

    public List<Rule> tempNexts;

    public boolean tempStop = false;

    private boolean inited = false;

    public void init(List<DeclaredClass> list, Map<String, Datasource> dataSources, ParsedDrlFile parsedDrlFile, DrlSession session, VariableResolverFactory global) {
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

            for (ClassCondition classCondition : classConditions) {
                classCondition.init(list, dataSources);
            }

            action.compile(ParsedDrlFile.classImportResolverFactory.getImportedClasses());

            inited = true;
        }

        if (next != null) {
            next.init(list, dataSources, parsedDrlFile, session, global);
        }
    }

    public void accept(Object o) {
        if (shouldIInvoke()) {
            VariableResolverFactory fame = new MapVariableResolverFactory();
            fame.setNextFactory(ruleMapVariableResovlverFactory);
            boolean pass = true;
            for (ClassCondition classCondition : classConditions) {
                if (!classCondition.evaluate(o, fame)) {
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

    public List<ClassCondition> getClassCondition() {
        return classConditions;
    }

    public void setClassCondition(List<ClassCondition> classConditions) {
        this.classConditions = classConditions;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
