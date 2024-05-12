package org.educative.dp.twod;

import java.util.Arrays;
import java.util.List;

public class Leetcode120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();

        // Extra row and column will be just a placeholders
        int[][] dp = new int[rows + 1][rows + 1];

        // Populate the rest of the DP array
        for(int rowIndex=rows - 1; rowIndex >= 0; rowIndex--) {
            for(int columnIndex=rowIndex; columnIndex >= 0; columnIndex--){
                int current = triangle.get(rowIndex).get(columnIndex);

                int down = current + dp[rowIndex + 1][columnIndex];
                int right = current + dp[rowIndex + 1][columnIndex + 1];


                dp[rowIndex][columnIndex] = Math.min(down, right);
            }
        }

        return dp[0][0];
    }
    public int minimumTotal_memo(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int columns = triangle.get(rows - 1).size();
        int[][] memo = new int[rows][columns];
        for(int[] sub : memo) {
            Arrays.fill(sub, -1);
        }

        return memoization(0, 0, memo, triangle);
    }
    public int minimumTotal2(List<List<Integer>> triangle) {
        return recursion(0, 0, triangle);
    }

    private int memoization(int rowIndex, int columnIndex, int[][] memo, List<List<Integer>> triangle) {
        // Boundary condition
        if(rowIndex >= triangle.size() || columnIndex >= triangle.get(rowIndex).size() ) return 0;
        if(rowIndex == triangle.size() - 1) return triangle.get(rowIndex).get(columnIndex);

        if(memo[rowIndex][columnIndex] != -1) return memo[rowIndex][columnIndex];

        int down = triangle.get(rowIndex).get(columnIndex) + memoization(rowIndex + 1, columnIndex, memo, triangle);
        int right = triangle.get(rowIndex).get(columnIndex) + memoization(rowIndex + 1, columnIndex + 1, memo, triangle);

        memo[rowIndex][columnIndex] = Math.min(down, right);

        return memo[rowIndex][columnIndex];
    }

    private int recursion(int rowIndex, int columnIndex, List<List<Integer>> triangle) {
        // Boundary condition
        if(rowIndex >= triangle.size() || columnIndex >= triangle.get(rowIndex).size() ) return 0;
        if(rowIndex == triangle.size() - 1) return triangle.get(rowIndex).get(columnIndex);

        int down = triangle.get(rowIndex).get(columnIndex) + recursion(rowIndex + 1, columnIndex, triangle);
        int right = triangle.get(rowIndex).get(columnIndex) + recursion(rowIndex + 1, columnIndex + 1, triangle);

        return Math.min(down, right);
    }
}
