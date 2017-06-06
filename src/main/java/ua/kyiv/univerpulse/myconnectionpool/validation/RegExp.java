package ua.kyiv.univerpulse.myconnectionpool.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {

    public static boolean isCorrectName(String name){
        Pattern p = Pattern.compile("^[A-Za-z0-9_-]{2,}$");
        Matcher m = p.matcher(name);
        if(!m.matches()) return false;
        return true;
    }

    public static boolean isCorrectLogin(String login){
        Pattern p = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");
        Matcher m = p.matcher(login);
        if(!m.matches()) return false;
        return true;
    }

    public static boolean isCorrectPassword(String password){
        Pattern p = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@#!%*?&])[A-Za-z\\d$@#!%*?&]{8,}");
        Matcher m = p.matcher(password);
        if(!m.matches()) return false;
        return true;
    }
}
