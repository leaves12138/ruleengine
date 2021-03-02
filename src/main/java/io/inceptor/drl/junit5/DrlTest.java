package io.inceptor.drl.junit5;

import io.inceptor.drl.Drools;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.junit5.annotation.DrlDatasource;
import io.inceptor.drl.junit5.annotation.Session;
import io.inceptor.drl.util.DrlSession;
import io.inceptor.drl.util.DrlTool;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;

public class DrlTest implements Extension, TestInstancePostProcessor {

    @Override
    public void postProcessTestInstance(Object o, ExtensionContext extensionContext) throws Exception {
        Class c = (Class) extensionContext.getElement().get();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            Session drlSession = field.getAnnotation(Session.class);
            if (drlSession == null)
                continue;
            if (!(DrlSession.class.isAssignableFrom(field.getType())))
                continue;


            DrlSession session = Drools.getSession();
            String[] urls = drlSession.value();
            if (urls == null || urls.length == 0)
                continue;

            for (int i = 0; i < urls.length; i++) {
                try {
                    String urlstr = urls[i];
                    urlstr = urlstr.replace("${drls-root}", drlsRoot());
                    URL url = new URL(urlstr);
                    String fileName = url.getFile();
                    if (!fileName.endsWith(".drl"))
                        throw new RuntimeException("only support drl file");
                    String[] temps = fileName.split("/");
                    fileName = temps[temps.length - 1];
                    fileName = fileName.substring(0, fileName.length() - 4);
                    InputStream inputStream = url.openStream();
                    if (i == 0)
                        session.addDrlAndSetEntrance(fileName, inputStream);
                    else
                        session.addDrl(fileName, inputStream);
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occured during open drl file", e);
                }
            }

            DrlDatasource drlDatasource = field.getAnnotation(DrlDatasource.class);

            if (drlDatasource != null) {
                urls = drlDatasource.value();
                for (int i = 0; i < urls.length; i++) {
                    try {
                        String urlstr = urls[i];
                        urlstr = urlstr.replace("${drls-root}", drlsRoot());
                        URL url = new URL(urlstr);
                        String fileName = url.getFile();
                        if (!fileName.endsWith(".ds"))
                            throw new RuntimeException("only support ds file");
                        InputStream inputStream = url.openStream();
                        List<Datasource> datasourceList = DrlTool.parseDs(inputStream);
                        session.addDs(datasourceList);
                    } catch (IOException e) {
                        throw new RuntimeException("IOException occured during open drl file", e);
                    }
                }
            }
            field.setAccessible(true);
            field.set(o, session);
        }
    }

    private String drlsRoot() {
        return Drools.class.getClassLoader().getResource("drls").getPath();
    }

    private String libRoot() {
        return Drools.class.getClassLoader().getResource("drls").getPath() + "/..";
    }
}
