package org.educative.binarysearch;

public class Leetcode74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;

        int m = matrix.length, n = matrix[0].length;

        int low = 0, high = m * n - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;
            int row = mid / n, col = mid % n;

            if(target == matrix[row][col]) return true;
            else if(target < matrix[row][col]) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }

        return false;
    }
}
