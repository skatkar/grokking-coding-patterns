package org.grokking.dp.stock;

import java.util.Arrays;
import java.util.List;

public class Leetcode309 {

    // Leetcode 122
    public int maxProfit(int[] prices, int fee) {
        // return recursion(0, true, prices, fee);
        // return dynamic(prices, fee);
        int[][] memo = new int[prices.length][2];
        for(int[] sub : memo) {
            Arrays.fill(sub, -1);
        }

        return memoization(0, 1, prices, memo, fee);
    }

    private int dynamic(int[] prices, int fee) {
        int rows = prices.length;
        int[][] dp = new int[rows + 1][2];
        for(int i=0; i < 2; i ++) {
            dp[rows][i] = 0;
        }

        for(int i=rows - 1; i >= 0 ; i--) {
            for(int buy=0; buy < 2; buy++) {
                if(buy == 0) { // buy the stock
                    dp[i][buy] = Math.max(dp[i + 1][0], -prices[i] + dp[i + 1][1]);
                }else if(buy == 1){ // sell the stock
                    dp[i][buy] = Math.max(dp[i + 1][1], prices[i] - fee + dp[i + 1][0]);
                }
            }
        }

        // here we started the recursion so returning the value at this index as an answer
        return dp[0][0];
    }

    private int memoization(int index, int canBuy, int[] prices, int[][] memo, int fee) {
        // Base case
        if(index >= prices.length)
            return 0;

        if(memo[index][canBuy] != -1) return memo[index][canBuy];

        if(canBuy == 1){
            // We bought this stock, turn the price to -
            // Similar to (7 - 3), if we bought at 3 and selling at price 7
            int pick = -prices[index] + memoization(index + 1, 0, prices, memo, fee);
            int notPick = memoization(index + 1, 1, prices, memo, fee);
            return memo[index][canBuy] = Math.max(pick, notPick);
        }
        // Sell
        int pick = prices[index] - fee + memoization(index + 1, 1, prices, memo, fee);
        int notPick = memoization(index + 1, 0, prices, memo, fee);
        return memo[index][canBuy] = Math.max(pick, notPick);
    }

    private int recursion(int index, boolean canBuy, List<Integer> profits, int[] prices) {
        // Base cases
        if(index == prices.length) return 0;

        if(canBuy){
            // We bought this stock, turn the price to -
            // Similar to (7 - 3), if we bought at 3 and selling at price 7
            int pick = -prices[index] + recursion(index + 1, false, profits, prices);
            int notPick = recursion(index + 1, true, profits, prices);
            int profit =  Math.max(pick, notPick);
            profits.add(profit);
            return profit;
        }
        // Sell
        int pick = prices[index] + recursion(index + 1, true, profits, prices);
        int notPick = recursion(index + 1, false, profits, prices);
        int profit =  Math.max(pick, notPick);
        profits.add(profit);
        return profit;
    }
}
