package com.company;

public class CompoundInterest {
    public static void main(String[] args) {
//        P is principle amount
//        R is the rate and
//        T is the time span
        double principle = 10000, rate = 10.25, time = 5;
        double CI = principle * (Math.pow((1 + rate / 100), time));
        System.out.println("Compound Interest is "+ CI);
    }
}
