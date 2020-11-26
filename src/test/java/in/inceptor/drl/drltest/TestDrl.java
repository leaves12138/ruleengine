package in.inceptor.drl.drltest;

import io.inceptor.asm.AUTOGENERATE.example.Person;
import io.inceptor.drl.Drools;
import io.inceptor.drl.drl.datasource.impl.JDBCDatasource;
import io.inceptor.drl.junit5.DrlTest;
import io.inceptor.drl.junit5.annotation.DrlDatasource;
import io.inceptor.drl.junit5.annotation.Session;
import io.inceptor.drl.util.DrlSession;
import io.inceptor.drl.util.ObjectCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileInputStream;

@ExtendWith(DrlTest.class)
public class TestDrl {

    @Session({"file:///home/transwarp/gitlab/inceptor-drools/src/main/resources/assert.drl"})
    @DrlDatasource({"name=persons,url=jdbc:mysql://localhost:3306/drools,userName=root,password=password"})
    DrlSession session;

    @Test
    public void testDatasource() {
        try {
            DrlSession session = Drools.getSession();

            session.addDs(new JDBCDatasource("persons", "jdbc:mysql://localhost:3306/drools", "root", "password"));

            FileInputStream in1 = new FileInputStream("/home/transwarp/gitlab/inceptor-drools/src/main/resources/dstest.drl");
            session.addDrl("dstest", in1);
            new Thread() {
                @Override
                public void run() {
                    int i = 0;
                    while (true) {
                        Object o = new Object();
                        session.offer(o);
                        try {
                            Thread.sleep(5);
                        } catch (Exception e) {
                        }
                        if (i == 1000) {
                            session.halt();
                            return;
                        }
                    }
                }
            }.start();
            session.setEntrance("example/dstest");
            session.fireUtilHalt();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testObc() {
        try {
            DrlSession session = Drools.getSession();

            session.addDs(new JDBCDatasource("persons", "jdbc:mysql://localhost:3306/drools", "root", "password"));

            FileInputStream in1 = new FileInputStream("/home/transwarp/gitlab/inceptor-drools/src/main/resources/obc.drl");
            session.addDrl("obc", in1);
            new Thread() {
                @Override
                public void run() {
                    ObjectCreator objectCreator = session.getObjectCreator("example/obc", "Person");
                    if (objectCreator == null)
                        throw new RuntimeException("can't find class in drl ");
                    int i = 0;
                    while (true) {
                        i++;
                        Object o = objectCreator.createObject();
                        objectCreator.setProperty(o, "name", "Jimmy").setProperty(o, "hight", i);
                        session.offer(o);
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                        }
                        if (i == 1000) {
                            session.halt();
                            return;
                        }
                    }
                }
            }.start();
            session.setEntrance("example/obc");
            session.fireUtilHalt();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testImport() {
        try {
            DrlSession session = Drools.getSession();

            session.addDs(new JDBCDatasource("persons", "jdbc:mysql://localhost:3306/drools", "root", "password"));

            FileInputStream in1 = new FileInputStream("/home/transwarp/gitlab/inceptor-drools/src/main/resources/importfollower.drl");
            FileInputStream in2 = new FileInputStream("/home/transwarp/gitlab/inceptor-drools/src/main/resources/importmain.drl");
            session.addDrl("importfollower", in1);
            session.addDrl("importmain", in2);
            Person person = new Person().setName("Jimmy").setHight(160).setIsTall(true);
            session.put(person);
            session.setEntrance("example/importmain");
            session.fireOnce();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void assertTest() {
        try {
            Person person = new Person().setName("Jimmy").setHight(160).setIsTall(true);
            session.put(person);
            session.fireOnce();
            assert (person.getName().equals("sally"));
            return;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}