package com.company;

import java.util.Locale;

public class CWH_14_STRINGMETHODS {
    public static void main(String[] args) {
        String name  = "harry";
//        System.out.println(name);
        int value = name.length();
        System.out.println(value);
        String lstring = name.toLowerCase();
        System.out.println(lstring);
        String ustring = name.toUpperCase();
        System.out.println(ustring);

        String nanTrimeString = "   pratik     ";
        System.out.println(nanTrimeString);
        System.out.println(nanTrimeString.trim());




    }
}
