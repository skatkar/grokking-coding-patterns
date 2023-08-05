package org.educative.stacks;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Leetcode1249 {
    public static void main(String[] args) {
        Leetcode1249 question = new Leetcode1249();
        String toMakeValid = question.minRemoveToMakeValid("lee(t(c)o)de)");
        System.out.println("toMakeValid = " + toMakeValid);
    }

    public String minRemoveToMakeValid(String s) {
        if(s == null || s.length() == 0) return "";

        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        Set<Integer> indexesToRemove = new HashSet<>();

        for(int i=0; i < s.length(); i++) {
            if(s.charAt(i) == '(')
                stack.push(i); // found opening, push to the stack

            if(s.charAt(i) == ')'){
                if(stack.isEmpty()){
                    indexesToRemove.add(i); // found unbalanced, keep track of the index
                }else
                    stack.pop(); // found matching
            }
        }

        // stack is still not empty, there are unmatched parentheses
        while(!stack.isEmpty()){
            indexesToRemove.add(stack.pop());
        }

        for(int i=0; i < s.length(); i++){
            if(!indexesToRemove.contains(i)){
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}
