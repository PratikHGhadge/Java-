package com.company;

public class febonachi {
    public static void main(String[] args) {
        int n = 6;
        System.out.println(feb(n));
    }
    static int feb(int n){
       if (n<=1){
           return n;
       }
       else {
           return feb(n-1)+feb(n-2);
       }
    }
}




