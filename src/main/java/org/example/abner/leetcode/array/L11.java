package org.example.abner.leetcode.array;

public class L11 {

    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            int a = height[i];
            int b = height[j];
            if (a >= b) {
                max = Math.max(max, b * (j - i));
                j--;
            } else {
                max = Math.max(max, a * (j - i));
                i++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        L11 l11 = new L11();
        System.out.println(l11.maxArea(height));
    }
}
