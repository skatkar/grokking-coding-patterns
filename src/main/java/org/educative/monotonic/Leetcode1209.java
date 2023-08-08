package org.educative.monotonic;

import java.util.Stack;

public class Leetcode1209 {
    public String removeDuplicates(String s, int k) {
        if(s == null || s.length() == 0) return s;

        Stack<Object[]> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for(char ch : s.toCharArray()) {
            if(stack.isEmpty()) {
                stack.push(new Object[]{ch, 1});
            }else {
                char lastChar = (char)stack.peek()[0];
                int lastCount = (int) stack.peek()[1];
                if(lastChar == ch && lastCount < k - 1) {
                    stack.push(new Object[]{ch, lastCount + 1});
                }else if(lastChar == ch && lastCount == k - 1) {
                    int count = 0;
                    while(count != k - 1) {
                        stack.pop();
                        count++;
                    }
                }else if(lastChar != ch) {
                    stack.push(new Object[]{ch, 1});
                }
            }
        }

        while(!stack.isEmpty()) {
            Object[] item = stack.pop();
            result.append((char)item[0]);
        }

        return result.reverse().toString();
    }

    public String removeDuplicates2(String s, int k) {
        if(s == null || s.length() == 0) return s;
        StringBuilder sb = new StringBuilder();

        Stack<int[]> stack = new Stack<>();
        for(char ch : s.toCharArray()) {
            if(!stack.isEmpty() && stack.peek()[0] == ch) {
                stack.peek()[1]++;
            }else {
                stack.push(new int[]{ch, 1});
            }

            if(stack.peek()[1] == k){
                stack.pop();
            }
        }

        while(!stack.isEmpty()) {
            int[] pop = stack.pop();
            sb.append(String.valueOf((char)pop[0]).repeat(pop[1]));
        }

        return sb.reverse().toString();
    }
}
