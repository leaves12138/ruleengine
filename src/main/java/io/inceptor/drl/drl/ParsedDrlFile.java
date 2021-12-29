package io.inceptor.drl.drl;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import io.inceptor.drl.annotation.DrlFunction;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.dialect.Dialect;
import io.inceptor.drl.drl.fact.Fact;
import io.inceptor.drl.drl.staticclass.StaticFunction;
import io.inceptor.drl.exceptions.DatasourceNotFoundException;
import io.inceptor.drl.exceptions.DrlFileNoFoundException;
import io.inceptor.drl.util.DrlSession;
import io.inceptor.drl.util.DrlUtils;
import io.inceptor.drl.util.Utils;
import org.mvel2.MVEL;
import org.mvel2.ParserConfiguration;
import org.mvel2.integration.impl.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class ParsedDrlFile {
    private DrlSession session;
    private String fileName;
    private String location;
    public  Dialect dialect = Dialect.MVEL;
    private String[] drlImports;
    private String[] datasourceImports;
    private Set<JavaImportClass> javaImportClasses = new HashSet<>();
    private Set<DefinedFunction> definedFunctions = new HashSet<>();
    private static Set<String> globalStaticImports = new HashSet<>();
    private Set<String> staticImports = new HashSet<>();
    private Set<GlobalImport> globalImports = new HashSet<>();
    private List<DeclaredClass> declaredClasses = new LinkedList<>();
    private String globalExpr;
    private Map<String, Datasource> datasources = new HashMap();
    private Rule ruleHead;
    private Rule ruleTail;
    private Entry ruleEntry;
    private MapVariableResolverFactory globalResolverFactory;

    private boolean inited = false;
    private Set<String> removedRules = new HashSet<>(10);

    public static ClassImportResolverFactory classImportResolverFactory
            = new ClassImportResolverFactory(new ParserConfiguration(), null, false);

    public static StaticMethodImportResolverFactory staticMethodImportResolverFactory
            = new StaticMethodImportResolverFactory();

    static {
        classImportResolverFactory.addClass(DrlUtils.class);


        try {
            for (Method method : StaticFunction.class.getMethods()) {
                if (method.getDeclaredAnnotation(DrlFunction.class) != null) {
                    classImportResolverFactory.createVariable(method.getName(), method);
                }
            }
        } catch (SecurityException e) {
            throw new RuntimeException("Something wrong when invoke static in ParsedDrlFile.java", e);
        }

        globalStaticImports.add(Utils.staticImport(DrlUtils.class));
        globalStaticImports.add(Utils.staticImport(StaticFunction.class));
    }

    public void init(DrlSession session) {
        if (inited) {
            initGlobal();
            return;
        }

        this.session = session;


        globalResolverFactory = new MapVariableResolverFactory();

        globalResolverFactory.setNextFactory(classImportResolverFactory);

        initJavaImport();

        initStaticImport();

        initImportDatasources(datasourceImports);

        initImportDrlFiles(drlImports);

        for (DeclaredClass declaredClass : declaredClasses) {
            declaredClass.init();
        }

        initDefinedFunctions();

        initGlobal();

        initGlobalExpr();

        switch (session.mode) {
            case Serial:
                ruleEntry = new RuleEntry().init(ruleHead, location, definedFunctions, globalImports, declaredClasses, javaImportClasses, staticImports, datasources, this, session, globalResolverFactory);
                break;
            case Tree:
                ruleEntry = new TreeEntry().init(ruleHead, location, definedFunctions, globalImports, declaredClasses, javaImportClasses, staticImports, datasources, this, session, globalResolverFactory, session.trees);
                break;
            case Parallel:
                ruleEntry = new ParallelEntry().init(ruleHead, location, definedFunctions, globalImports, declaredClasses, javaImportClasses, staticImports, datasources, this, session, globalResolverFactory);
                break;
        }

        inited = true;
    }

    public boolean isInited() {
        return inited;
    }

    public boolean shouldIInvoke(Rule rule) {
        return !removedRules.contains(rule.getName());
    }

    public boolean removeOnce(String ruleName) {
        return removedRules.add(ruleName);
    }

    private void initJavaImport() {
        if (javaImportClasses == null || javaImportClasses.size() == 0)
            return;

        Set<JavaImportClass> add = new HashSet<>();

        Iterator<JavaImportClass> iter = javaImportClasses.iterator();
        while (iter.hasNext()) {
            JavaImportClass javaImportClass = iter.next();
            if ("*".equals(javaImportClass.getClassName())) {
                try {
                    ImmutableSet<ClassPath.ClassInfo> clsSet = ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClasses(javaImportClass.getLocation());
                    for (ClassPath.ClassInfo info : clsSet) {
                        try {
                            JavaImportClass javaImportClass1 = new JavaImportClass(info.getName());
                            Class c = javaImportClass1.init();
                            if ((c.getModifiers() & Modifier.PUBLIC) != 0) {
                                add.add(javaImportClass1);
                                classImportResolverFactory.addClass(c);
                            }
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException("can't find class " + info.getName() + " by current classloader", e);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException("IOException while find import xxx.*", e);
                }
                iter.remove();
            } else {
                try {
                    Class c = javaImportClass.init();
                    classImportResolverFactory.addClass(c);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("can't find class " + javaImportClass + " by current classloader", e);
                }
            }
        }

        javaImportClasses.addAll(add);
    }

    private void initStaticImport() {
        staticImports.addAll(globalStaticImports);
    }

    private void initImportDatasources(String[] datasourceImports) {
        if (datasourceImports == null)
            return;
        for (String dsStr : datasourceImports) {
            Datasource ds = searchDatasource(dsStr);
            if (ds == null)
                throw new DatasourceNotFoundException("can't find datasource: " + dsStr);
            ds.init();
            this.datasources.put(dsStr, ds);
        }
    }

    private void initImportDrlFiles(String[] drlImports) {
        if (drlImports == null)
            return;
        for (String drl : drlImports) {
            ParsedDrlFile impDrl = searchParsedDrlFile(drl);
            if (impDrl == null)
                throw new DrlFileNoFoundException("can't find drl file: " + drl);
            if (!impDrl.isInited()) {
                impDrl.init(session);
            }
            if (impDrl != null)
                importDrlFileInternal(impDrl);
        }
    }

    private void initGlobalExpr() {
        MVEL.eval(globalExpr, globalResolverFactory);
    }

    private void initGlobal() {
        for (GlobalImport globalImport : globalImports) {
            Object go = session.globalGet(globalImport.getGlobalName());
            if (go != null) {
                globalResolverFactory.createVariable(globalImport.getGlobalName(), go);
            }
        }
    }

    private void initDefinedFunctions() {
        for (DefinedFunction function : definedFunctions) {
            function.init(location, javaImportClasses.stream().map(JavaImportClass::getFullJavaName).collect(Collectors.toSet()), staticImports);
            globalResolverFactory.createVariable(function.getMethodName(), function.getOnMethod());

            staticImports.add(function.getImportSentence());
        }
    }

    private Datasource searchDatasource(String dsStr) {
        return session.fetchDatasource(dsStr);
    }

    private ParsedDrlFile searchParsedDrlFile(String drl) {
        return session.fetchParsedDrlFile(drl);
    }

    private void importDrlFileInternal(ParsedDrlFile impDrl) {
        datasources.putAll(impDrl.getDatasources());
        declaredClasses.addAll(impDrl.getDeclaredClasses());
        ruleTail.next = impDrl.ruleHead;
        if (ruleHead == null)
            ruleHead = ruleTail;
        ruleTail = impDrl.ruleTail;
    }

    public void accept(Fact o) {
        removedRules.clear();

        ruleEntry.accept(o);
    }

    public Rule getHeadRule() {
        return ruleHead;
    }

    public Entry getRuleEntry() {
        return ruleEntry;
    }

    //--------------getter and setter-----------------------------
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addImportClass(JavaImportClass javaImportClass) {
        javaImportClasses.add(javaImportClass);
    }

    public void addDefinedFunction(DefinedFunction definedFunction) {
        definedFunctions.add(definedFunction);
    }

    public void addImportGlobal(GlobalImport g) {
        globalImports.add(g);
    }

    public String[] getDrlImports() {
        return drlImports;
    }

    public void setDrlImports(String[] drlImports) {
        this.drlImports = drlImports;
    }

    public String[] getDatasourceImports() {
        return datasourceImports;
    }

    public void setDatasourceImports(String[] datasourceImports) {
        this.datasourceImports = datasourceImports;
    }

    public List<DeclaredClass> getDeclaredClasses() {
        return declaredClasses;
    }

//    public void setDeclaredClasses(List<DeclaredClass> declaredClasses) {
//        this.declaredClasses = declaredClasses;
//    }

    public void addDeclaredClasses(DeclaredClass declaredClass) {
        this.declaredClasses.add(declaredClass);
    }

    public Rule getRuleHead() {
        return ruleHead;
    }

    public void setRuleHead(Rule rule) {
        this.ruleHead = rule;
    }

    public void setRuleTail(Rule ruleTail) {
        this.ruleTail = ruleTail;
    }

    public Map<String, Datasource> getDatasources() {
        return datasources;
    }

    public void setGlobalExpr(String global) {
        this.globalExpr = global;
    }

    public void clear(){
        ruleEntry.clear();
    }


}
