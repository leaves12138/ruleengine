package io.inceptor.drl.util;

import io.inceptor.drl.drl.DeclaredClass;
import io.inceptor.drl.drl.ParsedDrlFile;
import io.inceptor.drl.drl.Rule;
import io.inceptor.drl.drl.RuleEntry;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.fact.Fact;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class DrlSession {
    private Map<String, ParsedDrlFile> drlCache = new HashMap();

    private Map<String, Datasource> dsCache = new HashMap();

    private LinkedBlockingQueue objectQueue = new LinkedBlockingQueue();

    private Map<String, Object> globalMap = new HashMap<>();

    public Map<String, String> trees = new HashMap<>();

    private RuleEntry entry;

    private String entrance;

    private boolean stopped;

    public boolean treeModeOn = false;

    static {
        SecondTimeWheel.start();
    }

    public void addDrl(String fileName, InputStream is) throws IOException {
        ParsedDrlFile pd = DrlTool.parse(is);
        drlCache.put(pd.getLocation() + "/" + fileName, pd);
    }

    public void addDrlAndSetEntrance(String fileName, InputStream is) throws IOException {
        ParsedDrlFile pd = DrlTool.parse(is);
        String fullName = pd.getLocation() + "/" + fileName;
        drlCache.put(fullName, pd);
        setEntrance(fullName);
    }

    public void addDs(InputStream is) throws IOException {
        addDs(DrlTool.parseDs(is));
    }

    public void addDs(Datasource ds) {
        dsCache.put(ds.getName(), ds);
    }

    public void addDs(List<Datasource> dses) {
        if (dses == null)
            return;
        dses.forEach(datasource -> dsCache.put(datasource.getName(), datasource));
    }

    public Datasource fetchDatasource(String dsStr) {
        if (dsCache.containsKey(dsStr)) {
            return dsCache.get(dsStr);
        }

        return null;
    }

    public ParsedDrlFile fetchParsedDrlFile(String drlName) {
        drlName = drlName.replace(".", "/");
        if (drlCache.containsKey(drlName))
            return drlCache.get(drlName);
        return null;
    }

    public void fireUtilHalt() {
        initEntry();
        stopped = false;
        while (!stopped) {
            try {
                Object c = objectQueue.take();
                if (c instanceof  List) {
                    entry.accept((List<Fact>) c);
                }
                else
                    entry.accept((Fact)c);
            } catch (InterruptedException e) {
                throw new RuntimeException("drl back session iterrupted");
            }
        }
        close();
    }

    public void fireOnce() {
        initEntry();
        Object c;
        try {
            while ((c = objectQueue.poll()) != null) {
                if (c instanceof  List) {
                    entry.accept((List<Fact>) c);
                }
                else
                    entry.accept((Fact) c);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        close();
    }

    public void setEntrance(String en) {
        entrance = en;
    }

    public void halt() {
        stopped = true;
    }

    public boolean offer(Object o) {
        return objectQueue.offer(wapper(o));
    }

    public boolean put(Object o) {
        try {
            while (objectQueue.size() > 10000) {
                Thread.sleep(100);
            }
            objectQueue.put(wapper(o));
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }

    public ObjectCreator getObjectCreator(String fileName, String className) {
        if (!drlCache.containsKey(fileName))
            return null;
        ParsedDrlFile drl = drlCache.get(fileName);

        List<DeclaredClass> declaredClasses = drl.getDeclaredClasses();

        for (DeclaredClass declaredClass : declaredClasses) {
            if (declaredClass.getClassName().equals(className))
                declaredClass.init();
            return new ObjectCreator(declaredClass.getInstance());
        }

        return null;
    }

    public StreamObjectCreator getStreamObjectCreator() {
        if (entrance == null)
            return null;
        List<DeclaredClass> declaredClasses = drlCache.get(entrance).getDeclaredClasses();
        for (DeclaredClass declaredClass : declaredClasses) {
            for (DeclaredClass.Annotation annotation : declaredClass.getAnnotations()) {
                if (annotation.getName().equals("Stream")) {
                    declaredClass.init();
                    Class c = declaredClass.getInstance();
                    return new StreamObjectCreator(c);
                }
            }
        }
        return null;
    }

    public void setGlobal(String name, Object o) {
        globalMap.put(name, o);
    }

    public Object globalGet(String name) {
        return globalMap.get(name);
    }

    public void clear() {
        if (entry != null)
            entry.clear();
    }

    public void close() {
        for (Datasource datasource : dsCache.values()) {
            datasource.close();
        }
    }

    public void initEntry() {
        if (entry == null) {
            if (entrance == null)
                throw new RuntimeException("there is no entrance in this session");
            if (!drlCache.containsKey(entrance))
                throw new RuntimeException("can't find drl file " + entrance + " in cache");
            ParsedDrlFile file = drlCache.get(entrance);
            file.init(this);
            entry = file.getRuleEntry();
        }
    }

    public void setTreeMode(Boolean treeModeOn) {
        this.treeModeOn = treeModeOn;
    }

    public void addTree(String treeName, String treeJson) {
        trees.put(treeName, treeJson);
    }

    private Object wapper(Object o) {
        if (o == null) {
            return Fact.NullFact;
        }

        if (o instanceof List) {
            List<Fact> list = new ArrayList<>();
            ((List)o).forEach(t -> list.add(Fact.of(t)));
            return list;
        }

        return Fact.of(o);
    }
}
