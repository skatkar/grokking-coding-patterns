package org.educative.dp.subsets;

import java.util.Arrays;

public class Knapsack01 {
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        //return recursion(n-1, weight, value, maxWeight);
        int[][] memo = new int[n][maxWeight + 1];
        for(int[] subarray : memo) {
            Arrays.fill(subarray, -1);
        }
        return memoization(n-1, weight, value, maxWeight, memo);
    }

    private static int dynamic(int[] weight, int[] value, int W, int n) {
        int[][] dp = new int[n][W + 1];

        // Base condition
        // Look at the memoization base condition
        // For all the weights from weight[0] to maxWeight
        for(int i=weight[0]; i <= W; i++) {
            dp[0][i] = value[0];
        }

        for(int index=1; index < n; index++){
            for(int wt=0; wt <= W; wt++){
                int pick = Integer.MIN_VALUE;
                if(weight[index] <= wt){
                    pick = value[index] + dp[index - 1][wt - weight[index]];
                }

                int notPick = dp[index - 1][wt];
                dp[index][wt] = Math.max(pick, notPick);
            }
        }


        return dp[n-1][W];
    }

    // Two indexes are changing. So, it is a DP problem with 2D matrix
    private static int memoization(int index, int[] weight, int[] value, int maxWeight, int[][] memo){
        if(index == 0) {
            if(maxWeight >= weight[0]) return value[0];
            else return 0;
        }

        if(memo[index][maxWeight] != -1) return memo[index][maxWeight];

        int pick = Integer.MIN_VALUE;
        if(weight[index] <= maxWeight){
            pick = value[index] + memoization(index-1, weight, value, maxWeight - weight[index], memo);
        }
        int notPick = memoization(index - 1, weight, value, maxWeight, memo);

        memo[index][maxWeight] = Math.max(pick, notPick);

        return memo[index][maxWeight];
    }

    private static int recursion(int index, int[] weight, int[] value, int maxWeight){

        // Base condition
        if(index == 0) {
            if(maxWeight >= weight[0]) return value[0];
            else return 0;
        }

        int pick = Integer.MIN_VALUE;
        if(weight[index] <= maxWeight){
            pick = value[index] + recursion(index - 1, weight, value, maxWeight - weight[index]);
        }
        int notPick = recursion(index - 1, weight, value, maxWeight);

        return Math.max(pick, notPick);
    }


    public static void main(String[] args) {

    }
}
