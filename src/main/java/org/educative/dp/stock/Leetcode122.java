package org.educative.dp.stock;

import java.util.Arrays;

public class Leetcode122 {
    public int maxProfit(int[] prices) {
        // return recursion(0, true, prices);
        int[][] memo = new int[prices.length][2];
        for(int[] sub : memo) {
            Arrays.fill(sub, -1);
        }

        return memoization(0, 1, prices, memo);
    }

    private int dynamic(int[] prices) {
        int rows = prices.length;
        int[][] dp = new int[rows + 1][2];
        for(int i=0; i < 2; i ++) {
            dp[prices.length][i] = 0;
        }

        for(int i=rows; i >= 0 ; i--) {
            for(int j=0; j < 2; j++) {
                if(j == 1) {
                    int pick = -prices[i - 1] + dp[i + 1][0];
                    int notPick = dp[i + 1][1];
                    dp[i][j] = Math.max(pick, notPick);
                }else {
                    int pick = prices[i - 1] + dp[i + 1][1];
                    int notPick = dp[i + 1][0];
                    dp[i][j] = Math.max(pick, notPick);
                }
            }
        }

        return dp[0][0];
    }

    private int memoization(int index, int canBuy, int[] prices, int[][] memo) {
        // Base case
        if(index >= prices.length)
            return 0;

        if(memo[index][canBuy] != -1) return memo[index][canBuy];

        if(canBuy == 1){
            // We bought this stock, turn the price to -
            // Similar to (7 - 3), if we bought at 3 and selling at price 7
            int pick = -prices[index] + memoization(index + 1, 0, prices, memo);
            int notPick = memoization(index + 1, 1, prices, memo);
            return memo[index][canBuy] = Math.max(pick, notPick);
        }
        // Sell
        int pick = prices[index] + memoization(index + 1, 1, prices, memo);
        int notPick = memoization(index + 1, 0, prices, memo);
        return memo[index][canBuy] = Math.max(pick, notPick);
    }

    private int recursion(int index, boolean canBuy, int[] prices) {
        // Base case
        if(index >= prices.length)
            return 0;

        if(canBuy){
            // We bought this stock, turn the price to -
            // Similar to (7 - 3), if we bought at 3 and selling at price 7
            int pick = -prices[index] + recursion(index + 1, false, prices);
            int notPick = recursion(index + 1, true, prices);
            return Math.max(pick, notPick);
        }
        // Sell
        int pick = prices[index] + recursion(index + 1, true, prices);
        int notPick = recursion(index + 1, false, prices);
        return Math.max(pick, notPick);
    }
}
