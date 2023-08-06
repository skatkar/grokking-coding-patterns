package org.educative.stacks;

import java.util.Stack;

public class Leetcode227 {
    public static void main(String[] args) {
        Leetcode227 question = new Leetcode227();
        int answer = question.calculate(" 3+5 / 2 ");
        System.out.println("answer = " + answer);
    }

    public int calculate(String s) {
        int currentNumber = 0;
        char lastSign = '+';
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)) {
                currentNumber = currentNumber * 10 + (ch - '0');
            }

            if(!Character.isDigit(ch) && ch != ' ' || i == s.length() - 1) {
                if(lastSign == '+'){
                    stack.push(currentNumber);
                }else if(lastSign == '-') {
                    stack.push(-currentNumber);
                }else if(lastSign == '*') {
                    stack.push(stack.pop() * currentNumber);
                }else if(lastSign == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                lastSign = ch;
                currentNumber = 0;
            }
        }

        int result = 0;
        while(!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}
