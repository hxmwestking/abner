package org.example.abner.leetcode.greedy;

public class L122 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                ans += (prices[i - 1] - prices[i]);
            }
        }
        return ans;
    }
}
