package com.apanacollege;
import java.util.Arrays;

public class Array {
    public static void main(String[] args) {
        int[] marks = new int[4];
        marks[0] = 94;
        marks[1] = 45;
        marks[2] = 67;
        marks[3] = 88;

        // length
        System.out.println(marks.length);

        // sort
        System.out.println(marks[0]);
        Arrays.sort(marks);
        System.out.println(marks[0]);
    }
}
