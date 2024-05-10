package org.educative.dp.oned;

import java.util.Arrays;

public class Leetcode1137 {
    public static void main(String[] args) {
        Leetcode1137 q = new Leetcode1137();
        int answer = q.tribonacci(25);
        System.out.println("answer = " + answer);
    }

    public int tribonacci(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return helper(n, memo);
    }

    private int helper(int n, int[] memo) {
        if(n == 0) return 0;
        if(n == 1 || n == 2) return 1;

        if(memo[n] != -1) return memo[n];

        memo[n] = helper(n - 1, memo) + helper(n - 2, memo) + helper(n - 3, memo);
        return memo[n];
    }
}
