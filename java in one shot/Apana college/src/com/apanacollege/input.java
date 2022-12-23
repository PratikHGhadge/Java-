package com.apanacollege;

import java.util.Scanner;

public class input {
    public static void main(String[] args) {
        Scanner ne = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        System.out.print("Input your age :");
        int age = ne.nextInt();
        System.out.println(age);

        System.out.print("Enter your Name :");
        String name = sc.nextLine();
        System.out.println(name);
    }
}
