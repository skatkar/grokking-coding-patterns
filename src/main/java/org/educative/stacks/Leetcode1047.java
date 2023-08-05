package org.educative.stacks;

import java.util.Stack;

public class Leetcode1047 {
    public static void main(String[] args) {
        Leetcode1047 question = new Leetcode1047();
        String answer = question.removeDuplicates("azxxzy");
        System.out.println("answer = " + answer);
    }

    public String removeDuplicates(String s) {
        if(s == null || s.length() == 0) return s;

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(char ch : s.toCharArray()){
            if(!stack.isEmpty() && ch == stack.peek()){
                stack.pop();
            }else{
                stack.push(ch);
            }
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
