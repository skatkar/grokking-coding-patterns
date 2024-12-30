package org.grokking.monotonic;

import java.util.Stack;

public class Leetcode739 {
    public int[] dailyTemperatures(int[] temperatures) {
        if(temperatures == null || temperatures.length == 0) return new int[]{};

        Stack<int[]> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for(int i=0; i < temperatures.length; i++) {

            while(!stack.isEmpty() && temperatures[i] > stack.peek()[0]) {
                int[] current = stack.pop();
                result[current[1]] = i - current[1];
            }
            stack.push(new int[]{temperatures[i], i});
        }

        return result;
    }
}
