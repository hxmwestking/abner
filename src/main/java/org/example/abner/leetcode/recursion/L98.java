package org.example.abner.leetcode.recursion;


import java.util.Deque;
import java.util.LinkedList;

public class L98 {

    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stk = new LinkedList<>();
        int inorder = Integer.MIN_VALUE;
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            if (inorder >= root.val) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
