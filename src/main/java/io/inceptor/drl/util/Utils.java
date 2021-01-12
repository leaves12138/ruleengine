package io.inceptor.drl.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ParseContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;

public class Utils {
    static private ObjectReader objectReader = new ObjectMapper().reader(JsonNode.class);
    static Configuration configurationEx = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider()).build();
    static private ParseContext parseContextEx = JsonPath.using(configurationEx);

    public static ObjectReader getObjectReader() {
        return objectReader;
    }

    public static ParseContext getParseContextEx() {
        return parseContextEx;
    }
}
