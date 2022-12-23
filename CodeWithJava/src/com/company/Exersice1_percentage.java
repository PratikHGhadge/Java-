package com.company;

import java.util.Scanner;

public class Exersice1_percentage {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of marks of subject1 :");
        int subject1 = input.nextInt();
        System.out.print("Enter the number of marks of subject2 :");
        int subject2 = input.nextInt();
        System.out.print("Enter the number of marks of subject3 :");
        int subject3 = input.nextInt();
        System.out.print("Enter the number of marks of subject4 :");
        int subject4 = input.nextInt();
        System.out.print("Enter the number of marks of subject5 :");
        int subject5 = input.nextInt();
        int percentage = (subject1+subject2+subject3+subject4+subject5)/5;
        System.out.println("The percentage of student is :"+percentage);

    }
}
