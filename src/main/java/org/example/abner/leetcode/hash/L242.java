package org.example.abner.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

public class L242 {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.get(c) < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        L242 l242 = new L242();
        System.out.println(l242.isAnagram("anagram", "nagaram"));
        System.out.println(l242.isAnagram("rat", "car"));
    }
}
