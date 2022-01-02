package com.github.mrmks.mc.sapi_extension;

import java.util.Locale;

public class Utils {
    private static final int charDis = 'A' - 'a';
    public static String getDisplayName(String key) {
        key = key.toLowerCase(Locale.ENGLISH);
        StringBuilder bd = new StringBuilder(key);
        char c;
        for (int i = 0; i < key.length(); i++) {
            if ((i == 0 || key.charAt(i - 1) == ' ') && ((c = key.charAt(i)) >= 'a' && c <= 'z')) {
                bd.setCharAt(i, (char) (c + charDis));
            }
        }
        return bd.toString();
    }
}
