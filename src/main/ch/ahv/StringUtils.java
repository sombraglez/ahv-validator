package ch.ahv;

import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean containsOnlyDigits(String str){
        if (isNullOrEmpty(str)){
            return false;
        }
        Pattern onlyDigitsPattern = Pattern.compile("[0-9]+");
        return onlyDigitsPattern.matcher(str).matches();
    }
}
