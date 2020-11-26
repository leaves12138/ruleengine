package io.inceptor.drl.junit5;

import io.inceptor.drl.Drools;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.datasource.impl.JDBCDatasource;
import io.inceptor.drl.junit5.annotation.DrlDatasource;
import io.inceptor.drl.junit5.annotation.DrlFiles;
import io.inceptor.drl.junit5.annotation.Session;
import io.inceptor.drl.util.DrlSession;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrlTest implements Extension, TestInstancePostProcessor {
    static String reg = "(.+)=(.+)";
    static Pattern pattern = Pattern.compile(reg);

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
                    URL url = new URL(urlstr);
                    String fileName = url.getFile();
                    if (!fileName.endsWith(".drl"))
                        throw new RuntimeException("only support drl file");
                    String[] temps = fileName.split("/");
                    fileName = temps[temps.length-1];
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

            String[] dses;
            if (drlDatasource != null && (dses = drlDatasource.value()) != null) {
                for (String ds : dses) {
                    Map<String, String> map = new HashMap(8);
                    String[] pairs = ds.split(",");
                    for (String pair : pairs) {
                        try {
                            Matcher matcher = pattern.matcher(pair);
                            String key = matcher.group(1);
                            String value = matcher.group(2);
                            map.put(key, value);
                        } catch (Exception e) {

                        }
                    }
                    if (!(map.containsKey("type")))
                        continue;
                    String type = map.get("type");
                    switch (type) {
                        case "jdbc": {
                            String name = map.get("name");
                            String url = map.get("url");
                            String userName = map.get("userName");
                            String password = map.get("password");
                            if (name == null && url == null && userName == null && password == null)
                                throw new RuntimeException("jdbc resource is not complete, (name, url, userName, password) need to be offered");
                            Datasource datasource = new JDBCDatasource(name, url, userName, password);
                            session.addDs(datasource);
                            break;
                        }
                        default: {
                            throw new RuntimeException("don't support this type now");
                        }
                    }
                }
            }

            field.setAccessible(true);
            field.set(o, session);
        }
    }
}
