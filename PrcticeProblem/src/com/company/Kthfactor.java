package com.company;

public class Kthfactor {
    public static void main(String[] args) {
        int N = 4;
        int K =2;
        int f = kThSmallestFactor(N,K);
        System.out.println(f);


    }
    static int kThSmallestFactor(int N , int K) {
        int sum = 0;
        int[] arr = new int[100];
        for(int i = 1; i <=N; i++){
            if(N%i==0){
                sum+=1;
                arr[sum] = i;
            }
        }
        return arr[K];
    }
}
