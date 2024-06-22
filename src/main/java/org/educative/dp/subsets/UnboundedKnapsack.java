package org.educative.dp.subsets;

import java.util.Arrays;

public class UnboundedKnapsack {

    /** Unbounded knapsack
     * Link: <a href="https://www.naukri.com/code360/problems/unbounded-knapsack_1215029">...</a>
     * @param n
     * @param w
     * @param profit
     * @param weight
     * @return
     */
    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        int[][] memo = new int[n][w + 1];
        for(int[] subArray : memo) {
            Arrays.fill(subArray, -1);
        }
        // return recursion(n - 1, w, profit, weight);
        return memoization(memo, n - 1, w, profit, weight);
    }

    private static int memoization(int[][] memo, int index, int w, int[] profit, int[] weight) {
        // Base condition
        if(index == 0){
            return (w/weight[0]) * profit[0];
        }

        if(memo[index][w] != -1) return memo[index][w];

        // Typical take/not take a scenario, but we have unlimited supply here
        int notTake = memoization(memo, index - 1, w, profit, weight);
        int take = Integer.MIN_VALUE;
        if(weight[index] <= w){
            take = profit[index] + memoization(memo, index, w - weight[index], profit, weight);
        }

        memo[index][w] = Math.max(take, notTake);
        return memo[index][w];
    }

    private static int recursion(int index, int w, int[] profit, int[] weight) {

        // Base condition
        if(index == 0){
            return (w/weight[0]) * profit[0];
        }

        // Typical take/not take a scenario but we have unlimited supply here
        int notTake = recursion(index - 1, w, profit, weight);
        int take = Integer.MIN_VALUE;
        if(weight[index] <= w){
            take = profit[index] + recursion(index, w - weight[index], profit, weight);
        }

        return Math.max(take, notTake);
    }
}
