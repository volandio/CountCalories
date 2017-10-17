package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    public static String md5(String password) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = password.getBytes();
            byte[] arraySecond = md.digest(array);
            result = new String(arraySecond);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String encode(String password) {
        String result = md5(password) + "sadasfdsa";
        StringBuilder a = new StringBuilder(result).reverse();
        result = a.toString();
        result = md5(result);
        return result;
    }
}
