package org.grokking.dp.practice;

public class Leetcode518 {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        //return recursion(n - 1, amount, coins);

        // Memoization
//        int[][] memo = new int[n][amount + 1];
//        for(int[] subArray : memo) {
//            Arrays.fill(subArray, -1);
//        }
//        return memoization(n - 1, amount, coins, memo);

        // Dynamic programming
        return dynamic(amount, coins);
    }

    private int dynamic(int amount, int[] coins){
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        // Base condition
        for(int currAmount=0; currAmount <= amount; currAmount++) {
            if(currAmount % coins[0] == 0){
                dp[0][currAmount] = 1;
            }else{
                dp[0][currAmount] = 0;
            }
        }

        // Populate the DP array
        for(int index=1; index < n; index++) {
            for(int currAmount=0; currAmount <= amount; currAmount++) {
                // Not Pick condition
                int notPick = dp[index - 1][currAmount];

                // Pick condition
                int pick = 0;
                if(coins[index] <= currAmount)
                    pick = dp[index][currAmount - coins[index]];

                dp[index][currAmount] = pick + notPick;
            }
        }

        return dp[n - 1][amount];
    }

    private int memoization(int index, int amount, int[] coins, int[][] memo) {
        if(index == 0){
            if(amount % coins[0] == 0)
                return 1;
            return 0;
        }

        if(memo[index][amount] != -1) return memo[index][amount];

        // Not Pick condition
        int notPick = memoization(index - 1, amount, coins, memo);

        // Pick condition
        int pick = 0;
        if(coins[index] <= amount)
            pick = memoization(index, amount - coins[index], coins, memo);

        memo[index][amount] = pick + notPick;
        return memo[index][amount];
    }

    private int recursion(int index, int amount, int[] coins) {

        if(index == 0){
            if(amount % coins[0] == 0)
                return 1;
            return 0;
        }


        // Not Pick condition
        int notPick = recursion(index - 1, amount, coins);

        // Pick condition
        int pick = 0;
        if(coins[index] <= amount)
            pick = recursion(index - 1, amount - coins[index], coins);

        return pick + notPick;
    }
}
