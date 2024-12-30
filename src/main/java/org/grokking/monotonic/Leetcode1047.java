package org.grokking.monotonic;

import java.util.Stack;

public class Leetcode1047 {
    public String removeDuplicates(String s) {
        if(s == null || s.length() == 0) return s;

        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(!stack.isEmpty() && stack.peek() == c){
                stack.pop();
            }else {
                stack.push(c);
            }
        }

        while(!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.reverse().toString();
    }
}
