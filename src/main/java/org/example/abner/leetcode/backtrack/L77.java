package org.example.abner.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class L77 {

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();


    public List<List<Integer>> combine(int n, int k) {
        dfs(1, n, k);
        return ans;
    }

    private void dfs(int cur, int n, int k) {
        // 剪枝：temp 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 temp
        if (temp.size() + (n - cur + 1) < k) {
            return;
        }

        // 递归终止条件是：path 的长度等于 k
        if (temp.size() == k) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        // 遍历可能的搜索起点
        for (int i = cur; i <= n; i++) {
            // 向路径变量里添加一个数
            temp.add(i);
            System.out.println("递归之前 => " + temp);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            dfs(i + 1, n, k);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            temp.remove(temp.size() - 1);
            System.out.println("递归之后 => " + temp);
        }
    }

    public static void main(String[] args) {
        L77 l77 = new L77();
        System.out.println(l77.combine(4, 2));
    }
}
