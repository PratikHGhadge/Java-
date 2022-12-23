package com.company;

import java.util.Scanner;

public class Commission {
    public static void main(String[] args) {
                Scanner sc=new Scanner(System.in);

                System.out.print("Enter amount:");

                double amount=sc.nextDouble();

                System.out.print("Enter commissionPercentage:");

                double commissionPercentage=sc.nextDouble();

                double commission = (amount/100)*commissionPercentage;

                System.out.println("Commission amount="+commission);

            }
}
