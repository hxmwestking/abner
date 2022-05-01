package org.example.abner.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

public class L22 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        recur("", 0, 0, n, res);
        return res;
    }

    private void recur(String str, int left, int right, int max, List<String> res) {
        if (left == max && right == max) {
            res.add(str);
            return;
        }
        if (left < max) {
            recur(str + "(", left + 1, right, max, res);
        }
        if (right < left) {
            recur(str + ")", left, right + 1, max, res);
        }
    }

    public static void main(String[] args) {
        L22 l22 = new L22();
        System.out.println(l22.generateParenthesis(3));
    }
}
