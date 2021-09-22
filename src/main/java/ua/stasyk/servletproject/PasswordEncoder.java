package ua.stasyk.servletproject;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    private static final Logger log = Logger.getLogger(PasswordEncoder.class);
    public static String encode(String password) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];

        try{
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        }

        BigInteger bigInteger = new BigInteger(1, digest);
        StringBuilder encryptedPassword = new StringBuilder(bigInteger.toString(16));

        while (encryptedPassword.length() < 16){
            encryptedPassword.insert(0, "0");
        }

        return encryptedPassword.toString();
    }
}
