package io.inceptor.drl.drl;

import io.inceptor.drl.drl.action.Action;
import io.inceptor.drl.drl.agenda.Agenda;
import io.inceptor.drl.drl.agenda.ResultFutureActivation;
import io.inceptor.drl.drl.condition.ClassResult;
import io.inceptor.drl.drl.condition.Condition;
import io.inceptor.drl.drl.condition.symbol.SymbolClassName;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.dialect.Dialect;
import io.inceptor.drl.drl.fact.Fact;
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

//        if (next != null) {
//            next.init(packageName, definedFunctions, globalImports, declaredClasses, javaImportClasses,staticImports ,dataSources, parsedDrlFile, session, global);
//        }
    }

    public void postInit(Map<String, Class<?>> classMap) {
        action.postCompile(classMap);
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

    public void accept(Fact o, Agenda agenda) {
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
                agenda.addActivation(() -> action.invoke(fame));

            for (Rule tempNext : tempNexts)
                agenda.addActivation(() ->
                tempNext.accept(o, agenda));
        }

        if (next != null && !tempStop) {
            agenda.addActivation(() ->  next.accept(o, agenda));
        }

        tempNexts.clear();

        tempStop = false;
    }

    //prepare for cep
    public void accept(List<Fact> os, Agenda agenda) {
        if (shouldIInvoke()) {
            List<MapVariableResolverFactory> frames = new LinkedList<>();
            MapVariableResolverFactory mapVariableResolverFactory = new MapVariableResolverFactory();
            mapVariableResolverFactory.setNextFactory(ruleMapVariableResovlverFactory);
            frames.add(mapVariableResolverFactory);

            ClassResult result;
            for (int i = 0; i < conditions.size(); i++) {
                Condition condition = conditions.get(i);
                result = condition.evaluate(os, frames);
                if (!result.isFuture())
                    frames = result.frames();
                else {
                    processFutureResult(os, result, conditions.subList(i + 1, conditions.size()), action, agenda);
                    if (next != null) {
                        agenda.addActivation(() -> next.accept(os, agenda));
                    }
                    return;
                }
            }

            for (MapVariableResolverFactory frame : frames) {
                agenda.addActivation(() -> action.invoke(frame));
            }

            if (next != null) {
                agenda.addActivation(() -> next.accept(os, agenda));
            }

        }

        tempNexts.clear();

        tempStop = false;
    }

    public void accept(List<Fact> os, Agenda agenda, TreeEntry.TreeNode ruleTree) {
        if (shouldIInvoke()) {
            List<MapVariableResolverFactory> frames = new LinkedList<>();
            MapVariableResolverFactory mapVariableResolverFactory = new MapVariableResolverFactory();
            mapVariableResolverFactory.setNextFactory(ruleMapVariableResovlverFactory);
            frames.add(mapVariableResolverFactory);

            ClassResult result;
            for (int i = 0; i < conditions.size(); i++) {
                Condition condition = conditions.get(i);
                result = condition.evaluate(os, frames);
                if (!result.isFuture())
                    frames = result.frames();
                else {
                    processFutureResult(os, result, conditions.subList(i + 1, conditions.size()), action, agenda, ruleTree);
                    return;
                }
            }

            if (frames.size() != 0) {
                for (MapVariableResolverFactory frame : frames) {
                    agenda.addActivation(() -> action.invoke(frame));
                }

                if (ruleTree.children.size() != 0) {
                    for (TreeEntry.TreeNode treeNode : ruleTree.children) {
                        agenda.addActivation(() -> treeNode.peak.accept(os, agenda));
                    }
                }
            }
        }

//        if (next != null && !tempStop) {
//            next.accept(os);
//        }

        tempNexts.clear();

        tempStop = false;
    }

    private void processFutureResult(List<Fact> os, ClassResult result, List<Condition> waitingconditions, Action action, Agenda agenda) {
        assert result.isFuture();

        ResultFutureActivation activation = ResultFutureActivation.ofValue(agenda, result, () -> {
            assert result.isComplete();
            List<MapVariableResolverFactory> frames = result.frames();

            ClassResult result1;
            for (int i = 0; i < waitingconditions.size(); i++) {
                Condition condition = waitingconditions.get(i);
                result1 = condition.evaluate(os, frames);
                result.setNext(result1);
                if (!result1.isFuture())
                    frames = result.frames();
                else {
                    processFutureResult(os, result1, waitingconditions.subList(i + 1, waitingconditions.size()), action, agenda);
                    return;
                }
            }

            for (MapVariableResolverFactory frame : frames) {
                agenda.addActivation(() -> action.invoke(frame));
            }

        });

        agenda.addActivation(activation);
    }

    private void processFutureResult(List<Fact> os, ClassResult result, List<Condition> waitingconditions, Action action, Agenda agenda, TreeEntry.TreeNode treeNode) {
        assert result.isFuture();

        ResultFutureActivation activation = ResultFutureActivation.ofValue(agenda, result, () -> {
            assert result.isComplete();
            List<MapVariableResolverFactory> frames = result.frames();

            ClassResult result1;
            for (int i = 0; i < waitingconditions.size(); i++) {
                Condition condition = waitingconditions.get(i);
                result1 = condition.evaluate(os, frames);
                result.setNext(result1);
                if (!result1.isFuture())
                    frames = result.frames();
                else {
                    processFutureResult(os, result1, waitingconditions.subList(i + 1, waitingconditions.size()), action, agenda, treeNode);
                    return;
                }
            }

            if (frames.size() != 0) {
                for (MapVariableResolverFactory frame : frames) {
                    agenda.addActivation(() -> action.invoke(frame));
                }
                if (treeNode.children.size() != 0) {
                    for (TreeEntry.TreeNode treeNode1 : treeNode.children) {
                        agenda.addActivation(() -> treeNode1.peak.accept(os, agenda));
                    }
                }
            }
        });

        agenda.addActivation(activation);
    }

    private boolean shouldIInvoke() {

        if (ruleStartTime != -1 && SecondTimeWheel.getMilliTime() < ruleStartTime)
            return false;

        if (ruleEndTime != -1 && SecondTimeWheel.getMilliTime() > ruleEndTime)
            return false;

        return parsedDrlFile.shouldIInvoke(this);
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

    //not used for now, wait to code for flag tempStop
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

    public void clear() {
        for (Condition condition : conditions) {
            condition.clear();
        }
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
