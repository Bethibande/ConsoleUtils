package de.bethibande.utils;

import java.lang.reflect.Array;

public class Arrays {

    public static byte[] trim(byte[] arr, int off, int newLength) {
        byte[] newArr = new byte[newLength];
        System.arraycopy(arr, off, newArr, 0, newLength);

        return newArr;
    }

    public static <T> boolean contains(T[] arr, T obj) {
        return java.util.Arrays.asList(arr).contains(obj);
    }

    public static <T> T[] of(T... arr) {
        return arr;
    }

    public static String[] toLowerCase(String... arr) {
        return java.util.Arrays.stream(arr).map(String::toLowerCase).toArray(String[]::new);
    }

    public static String join(Object[] arr, String s) {
        StringBuilder sb = new StringBuilder();

        for(Object obj : arr) {
            sb.append(obj.toString()).append(s);
        }

        int len = sb.length();
        sb.delete(len-s.length(), len);

        return sb.toString();
    }

    public static <T> T[] add(T[] array, T... add) {
        if(array[0] == null) return null;
        T[] newArray = (T[]) Array.newInstance(array[0].getClass(), array.length + add.length);

        System.arraycopy(array, 0, newArray, 0, array.length);
        System.arraycopy(add, 0, newArray, array.length, add.length);

        return newArray;
    }

}
