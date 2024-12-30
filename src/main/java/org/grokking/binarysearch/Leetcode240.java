package org.grokking.binarysearch;

public class Leetcode240 {
    // Time complexity: O(m + n)
    // Space complexity: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;

        // We can start from a lower left corner or upper right corner only
        // if we start from upper left (0,0) - right and below both number will be greater than this number
        // if we start from lower right (m-1, n-1) - left and above will be smaller than this number
        int i = matrix.length - 1; // last row
        int j = 0;  // first column

        while(i >=0 && j < matrix[0].length) {
            if(matrix[i][j] == target) {
                return true;
            }else if(matrix[i][j] < target) {
                j++;
            }else {
                i--;
            }
        }

        return false;
    }
}
