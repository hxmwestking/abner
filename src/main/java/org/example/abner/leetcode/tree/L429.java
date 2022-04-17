package org.example.abner.leetcode.tree;

import java.util.*;

public class L429 {

    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < cnt; i++) {
                Node cur = queue.poll();
                level.add(cur.val);
                System.out.println("level add:" + cur.val);
                if (cur.children != null && cur.children.size() != 0)
                    for (Node child : cur.children) {
                        queue.offer(child);
                        System.out.println("queue offer:" + child.val);
                    }
            }
            ans.add(level);
        }
        return ans;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node three = new Node(3);
        Node two = new Node(2);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        root.children = Arrays.asList(three, two, four);
        three.children = Arrays.asList(five, six);
        two.children = Arrays.asList(seven);
        four.children = Arrays.asList(eight);
        L429 l429 = new L429();
        System.out.println(l429.levelOrder(root));
    }


    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
