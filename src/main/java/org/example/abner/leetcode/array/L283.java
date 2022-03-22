package org.example.abner.leetcode.array;

import java.util.Arrays;

public class L283 {

    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 1};
        L283 l283 = new L283();
        l283.moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }
}
