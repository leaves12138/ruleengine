package in.inceptor.drl.all;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.inceptor.drl.junit5.DrlTest;
import io.inceptor.drl.junit5.annotation.Session;
import io.inceptor.drl.util.DrlSession;
import io.inceptor.drl.util.DrlTool;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.extension.ExtendWith;
import io.inceptor.asm.AUTOGENERATE.example.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(DrlTest.class)
public class AllTypeTest {
    static private ObjectMapper objectMapper = new ObjectMapper();

    private static Logger logger = LoggerFactory.getLogger(AllTypeTest.class);

    @Session({"file://${drls-root}/alltype.drl"})
    DrlSession session;

    public static void main(String[] args) throws Exception{
        try {
            args = new String[5];
            args[0] = "-pd";
            args[1] = "-pj";
            args[2] = "-o";
            args[3] = "/home/transwarp/gitlab/inceptor-drools/genjar";
            args[4] = Utils.drlsRoot();

            DrlTool.main(args);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws Exception {
        Type type = new Type().setT1(1L).setT2((short)2).setT3(3.0)
                .setT4((byte)4).setT5(new Date("Thu Nov 01 18:27:06 CST 2018"))
                .setT6(Time.valueOf("06:06:06")).setT7(Timestamp.valueOf("1970-07-07 07:07:07"))
                .setT8("8").setT9(9).setT10((float)10.0).setT11(new Character('c'))
                .setT12(false).setT13(13).setT14(new BigDecimal(14))
                .setT15(objectMapper.readValue("{\"a\":{\"b\":15}}", JsonNode.class));
        session.put(type);
        session.fireOnce();
        assert type.getT12();
    }
}
