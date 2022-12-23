package com.company;

import java.util.Scanner;

public class DiscountOnProduct {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("enter market price:");
        float M = in.nextFloat();  // market price
        System.out.println("enter selling price:");
        float S = in.nextFloat();   // seling price
        System.out.println(DiscountPercentage(M,S));

    }
    static float DiscountPercentage(float S, float M){
        System.out.println("Discount is :");
        return ((S-M)/M)*100;
    }
}
