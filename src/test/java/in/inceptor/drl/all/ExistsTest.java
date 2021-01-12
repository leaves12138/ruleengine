package in.inceptor.drl.all;

import io.inceptor.asm.AUTOGENERATE.example2.Exists;
import io.inceptor.drl.junit5.DrlTest;
import io.inceptor.drl.junit5.annotation.Session;
import io.inceptor.drl.util.DrlSession;
import io.inceptor.drl.util.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({DrlTest.class})
public class ExistsTest {

    @Session({"file://${drls-root}/exists.drl"})
    DrlSession session;


    @Test
    public void exists() throws Exception{
        Exists exists = new Exists().setJson(Utils.getObjectReader().readValue("{\"a\":{\"b\":1}}"))
                .setId(0);
        session.put(exists);
        session.fireOnce();

        assert exists.getId() == 1;
    }
}
