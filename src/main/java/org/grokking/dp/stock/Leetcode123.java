package org.grokking.dp.stock;

public class Leetcode123 {
    public int maxProfit(int[] prices) {
        // return recursion(0, true, 2, prices);

        /*
        int rows = prices.length;
        int[][][] memo = new int[rows][2][3];
        for(int[][] subDp : memo) {
            for(int[] sub : subDp) {
                Arrays.fill(sub, -1);
            }
        }

        return memoization(0, 1, 2, memo, prices);
        */

        return dynamic(prices);
    }

    private int dynamic(int[] prices) {
        int rows = prices.length;
        int[][][] dp = new int[rows + 1][2][3];

        for(int index = rows - 1; index >= 0; index--) {
            for(int buy = 0; buy <= 1; buy++) {
                for(int cap = 1; cap <= 2; cap++) {
                    if(buy == 0){ // buy the stock
                        int pick = -prices[index] + dp[index + 1][1][cap];
                        int notPick = dp[index + 1][0][cap];
                        dp[index][buy][cap] = Math.max(pick, notPick);
                    }

                    if(buy == 1){ // sell the stock
                        int pick = prices[index] + dp[index + 1][0][cap - 1];
                        int notPick = dp[index + 1][1][cap];
                        dp[index][buy][cap] = Math.max(pick, notPick);
                    }
                }
            }
        }
        // whatever we passed as a starting pont for memoization or recursion
        // In recursion/memoization, our buy flag was 1 but over here we're considering buy at 0th index
        return dp[0][0][2];
    }

    private int memoization(int index, int canBuy, int cap, int[][][] memo, int[] prices){
        // Base cases
        if(cap == 0) return 0;
        if(index == prices.length) return 0;

        if(memo[index][canBuy][cap] != -1) return memo[index][canBuy][cap];

        if(canBuy == 1){
            // We bought this stock, turn the price to -
            // Similar to (7 - 3), if we bought at 3 and selling at price 7
            int pick = -prices[index] + memoization(index + 1, 0, cap, memo, prices);
            int notPick = memoization(index + 1, 1, cap, memo, prices);
            return memo[index][canBuy][cap] = Math.max(pick, notPick);
        }
        // Sell
        int pick = prices[index] + memoization(index + 1, 1, cap - 1, memo, prices);
        int notPick = memoization(index + 1, 0, cap, memo, prices);
        return memo[index][canBuy][cap] = Math.max(pick, notPick);
    }

    private int recursion(int index, boolean canBuy, int cap, int[] prices) {
        // Base cases
        if(cap == 0) return 0;
        if(index == prices.length) return 0;

        if(canBuy){
            // We bought this stock, turn the price to -
            // Similar to (7 - 3), if we bought at 3 and selling at price 7
            int pick = -prices[index] + recursion(index + 1, false, cap, prices);
            int notPick = recursion(index + 1, true, cap, prices);
            return Math.max(pick, notPick);
        }
        // Sell
        int pick = prices[index] + recursion(index + 1, true, cap - 1, prices);
        int notPick = recursion(index + 1, false, cap, prices);
        return Math.max(pick, notPick);
    }
}
