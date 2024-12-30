package org.grokking.dp.twod;

import java.util.Arrays;

public class Leetcode63 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length, columns = obstacleGrid[0].length;
        int[][] dp = new int[rows][columns];
        if(obstacleGrid[0][0] != 1) dp[0][0] = 1;

        for(int i=0; i < rows; i++){
            for(int j=0; j < columns; j++) {
                if(i == 0 && j == 0) continue;

                if(obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }

                int up = 0, right = 0;
                if(i != 0) {
                    up = dp[i - 1][j];
                }

                if(j != 0){
                    right = dp[i][j - 1];
                }

                dp[i][j] = up + right;
            }
        }

        return dp[rows - 1][columns - 1];
    }
    public int uniquePathsWithObstacles_memo(int[][] obstacleGrid) {
        int rows = obstacleGrid.length, columns = obstacleGrid[0].length;
        int[][] memo = new int[rows][columns];
        for(int[] sub : memo) {
            Arrays.fill(sub, -1);
        }
        return helper_memo(rows - 1, columns - 1,memo, obstacleGrid);
    }

    private int helper_memo(int row, int column, int[][] memo, int[][] obstacleGrid) {
        // Just one more boundary condition to Leetcode 62
        if(row >=0 && column >= 0 && obstacleGrid[row][column] == 1) return 0;


        // Exactly the copy paste of Leetcode 62
        if(row < 0 || column < 0) return 0;
        if(memo[row][column] != -1) return memo[row][column];

        if(row == 0 && column == 0) {
            memo[0][0] = 1;
            return memo[0][0];
        }
        // Going up
        int up = helper_memo(row - 1, column, memo, obstacleGrid);

        // Going right
        int right = helper_memo(row, column - 1, memo, obstacleGrid);

        memo[row][column] = up + right;

        return memo[row][column];
    }
}
