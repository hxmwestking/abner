package org.example.abner.leetcode.greedy;

public class L55 {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= max) {
                max = Math.max(max, i + nums[i]);
                if (max >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
