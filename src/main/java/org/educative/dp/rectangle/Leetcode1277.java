package org.educative.dp.rectangle;

public class Leetcode1277 {
    public int countSquares(int[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length;

        // Each cell of this 2-D determines the number of squares having right bottom at this cell
        int[][] dp = new int[rows][columns];
        int total = 0;

        // fill the first row and the first column as it is.
        for(int i=0; i < columns; i++) dp[0][i] = matrix[0][i];
        for(int i=0; i < rows; i++) dp[i][0] = matrix[i][0];

        // Look at the three neighboring cells and determine the current cell
        for(int i=1; i < rows; i++) {
            for(int j=1; j < columns; j++) {
                if(matrix[i][j] != 0) {
                    dp[i][j] = 1 + Math.min(dp[i][j-1],
                            Math.min(dp[i-1][j], dp[i-1][j-1]));
                }
            }
        }

        for(int i=0; i < rows; i++) {
            for(int j=0; j < columns;j++) {
                total += dp[i][j];
            }
        }

        return total;
    }
}
