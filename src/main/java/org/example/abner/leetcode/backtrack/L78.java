package org.example.abner.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class L78 {

    private final List<Integer> temp = new ArrayList<>();
    private final List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return ans;
    }

    private void backtrack(int[] nums, int index) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        System.out.println("递归之前 => " + temp);
        backtrack(nums, index + 1);
        temp.add(nums[index]);
        System.out.println("递归之中 => " + temp);
        backtrack(nums, index + 1);
        temp.remove(temp.size() - 1);
        System.out.println("递归之后 => " + temp);
    }

    public static void main(String[] args) {
        L78 l78 = new L78();
        int[] nums = {1, 2, 3};
        System.out.println(l78.subsets(nums));
    }

}
