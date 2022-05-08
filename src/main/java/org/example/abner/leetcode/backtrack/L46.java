package org.example.abner.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class L46 {

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        dfs(nums, visited);
        return ans;
    }

    private void dfs(int[] nums, boolean[] visited) {
        if (temp.size() == nums.length) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            temp.add(nums[i]);
            System.out.println("递归之前 => " + temp);
            dfs(nums, visited);
            visited[i] = false;
            temp.remove(temp.size() - 1);
            System.out.println("递归之后 => " + temp);
        }
    }

    public static void main(String[] args) {
        L46 l46 = new L46();
        int[] arr = {1, 2, 3};
        System.out.println(l46.permute(arr));
    }
}
