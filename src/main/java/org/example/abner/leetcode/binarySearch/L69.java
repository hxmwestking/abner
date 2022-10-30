package org.example.abner.leetcode.binarySearch;

public class L69 {

    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int left = 1;
        int right = x;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid * mid > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public int mySqrt2(int x) {
        int l = 1;
        int r = x;
        int ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid * mid <= x) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
