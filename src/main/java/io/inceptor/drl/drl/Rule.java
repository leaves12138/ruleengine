package io.inceptor.drl.drl;

import io.inceptor.drl.drl.action.Action;
import io.inceptor.drl.drl.condition.ClassCondition;
import io.inceptor.drl.drl.condition.DbClassCondition;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import io.inceptor.drl.util.DrlSession;
import org.hibernate.Session;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.CachedMapVariableResolverFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rule {
    private String text;

    private DrlSession session;

    private String name;

    private SymbolTable symbolTable;

    private List<ClassCondition> classConditions;

    private Action action;

    public Rule next;

    public List<Rule> tempNexts;

    public boolean tempStop = false;

    private boolean inited = false;

    public void init(List<DeclaredClass> list, Map<String, Datasource> dataSources, DrlSession session) {
        if (!inited) {
            this.session = session;

            symbolTable = new SymbolTable();
            tempNexts = new ArrayList<>(3);

            symbolTable.put("thisrule", this);
            for (Datasource datasource : dataSources.values()) {
                symbolTable.put(datasource.getName(), datasource);
            }

//            for (DeclaredClass declaredClass : list){
//                imports.put(declaredClass.getClassName(), declaredClass.getInstance());
//            }
            symbolTable.putAll(ParsedDrlFile.staticImpots);

            for (ClassCondition classCondition : classConditions) {
                classCondition.init(list, dataSources);
            }

            action.compile(ParsedDrlFile.staticImpots);

            inited = true;
        }

        if (next != null) {
            next.init(list, dataSources, session);
        }
    }

    public void accept(Object o) {
        boolean pass = true;
        for (ClassCondition classCondition : classConditions) {
            if (!classCondition.evaluate(o, symbolTable)) {
                pass = false;
                break;
            }
        }

        if (pass)
            action.invoke(symbolTable);

        for (Rule tempNext : tempNexts)
            tempNext.accept(o);

        if (next != null && !tempStop)
            next.accept(o);

        tempNexts.clear();
        tempStop = false;
    }

    //prepare for cep
    public void accept(List<Object> os) {

    }

    public void firerulefile(String fileName) {
        ParsedDrlFile drlFile = session.fetchParsedDrlFile(fileName);
        if (!drlFile.isInited())
            drlFile.init(session);
        Rule tempRuleHead = drlFile.getRuleHead();
        tempNexts.add(tempRuleHead);
    }

    public void stopThisOne() {
        tempStop = true;
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
