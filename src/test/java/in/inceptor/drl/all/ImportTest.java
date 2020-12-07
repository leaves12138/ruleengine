package in.inceptor.drl.all;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.inceptor.asm.AUTOGENERATE.example.Person;
import io.inceptor.drl.junit5.DrlTest;
import io.inceptor.drl.junit5.annotation.DrlDatasource;
import io.inceptor.drl.junit5.annotation.Session;
import io.inceptor.drl.util.DrlSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DrlTest.class)
public class ImportTest {
    static private ObjectMapper objectMapper = new ObjectMapper();


    @Session({"file:///home/transwarp/gitlab/inceptor-drools/src/test/resources/importmain.drl",
            "file:///home/transwarp/gitlab/inceptor-drools/src/test/resources/importfollower.drl"})
    @DrlDatasource({"file:///home/transwarp/gitlab/inceptor-drools/src/test/resources/persons.ds"})
    DrlSession session2;



    @Test
    public void testImport() {
        try {
            Person person = new Person().setName("Jimmy").setHight(160).setIsTall(true);
            assert session2 != null;
            session2.put(person);
            session2.fireOnce();
            assert (person.getName().equals("Sally"));
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}