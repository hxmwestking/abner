package org.example.abner.leetcode.binarySearch;

public class L367 {

    public boolean isPerfectSquare(int num) {
        int l = 1;
        int r = num;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            long sqrt = (long) mid * mid;
            if (sqrt == num) {
                return true;
            } else if (sqrt < num) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }

}
