package org.example.abner.leetcode.dfsbfs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class L433 {

    char[] keys = new char[]{'A', 'C', 'G', 'T'};

    public int minMutation(String start, String end, String[] bank) {
        Set<String> cnt = new HashSet<>();
        Set<String> visited = new HashSet<>();
        for (String w : bank) {
            cnt.add(w);
        }
        if (start.equals(end)) {
            return 0;
        }
        if (!cnt.contains(end)) {
            return -1;
        }
        Queue<String> queue = new ArrayDeque<String>();
        queue.offer(start);
        visited.add(start);
        int step = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                String curr = queue.poll();
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (keys[k] != curr.charAt(j)) {
                            StringBuilder sb = new StringBuilder(curr);
                            sb.setCharAt(j, keys[k]);
                            String next = sb.toString();
                            if (!visited.contains(next) && cnt.contains(next)) {
                                if (next.equals(end)) {
                                    return step;
                                }
                                queue.offer(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private int minSteps = Integer.MAX_VALUE;
    private final Set<String> pathSet = new HashSet<>();

    public int minMutation2(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        // bank 基因库中是否存在 end 基因序列
        boolean flag = false;
        for (String str : bank) {
            if (str.equals(end)) {
                flag = true;
                break;
            }
        }
        // bank 基因库中不存在 end 基因 无法完成基因变化直接返回-1
        if (!flag) {
            return -1;
        }

        // 暴力回溯穷举所有基因变化的步数 记录并返回最小值
        backtracking(start, end, bank, 0);
        return minSteps == Integer.MAX_VALUE ? -1 : minSteps;
    }

    private void backtracking(String start, String end, String[] bank, int steps) {
        // 递归结束：更新最小步数并结束当前递归（一条符合基因变化的路径到了叶结点）
        if (start.equals(end)) {
            minSteps = Math.min(steps, minSteps);
            return;
        }

        // for循环扩展树的广度 每一层代表从一个基因序列开始变换
        for (String str : bank) {
            int diffNum = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) != start.charAt(i)) {
                    diffNum++;
                }
            }
            // 剪枝：判断当前bank中的基因是否可以由start基因变化而来
            // 从 start 到 str 不是有效的基因变化 || 之前的基因变化序列中已经用过了 str
            // 那么 bank 中当前基因是不能加入基因变化路径的 当前层直接尝试下一个基因
            if (diffNum != 1 || pathSet.contains(str)) {
                continue;
            }
            pathSet.add(str);
            steps++;
            // 扩展树的深度 递归进行下一次基因变化
            backtracking(str, end, bank, steps);
            steps--;
            // 回溯撤销下一层的影响 回到本层尝试bank中的其他基因
            pathSet.remove(str);
        }
    }
}
