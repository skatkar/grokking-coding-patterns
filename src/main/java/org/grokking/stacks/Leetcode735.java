package org.grokking.stacks;

import java.util.Arrays;
import java.util.Stack;

public class Leetcode735 {
    public static void main(String[] args) {
        Leetcode735 q = new Leetcode735();
        int[] asteroidCollision = q.asteroidCollision(new int[]{5, 10, -5});
        Arrays.stream(asteroidCollision)
                .forEach(i-> System.out.println("i = " + i));
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int a : asteroids) {
            if (a > 0) {
                stack.push(a);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -a) {
                    stack.pop();
                }

                if (stack.isEmpty() || stack.peek() < 0)  {
                    stack.push(a);
                }

                if (stack.peek() == -a) {
                    stack.pop();
                }
            }
        }

        int[] res = new int[stack.size()];
        int i = stack.size() - 1;

        while(!stack.isEmpty()) {
            res[i--] = stack.pop();
        }

        return res;
    }
}
