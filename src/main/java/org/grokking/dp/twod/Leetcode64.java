package org.grokking.dp.twod;

import java.util.Arrays;

public class Leetcode64 {

    public int minPathSum(int[][] grid) {
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];

        dp[0][0] = grid[0][0];

        for(int i=0; i < rows; i++) {
            for(int j=0; j < columns; j++) {
                if(i == 0 && j == 0) continue;

                // If we are going beyond the boundary, then we should cancel that path
                // To cancel it, return some big number but not Integer.MAX_VALUE
                // Either of the i or j is in negatives
                int up = 100000, left = 100000;
                if(i != 0) up = grid[i][j] + dp[i - 1][j];
                if(j != 0) left = grid[i][j] + dp[i][j - 1];

                dp[i][j] = Math.min(up, left);
            }
        }

        return dp[rows - 1][columns - 1];
    }
    public int minPathSum2(int[][] grid) {
        int rows = grid.length, columns = grid[0].length;
        int[][] memo = new int[rows][columns];
        for(int[] sub : memo) {
            Arrays.fill(sub, -1);
        }
        // return recursion(rows - 1, columns - 1, grid);
        return memo(rows - 1, columns - 1, memo, grid);
    }


    private int memo(int row, int column, int[][] memo, int[][] grid) {
        // If we are going beyond the boundary, then we should cancel that path
        // To cancel it, return some big number but not Integer.MAX_VALUE
        if(row < 0 || column < 0) return 100000;
        if(row == 0 && column == 0) {
            memo[0][0] = grid[0][0];
            return memo[0][0];
        }

        if(memo[row][column] != -1) return memo[row][column];

        int up = grid[row][column] + memo(row - 1, column, memo, grid);
        int left = grid[row][column] + memo(row, column - 1, memo, grid);

        memo[row][column] = Math.min(up, left);
        return memo[row][column];
    }

    private int recursion(int row, int column, int[][] grid) {
        // If we are going beyond the boundary, then we should cancel that path
        // To cancel it, return some big number but not Integer.MAX_VALUE
        if(row < 0 || column < 0) return 100000;
        if(row == 0 && column == 0) return grid[0][0];

        int up = grid[row][column] + recursion(row - 1, column, grid);
        int left = grid[row][column] + recursion(row, column - 1, grid);

        return Math.min(up, left);
    }
}
