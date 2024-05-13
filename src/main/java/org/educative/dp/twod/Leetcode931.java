package org.educative.dp.twod;

import java.util.Arrays;

public class Leetcode931 {
    public int minFallingPathSum(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for(int j=0; j < columns; j++) {
            dp[0][j] = matrix[0][j];
        }

        for(int rowIndex=1; rowIndex < rows; rowIndex++) {
            for(int columnIndex=0; columnIndex < columns; columnIndex++) {
                int up = matrix[rowIndex][columnIndex] + dp[rowIndex - 1][columnIndex];
                int left = matrix[rowIndex][columnIndex];

                if(columnIndex - 1 >= 0){
                    left += dp[rowIndex - 1][columnIndex - 1];
                }else{
                    left += 100000;
                }

                int right = matrix[rowIndex][columnIndex];
                if(columnIndex + 1 < columns){
                    right += dp[rowIndex - 1][columnIndex + 1];
                }else{
                    right += 10000;
                }
                dp[rowIndex][columnIndex] = Math.min(up, Math.min(left, right));
            }
        }

        for(int column=0; column < columns; column++) {
            min = Math.min(min, dp[rows - 1][column]);
        }
        return min;
    }
    public int minFallingPathSum2(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] memo = new int[rows][columns];
        for(int[] sub : memo) {
            Arrays.fill(sub, -1);
        }

        for(int i=0; i < columns; i++) {
            min = Math.min(min, memoization(0, i, memo, matrix));
        }
        return min;
    }

    private int memoization(int rowIndex, int columnIndex, int[][] memo, int[][] matrix) {
        // Boundary condition
        if(rowIndex >= matrix.length || rowIndex < 0 ||
                columnIndex >= matrix[0].length || columnIndex < 0 ) return 100000;
        if(rowIndex == matrix.length - 1) return matrix[rowIndex][columnIndex];

        if(memo[rowIndex][columnIndex] != -1) return memo[rowIndex][columnIndex];

        int down = matrix[rowIndex][columnIndex] + memoization(rowIndex + 1, columnIndex, memo, matrix);
        int left = matrix[rowIndex][columnIndex] + memoization(rowIndex + 1, columnIndex - 1, memo, matrix);
        int right = matrix[rowIndex][columnIndex] + memoization(rowIndex + 1, columnIndex + 1, memo, matrix);

        memo[rowIndex][columnIndex] = Math.min(down, Math.min(left,right));
        return memo[rowIndex][columnIndex];
    }
}
