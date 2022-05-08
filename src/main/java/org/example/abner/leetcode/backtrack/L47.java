package org.example.abner.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L47 {

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, vis);
        return ans;
    }

    private void backtrack(int[] nums, boolean[] vis) {
        if (temp.size() == nums.length) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            vis[i] = true;
            temp.add(nums[i]);
            System.out.println("递归之前 => " + temp);
            backtrack(nums, vis);
            vis[i] = false;
            temp.remove(temp.size() - 1);
            System.out.println("递归之后 => " + temp);
        }
    }

    public static void main(String[] args) {
        L47 l47 = new L47();
        int[] arr = {1, 2, 3};
        System.out.println(l47.permuteUnique(arr));
    }
}
