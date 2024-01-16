package carselling.selling.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {

    public static String fillzero(String number, int length){
        String res = number;
        for(int i = 0; i < length; i++){
            res = "0" + res;
        }
        return res;
    }

    public static String getPK(String prefix, int number, int length){
        String nbr = ""+number;
        String res = "";
        char[] lst = nbr.toCharArray();
        char[] lst2 =  prefix.toCharArray();
        int tempLength = lst.length + lst2.length;
        if(tempLength < length){
            int l = length - tempLength;
            res = fillzero(nbr, l);
        }
        return prefix + res;
    }

    public static boolean isEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
