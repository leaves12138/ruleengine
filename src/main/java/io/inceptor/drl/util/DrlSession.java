package io.inceptor.drl.util;

import io.inceptor.drl.drl.DeclaredClass;
import io.inceptor.drl.drl.ParsedDrlFile;
import io.inceptor.drl.drl.Rule;
import io.inceptor.drl.drl.datasource.Datasource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class DrlSession {
    private Map<String, ParsedDrlFile> drlCache = new HashMap();

    private Map<String, Datasource> dsCache = new HashMap();

    private LinkedBlockingQueue objectQueue = new LinkedBlockingQueue();

    private String entrance;

    private boolean stopped;

    public void addDrl(String fileName, InputStream is) throws IOException {
        ParsedDrlFile pd = DrlTool.parse(is);
        drlCache.put(pd.getLocation() + "/" + fileName, pd);
    }

    public void addDrlAndSetEntrance(String fileName, InputStream is) throws IOException{
        ParsedDrlFile pd = DrlTool.parse(is);
        String fullName = pd.getLocation() + "/" + fileName;
        drlCache.put(fullName, pd);
        setEntrance(fullName);
    }

    public void addDs(Datasource ds) {
        dsCache.put(ds.getName(), ds);
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
        if (entrance == null)
            throw new RuntimeException("there is no entrance in this session");
        if (!drlCache.containsKey(entrance))
            throw new RuntimeException("can't find drl file " + entrance + " in cache");
        ParsedDrlFile file = drlCache.get(entrance);
        file.init(this);
        Rule rule = file.getHeadRule();
        stopped = false;
        while (!stopped) {
            Object c = objectQueue.poll();
            rule.accept(c);
        }
    }

    public void fireOnce() {
        if (entrance == null)
            throw new RuntimeException("there is no entrance in this session");
        if (!drlCache.containsKey(entrance))
            throw new RuntimeException("can't find drl file " + entrance + " in cache");
        ParsedDrlFile file = drlCache.get(entrance);
        file.init(this);
        Rule rule = file.getHeadRule();
        stopped = false;
        Object c;
        while ((c = objectQueue.poll()) != null) {
            rule.accept(c);
        }
    }

    public void setEntrance(String en) {
        entrance = en;
    }

    public void halt() {
        stopped = true;
    }

    public boolean offer(Object o) {
        return objectQueue.offer(o);
    }

    public boolean put(Object o) {
        try {
            objectQueue.put(o);
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
}
