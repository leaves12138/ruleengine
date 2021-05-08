package io.inceptor.drl.drl;

import io.inceptor.drl.drl.action.Action;
import io.inceptor.drl.drl.condition.Condition;
import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.dialect.Dialect;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import io.inceptor.drl.exceptions.InitializationException;
import io.inceptor.drl.exceptions.TypeNotMatchException;
import io.inceptor.drl.util.DrlSession;
import io.inceptor.drl.util.SecondTimeWheel;
import io.inceptor.drl.util.Utils;
import org.mvel2.integration.VariableResolverFactory;
import io.inceptor.drl.drl.variable.MapVariableResolverFactory;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

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

    private Set<FlagTypeValue> flags = new HashSet<>();

    private long ruleStartTime = -1;

    private long ruleEndTime = -1;

    public void init(String packageName, Set<DefinedFunction> definedFunctions,Set<GlobalImport> globalImports ,List<DeclaredClass> declaredClasses , Set<JavaImportClass> javaImportClasses,Set<String> staticImports, Map<String, Datasource> dataSources, ParsedDrlFile parsedDrlFile, DrlSession session, VariableResolverFactory global) {
        if (!inited) {
            this.session = session;
            this.parsedDrlFile = parsedDrlFile;

            initRuleFlag();

            symbolTable = new SymbolTable();

            ruleMapVariableResovlverFactory = new MapVariableResolverFactory(symbolTable);

            tempNexts = new ArrayList<>(3);

            ruleMapVariableResovlverFactory.createVariable("thisrule", this);

            for (Datasource datasource : dataSources.values()) {
                ruleMapVariableResovlverFactory.createVariable(datasource.getName(), datasource);
            }

            ruleMapVariableResovlverFactory.setNextFactory(global);

            for (Condition condition : conditions) {
                condition.init(declaredClasses, javaImportClasses, dataSources);
            }

            if (action.getDialect() == Dialect.JAVA)
                action.compile(packageName, name, javaImportClasses.stream().map(s -> s.getFullJavaName()).collect(Collectors.toSet()), staticImports, getParams(globalImports));
            else
                action.compile(ParsedDrlFile.classImportResolverFactory.getImportedClasses());

            inited = true;
        }

        if (next != null) {
            next.init(packageName, definedFunctions, globalImports, declaredClasses, javaImportClasses,staticImports ,dataSources, parsedDrlFile, session, global);
        }
    }

    private void initRuleFlag() {
        flags.stream().forEach(flag -> {
            switch (flag.flagType) {
                case DateEffective: {
                    try {
                        ruleStartTime = Utils.parseTime(flag.value);
                    } catch (ParseException e) {
                        throw new InitializationException("can parse rule start time", e);
                    }
                    break;
                }

                case DateExpires: {
                    try {
                        ruleEndTime = Utils.parseTime(flag.value);
                    } catch (ParseException e) {
                        throw new InitializationException("can parse rule end time", e);
                    }
                    break;
                }

            }
        });
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

        if (ruleStartTime != -1 && SecondTimeWheel.getMilliTime() < ruleStartTime)
            return false;

        if (ruleEndTime != -1 && SecondTimeWheel.getMilliTime() > ruleEndTime)
            return false;

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

    public List<SymbolClassName> getParams(Set<GlobalImport> globalImports) {
        List<SymbolClassName> list = new LinkedList<>();

        for (Condition condition : conditions) {
            list.addAll(condition.getAllSymbolClassNames());
        }

        for (GlobalImport globalImport : globalImports) {
            SymbolClassName symbolClassName = new SymbolClassName();
            symbolClassName.setSymbolName(globalImport.getGlobalName());
            symbolClassName.setFullJavaName(globalImport.getGlobalType());
            list.add(symbolClassName);
        }

        return list;
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

    public void addFlag(FlagTypeValue f) {
        this.flags.add(f);
    }


    public enum FlagType {
        DateEffective("date-effective"),
        DateExpires("date-expires");

        private String name;

        FlagType(String name) {
            this.name = name;
        }

        public static FlagType match(String name) throws TypeNotMatchException {
            switch (name) {
                case "date-effective" :
                    return DateEffective;
                case "date-expires" :
                    return DateExpires;
                default:
                    throw new TypeNotMatchException();
            }
        }
    }

    public static class FlagTypeValue {
        private FlagType flagType;
        private String value;

        public FlagTypeValue(FlagType type, String value) {
            this.flagType = type;
            this.value = value;
        }

        public static FlagTypeValue valueOf(String flagName, String value) {
            return new FlagTypeValue(FlagType.match(flagName), value);
        }
    }
}
