package org.example.abner.leetcode.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class L20 {

    public Map<Character, Character> getMap() {
        Map<Character, Character> map = new HashMap<>(8);
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        return map;
    }

    public boolean isValid(String s) {
        Map<Character, Character> map = getMap();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.size() == 0) {
                    return false;
                }
                Character pop = stack.pop();
                Character character = map.get(pop);
                if (character != c) {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        L20 l20 = new L20();
        String s = "()";
        System.out.println(l20.isValid(s));
        s = "()[]{}";
        System.out.println(l20.isValid(s));
        s = "(]";
        System.out.println(l20.isValid(s));
        s = "([)]";
        System.out.println(l20.isValid(s));
        s = "{[]}";
        System.out.println(l20.isValid(s));
        s = "]";
        System.out.println(l20.isValid(s));
    }
}
