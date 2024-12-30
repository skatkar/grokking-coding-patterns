package org.grokking.binarysearch;

public class Leetcode1901 {

    // For a given column, find out a row having the highest number.
    private int findMaxElement(int[][] mat, int column) {
        int maxValue = -1, index = -1;
        for(int i=0; i < mat.length; i++) {
            if(mat[i][column] > maxValue){
                maxValue = mat[i][column];
                index = i;
            }
        }
        return index;
    }

    public int[] findPeakGrid(int[][] mat) {
        int columns = mat[0].length;
        int low = 0, high = columns - 1;

        while(low <= high) {
            int mid = low + (high - low)/2;

            // This will find out the maximum element vertically.
            int maxRowNumber = findMaxElement(mat, mid);

            // Now we need to check if the same number is greater than its horizontal neighbors or not.
            int left = mid > 0 ? mat[maxRowNumber][mid - 1] : -1; // as per the question, the outer area consists of -1
            int right = mid < columns - 1 ? mat[maxRowNumber][mid + 1] : -1;

            if(mat[maxRowNumber][mid] > left && mat[maxRowNumber][mid] > right) {
                return new int[]{maxRowNumber, mid};
            } else if(mat[maxRowNumber][mid] < left) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }

        return new int[]{-1, -1};
    }
}
