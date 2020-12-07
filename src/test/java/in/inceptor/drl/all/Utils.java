package in.inceptor.drl.all;

import io.inceptor.drl.Drools;

public class Utils {
    public static String drlsRoot(){
        return Utils.class.getClassLoader().getResource("drls").getPath();
    }

    public static String libRoot(){
        return Utils.class.getClassLoader().getResource("drls").getPath()+"/..";
    }
}
