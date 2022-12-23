package com.company;

import java.util.Scanner;

public class AvrageOfFirstNNaturalNo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter n:");
        int n = in.nextInt();
        System.out.println(Avg(n));
        System.out.println(avg(n));
    }
    static int Avg(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += i;
        }
        return sum/n;
    }

    static int avg(int n) {
        return (n*(n+1))/(2*n);
    }
}
