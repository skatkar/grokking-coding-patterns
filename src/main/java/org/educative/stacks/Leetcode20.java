package org.educative.stacks;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Leetcode20 {

    public static void main(String[] args) {
        Leetcode20 question = new Leetcode20();
        boolean valid = question.isValid("{[]}");
        System.out.println("valid = " + valid);
    }

    public boolean isValid(String s) {

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (stack.isEmpty() || !map.containsKey(ch)) {
                stack.push(ch);
            } else {
                char expectedChar = map.get(ch);
                if (expectedChar == stack.peek())
                    stack.pop();
                else
                    return false;
            }
        }

        return stack.isEmpty();
    }
}
