package de.bethibande.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class StringUtils {

    public static String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static final String[] colors = {"§a", "§b", "§c", "§d", "§e", "§1", "§2"};
    public static String calcColor(String txt) {
        int sum = txt.chars().sum();
        sum = sum % colors.length;

        return colors[sum];
    }

    public static String generateHash(int length) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            sb.append(characters.charAt(ThreadLocalRandom.current().nextInt(0, characters.length())));
        }
        return sb.toString();
    }

    public static String toTimeString(Duration d) {
        String days = d.toDays() <= 0 ? "": d.toDays() > 1 ? d.toDays() + " days, ": "one day, ";
        d = d.minusDays(d.toDays());

        String hours = d.toHours() <= 0 ? "": d.toHours() > 1 ? d.toHours() + " hours, ": "one hour, ";
        d = d.minusHours(d.toHours());

        String minutes = d.toMinutes() <= 0 ? "": d.toMinutes() > 1 ? d.toMinutes() + " minutes, ": "one minute, ";
        d = d.minusMinutes(d.toMinutes());

        String seconds = d.toSeconds() <= 0 ? "": d.toSeconds() > 1 ? d.toSeconds() + " seconds": "one second";

        return days + "" + hours + "" + minutes + "" + seconds;
    }

    public static String toMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static char getChar(long i, long i2) {
        if(i2 > 0) i = i / (26*i2);
        long a = i % 26;

        return alphabet.charAt((int) a);
    }

    public static String numberToString(long i, long length) {
        StringBuilder sb = new StringBuilder();
        for(long x = length; x > 0; x--) {
            char c = getChar(i, x-1);
            sb.append(c);
        }

        return sb.toString();
    }

}
