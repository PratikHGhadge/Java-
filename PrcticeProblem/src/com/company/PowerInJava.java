package com.company;

import java.util.Scanner;

public class PowerInJava {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter value of base :");
        int base = in.nextInt();
        System.out.println("Enter vale of power:");
        int power = in.nextInt();
        int ans = (int) Math.pow(base,power);
        System.out.println("ans = "+ ans);
    }
}
