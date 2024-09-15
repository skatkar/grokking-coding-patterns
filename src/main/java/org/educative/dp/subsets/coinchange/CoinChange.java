package org.educative.dp.subsets.coinchange;

public class CoinChange {
    public static void main(String[] args) {
        CoinChange q = new CoinChange();
        int countChange = q.countChange(new int[]{1, 2, 5}, 5);
        System.out.println(countChange);
    }

    /**
     * Ref:= <a href="https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/coin-change">...</a>
     * Given an infinite supply of ‘n’ coin denominations and a total money amount,
     * we are asked to find the total number of distinct ways to make up that amount.
     * @param denominations
     * @param total
     * @return
     */
    public int countChange(int[] denominations, int total) {
        /*
        int n = denominations.length;
        int[][] memo = new int[n][total + 1];
        for(int[] sub :memo) {
            Arrays.fill(sub, -1);
        }

        return memoization(denominations, total, n - 1, memo);
        */
        return dynamic(denominations, total);
    }

    private int dynamic(int[] denominations, int total) {
        int n = denominations.length;
        int[][] dp = new int[n + 1][total + 1];

        // if(total == 0) return 1; condition
        for(int row=0; row <= n; row++) {
            dp[row][0] = 1;
        }

        for(int index = 1; index <= n; index++){
            for(int currTotal = 1; currTotal <= total; currTotal++){
                int notPick = dp[index - 1][currTotal];
                int pick = 0;
                if(denominations[index - 1] <= currTotal){
                    pick = dp[index][currTotal - denominations[index - 1]];
                }
                dp[index][currTotal] = pick + notPick;
            }
        }
        return dp[n][total];
    }

    private int memoization(int[] denominations, int total, int index, int[][] memo) {
        // Base condition
        if(total == 0) return 1;
        if(index < 0) return 0;

        if(memo[index][total] != -1) return memo[index][total];
        int notPick = memoization(denominations, total, index - 1, memo);
        int pick = 0;
        if(denominations[index] <= total){
            pick = memoization(denominations, total - denominations[index], index, memo);
        }

        memo[index][total] = pick + notPick;
        return memo[index][total];
    }
}
