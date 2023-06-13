package org.educative.islands;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Number of islands :<a href="https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6388cbb0765bb2154037ce84">...</a>
 * TC : O(m * n)
 * SC : O(min(m * n))
 */
public class NumberOfIslands {
    static int rows;
    static int columns;
    public static int countIslands(int[][] matrix) {
        rows = matrix.length;
        columns = matrix[0].length;
        int totalIslands = 0;

        for(int i=0; i < rows; i++) {
            for(int j=0; j < columns; j++){
                if(matrix[i][j] == 1){
                    totalIslands++;
                    validateBFS(matrix, i, j);
                }
            }
        }

        return totalIslands;
    }

    private static void validateBFS(int[][] matrix, int currRow, int currCol){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{currRow, currCol});

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int column = current[1];

            if(row >=0 && row < rows && column >= 0 && column < columns && matrix[row][column] == 1) {
                matrix[row][column] = -1;

                // insert all neighboring cells to the queue for BFS
                queue.add(new int[] { row + 1, column }); // lower cell
                queue.add(new int[] { row - 1, column }); // upper cell
                queue.add(new int[] { row, column + 1 }); // right cell
                queue.add(new int[] { row, column - 1 }); // left cell
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(NumberOfIslands.countIslands(
                new int[][] {
                        { 0, 1, 1, 1, 0 },
                        { 0, 0, 0, 1, 1 },
                        { 0, 1, 1, 1, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0 }
                }));

        System.out.println(NumberOfIslands.countIslands(
                new int[][] {
                        { 1, 1, 1, 0, 0 },
                        { 0, 1, 0, 0, 1 },
                        { 0, 0, 1, 1, 0 },
                        { 0, 0, 1, 0, 0 },
                        { 0, 0, 1, 0, 0 }
                }));
    }
}
