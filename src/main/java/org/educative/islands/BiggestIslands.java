package org.educative.islands;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Question : <a href="https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6388d8940cc1849dcbc27fe3">...</a>
 */
public class BiggestIslands {
    // TC : O(m * n)
    // SC : O(m * n)
    public static int maxAreaOfIsland(int[][] matrix) {
        int biggestIslandArea = 0;

        for(int i=0; i < matrix.length; i++) {
            for(int j=0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 1) {
                    biggestIslandArea = Math.max(biggestIslandArea, countByBFS(matrix, i, j));
                }
            }
        }
        return biggestIslandArea;
    }

    private static int countByBFS(int[][] matrix, int currRow, int currCol) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{currRow, currCol});
        int noOfOnes = 0;

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];

            if(row >=0 && row < matrix.length && col >= 0 && col < matrix[0].length && matrix[row][col] == 1) {
                matrix[row][col] = -1;
                noOfOnes++;

                queue.add(new int[]{row + 1, col});
                queue.add(new int[]{row - 1, col});
                queue.add(new int[]{row, col + 1});
                queue.add(new int[]{row, col - 1});
            }
        }

        return noOfOnes;
    }

    public static void main(String[] args) {
        System.out.println(BiggestIslands.maxAreaOfIsland(
                new int[][] {
                        { 1, 1, 1, 0, 0 },
                        { 0, 1, 0, 0, 1 },
                        { 0, 0, 1, 1, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 1, 0, 0 }
                }));
    }
}
