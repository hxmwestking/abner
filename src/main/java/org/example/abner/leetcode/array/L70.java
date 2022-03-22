package org.example.abner.leetcode.array;

public class L70 {

    public int climbStairs(int n) {
        int f1 = 0, f2 = 0, f3 = 1;
        for (int i = 1; i <= n; i++) {
            f1 = f2;
            f2 = f3;
            f3 = f1 + f2;
        }
        return f3;
    }

}
