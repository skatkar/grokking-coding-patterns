package org.grokking.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class Leetcode2643 {
    // Problem statement: https://www.naukri.com/code360/problems/row-with-maximum-1-s_1112656?utm_source=youtube&utm_medium=affiliate&utm_campaign=codestudio_Striver_BinarySeries
    public static int rowMaxOnes(ArrayList<ArrayList<Integer>> mat, int rows, int columns) {
        int cnt_max = 0, index = -1;
        for(int i=0; i < rows; i++) {
            int cnt_ones = columns - getLowerBoundOfOnes(mat.get(i), columns);
            if(cnt_ones > cnt_max) {
                cnt_max = cnt_ones;
                index = i;
            }
        }

        return index;
    }

    private static int getLowerBoundOfOnes(List<Integer> arr, int columns) {
        int low = 0, high = columns - 1;
        int lowerBound = columns;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(arr.get(mid) >= 1){
                lowerBound = mid;
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }

        return lowerBound;
    }

    // Problem statement - https://leetcode.com/problems/row-with-maximum-ones/description/
    public int[] rowAndMaximumOnes(int[][] mat) {
        // 0th index => Index of the row
        // 1st index => number of ones in it.
        int[] result = new int[]{0,0};
        for(int i=0; i < mat.length; i++) {
            int count = 0;
            for(int j=0; j < mat[0].length; j++) {
                if(mat[i][j] == 1) count++;
            }

            if(count > result[1]){
                result[0] = i;
                result[1] = count;
            }
        }

        return result;
    }
}
