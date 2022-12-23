package com.company;

import java.util.Locale;

public class CWH_15_ps3 {
    public static void main(String[] args) {
//        problem 1   convert java string into lowercase
        String name = "Jack Parker";
        System.out.println(name.toLowerCase());

//        problem 2 replace spaces with underscore
        String text = "To lower case ";
        text = text.replace(" ", "_");
        System.out.println(text);

//        problem 3  replace name in letter
        String letter = "Dear <|name|>, Thanks alot!";
        System.out.println(letter.replace("<|name|>","Pratik"));

//        problem 4 detect double and tripal spaces
        String myString = "This string contains   double and tripal space ";
        System.out.println(myString.indexOf("  "));
        System.out.println(myString.indexOf("   "));

//        problem 5 use escape sequence characters
        String letter2 = "Dear harry,\n\tthis java course is nice.\t\nThanks!";
        System.out.println(letter2);


    }
}
