package org.example.abner.leetcode.queue;

import java.util.Stack;

public class L84 {

    public int largestRectangleArea(int[] heights) {
        int[] newHeights = new int[heights.length + 2];
        System.arraycopy(heights, 0, newHeights, 1, heights.length);
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < newHeights.length; i++) {
            while (!stack.isEmpty() && newHeights[stack.peek()] > newHeights[i]) {
                Integer pop = stack.pop();
                max = Math.max(max, newHeights[pop] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 2};
        L84 l84 = new L84();
        System.out.println(l84.largestRectangleArea(heights));
    }
}
