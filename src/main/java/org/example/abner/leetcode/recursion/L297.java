package org.example.abner.leetcode.recursion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class L297 {

    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            String str = rserialize(root, "");
            return "[" + str.substring(0, str.length() - 1) + "]";
        }


        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String str = data.substring(1, data.length() - 1);
            String[] dataArray = str.split(",");
            List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
            return rdeserialize(dataList);
        }

        private String rserialize(TreeNode root, String str) {
            if (root == null) {
                str += "null,";
            } else {
                str += root.val + ",";
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        private TreeNode rdeserialize(List<String> dataList) {
            if ("null".equals(dataList.get(0))) {
                dataList.remove(0);
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(dataList.get(0)));
            dataList.remove(0);
            root.left = rdeserialize(dataList);
            root.right = rdeserialize(dataList);
            return root;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        root.left = two;
        root.right = five;
        two.left = three;
        two.right = four;
        Codec codec = new Codec();
        System.out.println(codec.serialize(root));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
