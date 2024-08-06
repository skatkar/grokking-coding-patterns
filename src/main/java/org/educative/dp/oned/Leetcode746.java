package org.educative.dp.oned;

import java.util.Arrays;

public class Leetcode746 {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);

        // We can get the min cost either due to 1 step jump or 2 step jump
        return Math.min(helper(n - 1, cost, memo), helper(n - 2, cost, memo));
    }

    private int helper(int index, int[] cost, int[] memo) {
        if(index < 0) return 0;
        if(index == 0 || index == 1) return cost[index];

        if(memo[index] != -1) return memo[index];

        memo[index] = cost[index] + Math.min(
                helper(index - 1, cost, memo),
                helper(index - 2, cost, memo));
        return memo[index];
    }

}
