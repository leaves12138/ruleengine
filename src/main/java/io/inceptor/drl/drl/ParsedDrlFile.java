package io.inceptor.drl.drl;

import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import io.inceptor.drl.exceptions.DatasourceNotFoundException;
import io.inceptor.drl.exceptions.DrlFileNoFoundException;
import io.inceptor.drl.util.DrlSession;
import org.mvel2.MVEL;
import org.mvel2.ParserConfiguration;
import org.mvel2.integration.impl.ClassImportResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParsedDrlFile {
    private DrlSession session;
    private String fileName;
    private String location;
    private String[] javaImoprts;
    private String[] drlImports;
    private String[] datasourceImports;
    private List<DeclaredClass> declaredClasses;
    private String global;
    private Map<String, Datasource> datasources = new HashMap();
    private Rule ruleHead;
    private Rule ruleTail;
    private MapVariableResolverFactory globalResolverFactory;

    private boolean inited = false;

    public static ClassImportResolverFactory classImportResolverFactory
            = new ClassImportResolverFactory(new ParserConfiguration(), null, false);

    public void init(DrlSession session) {
        this.session = session;

        if (inited)
            return;
        globalResolverFactory = new MapVariableResolverFactory();

        globalResolverFactory.setNextFactory(classImportResolverFactory);

        initJavaImport(javaImoprts);

        initImportDatasources(datasourceImports);

        initImportDrlFiles(drlImports);

        for (DeclaredClass declaredClass : declaredClasses) {
            declaredClass.init();
        }

        initGlobal();

        if (ruleHead != null) {
            ruleHead.init(declaredClasses, datasources, this, session, globalResolverFactory);
        }

        inited = true;
    }

    public boolean isInited() {
        return inited;
    }

    private void initJavaImport(String[] javaImports) {
        if (javaImports == null)
            return;
        for (String javaImport : javaImports) {
            try {
                Class c = Thread.currentThread().getContextClassLoader().loadClass(javaImport);
                classImportResolverFactory.addClass(c);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("can't find class " + javaImport + " by current classloader", e);
            }
        }
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

    private void initGlobal() {
        MVEL.eval(global, globalResolverFactory);
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

    public void accept(Object o) {
        if (ruleHead != null) {
            ruleHead.accept(o);
        }
    }

    public Rule getHeadRule() {
        return ruleHead;
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

    public String[] getJavaImoprts() {
        return javaImoprts;
    }

    public void setJavaImoprts(String[] javaImoprts) {
        this.javaImoprts = javaImoprts;
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

    public void setDeclaredClasses(List<DeclaredClass> declaredClasses) {
        this.declaredClasses = declaredClasses;
    }

    public Rule getRuleHead() {
        return ruleHead;
    }

    public void setRuleHead(Rule rule) {
        this.ruleHead = rule;
    }

    public Rule getRuleTail() {
        return ruleTail;
    }

    public void setRuleTail(Rule ruleTail) {
        this.ruleTail = ruleTail;
    }

    public Map<String, Datasource> getDatasources() {
        return datasources;
    }

    public void setDatasources(Map<String, Datasource> datasources) {
        this.datasources = datasources;
    }

    public void setGlobal(String global) {
        this.global = global;
    }
}
