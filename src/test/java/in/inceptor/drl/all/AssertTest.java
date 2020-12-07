package in.inceptor.drl.all;

import io.inceptor.asm.AUTOGENERATE.example.Person;
import io.inceptor.drl.Drools;
import io.inceptor.drl.junit5.DrlTest;
import io.inceptor.drl.junit5.annotation.DrlDatasource;
import io.inceptor.drl.junit5.annotation.Session;
import io.inceptor.drl.util.DrlSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DrlTest.class)
public class AssertTest {

    @Session({"file://${drls-root}/assert.drl"})
    @DrlDatasource({"file://${drls-root}/persons.ds"})
    DrlSession session;


    @Test
    public void assertTest() {
        try {
            Person person = new Person().setName("Jimmy").setHight(160).setIsTall(true);
            session.put(person);
            session.fireOnce();
            assert (person.getName().equals("Sally"));
            return;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
