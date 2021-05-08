package io.inceptor.drl.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ParseContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    static private ObjectReader objectReader = new ObjectMapper().reader(JsonNode.class);
    static Configuration configurationEx = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider()).build();
    static private ParseContext parseContextEx = JsonPath.using(configurationEx);

    static private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static ObjectReader getObjectReader() {
        return objectReader;
    }

    public static ParseContext getParseContextEx() {
        return parseContextEx;
    }


    public static long parseTime(String time) throws ParseException {
        return format.parse(time).getTime();
    }

    public static String staticImport(String fullJavaClassName, String staticName) {
        return is + fullJavaClassName + "." + staticName;
    }

    public static String staticImport(String packageName, String className, String staticName) {
        return is + packageName + "." + className + "." + staticName;
    }

    public static String staticImport(Class c, String staticName) {
        return is + c.getName() + "." + staticName;
    }

    public static String staticImport(Class c) {
        return is + c.getName() + "." + "*";
    }

    private static String is = "";
}
