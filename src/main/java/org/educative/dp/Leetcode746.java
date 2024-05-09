package org.educative.dp;

import java.util.Arrays;

public class Leetcode746 {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return Math.min(helper(n - 1, cost, memo), helper(n - 1, cost, memo));
    }

    private int helper(int index, int[] cost, int[] memo) {
        if(index < 0) return 0;
        if(index == 0 || index == 1) return cost[index];

        if(memo[index] != -1) return memo[index];

        memo[index] = cost[index] + helper(index - 1, cost, memo) +
                helper(index - 2, cost, memo);
        return memo[index];
    }

}
