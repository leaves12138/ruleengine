package io.inceptor.drl.util;

public class DrlUtils {
    public static String reverse(String in) {
        char[] chars = in.toCharArray();
        int i = 0;
        for (; i < chars.length / 2; i++){
            char temp = chars[i];
            chars[i] = chars[chars.length -1 - i];
            chars[chars.length -1 - i] = temp;
        }

        return new String(chars);
    }
}
