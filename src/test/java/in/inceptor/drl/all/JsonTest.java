package in.inceptor.drl.all;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.inceptor.asm.AUTOGENERATE.example.Json;
import io.inceptor.asm.AUTOGENERATE.example.Person;
import io.inceptor.drl.junit5.DrlTest;
import io.inceptor.drl.junit5.annotation.DrlDatasource;
import io.inceptor.drl.junit5.annotation.Session;
import io.inceptor.drl.util.DrlSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DrlTest.class)
public class JsonTest {
    @Session({"file://${drls-root}/json.drl"})
    DrlSession session;

    private static ObjectReader reader = new ObjectMapper().reader(JsonNode.class);


    @Test
    public void assertTest() {
        try {
            Json json = new Json();
            json.setJson(reader.readValue("{\"a\":{\"b\":\"b\",\"c\":2}, \"b\":[\"nice\",\"world\"]}"));
            session.put(json);
            session.fireOnce();
            assert (json.getJson() == null);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
