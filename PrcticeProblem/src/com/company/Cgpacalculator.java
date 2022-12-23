package com.company;

public class Cgpacalculator {
    public static void main(String[] args) {
        int n = 5;
        double[] marks = {90,80,70,80,90};
        double Cgpa = cgpacalc(marks,n);
        System.out.println("CGPA = "+ Cgpa);
        System.out.println( "CGPA Percentage = "
                + String.format("%.2f", Cgpa * 9.5));
    }

    static double cgpacalc(double[] marks, int n) {
        double grade[] = new double[n];
        double cgpa, sum=0;
        for (int i = 0; i < n; i++) {
            grade[i] = (marks[i]/10);
        }
        for (int i = 0; i < n; i++) {
            sum += grade[i];
        }

        cgpa = sum/n;
        return cgpa;
    }
}
