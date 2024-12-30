package org.grokking.kwaymerge;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Leetcode378 {

    public int kthSmallest(int[][] matrix, int k) {
        // Array structure:
        // 0th - value
        // 1st - row number
        // 2nd - column number
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int N = matrix.length;

        // First populate all the rows of the first column
        for(int r=0; r < Math.min(N,k); r++) {
            minHeap.add(new int[]{matrix[r][0], r, 0});
        }

        while(k > 1) {

            int[] minElement = minHeap.poll();
            int row = minElement[1];
            int column = minElement[2];

            // proceed to the next column of whatever came out of the minHeap.
            column++;

            if(column < N) {
                minHeap.add(new int[]{matrix[row][column], row, column});
            }

            k--;
        }


        return minHeap.poll()[0];
    }
}
