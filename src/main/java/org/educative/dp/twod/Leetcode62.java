package org.educative.dp.twod;

import java.util.Arrays;

public class Leetcode62 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // 1. Check the recursion logic & identify the base case. Initialize that position in dp array
        dp[0][0] = 1;
        for(int i=0; i < m ; i++) {
            for(int j=0; j < n; j++) {
                if(i == 0 && j == 0) continue;

                int up = 0, right = 0;

                // 2. Convert the first recursive call to dp array invocation
                if(i != 0){
                    up = dp[i - 1][j];
                }

                // 3. Convert the second recursive call to dp array invocation
                if(j != 0){
                    right = dp[i][j - 1];
                }

                // 4. As per the recursive solution, sum them up.
                dp[i][j] = up + right;
            }
        }

        // 5. We started from this location while working on the recursive solution.
        return dp[m - 1][n - 1];
    }

    // memoization approach
    public int uniquePaths_memo(int m, int n) {
        int[][] memo = new int[m][n];
        for(int[] sub : memo) {
            Arrays.fill(sub, -1);
        }
        return helper_memo(m - 1, n - 1, memo);
    }

    // To down approach - recursive
    public int uniquePaths_rec(int m, int n) {
        return helper_rec(m - 1, n - 1);
    }

    private int helper_memo(int row, int column, int[][] memo) {
        if(row < 0 || column < 0) return 0;
        if(memo[row][column] != -1) return memo[row][column];

        if(row == 0 && column == 0) {
            memo[0][0] = 1;
            return memo[0][0];
        }
        // Going up
        int up = helper_memo(row - 1, column, memo);

        // Going right
        int right = helper_memo(row, column - 1, memo);

        memo[row][column] = up + right;

        return memo[row][column];
    }

    private int helper_rec(int row, int column) {
        if(row == 0 && column == 0) return 1;

        if(row < 0 || column < 0) return 0;

        // Going up
        int up = helper_rec(row - 1, column);

        // Going right
        int right = helper_rec(row, column - 1);

        return up + right;
    }
}
