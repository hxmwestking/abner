package org.example.abner.leetcode.divideAndConquer;

public class L50 {

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double half = myPow(x, n / 2);
        if (n % 2 == 1) {
            return half * half * x;
        } else {
            return half * half;
        }
    }

    public static void main(String[] args) {
        L50 l50 = new L50();
        System.out.println(l50.myPow(2, 10));
    }
}
