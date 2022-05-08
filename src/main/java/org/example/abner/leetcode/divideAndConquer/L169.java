package org.example.abner.leetcode.divideAndConquer;

public class L169 {

    public int majorityElement(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    private int majorityElementRec(int[] nums, int l, int r) {
        if (l == r) {
            return nums[l];
        }
        int mid = (r - l) / 2 + l;
        int left = majorityElementRec(nums, l, mid);
        int right = majorityElementRec(nums, mid + 1, r);

        if (left == right) {
            return left;
        }
        int leftCount = countInRange(nums, left, l, r);
        int rightCount = countInRange(nums, right, l, r);
        return leftCount > rightCount ? left : right;
    }

    private int countInRange(int[] nums, int num, int l, int r) {
        int count = 0;
        for (int i = l; i <= r; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }
}
