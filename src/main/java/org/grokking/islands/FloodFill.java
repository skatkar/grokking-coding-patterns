package org.grokking.islands;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {
    /**
     * Question : <a href="https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6388e8887b259e5c9e8c0274">...</a>
     * @param matrix
     * @param x
     * @param y
     * @param newColor
     * @return matrix with changed color
     */
    public static int[][] floodFill(int[][] matrix, int x, int y, int newColor) {
        if (matrix == null || matrix.length == 0 || matrix[x][y] == newColor) return matrix;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        int color = matrix[x][y];
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();

            int row = curr[0];
            int col = curr[1];

            if(row >= 0 && col >= 0 && row < matrix.length && col < matrix[0].length && matrix[row][col] == color) {
                matrix[row][col] = newColor;

                queue.add(new int[]{row + 1, col});
                queue.add(new int[]{row - 1, col});
                queue.add(new int[]{row, col + 1});
                queue.add(new int[]{row, col - 1});
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] answer = FloodFill.floodFill(
                new int[][] {
                        { 0, 1, 1, 1, 0 },
                        { 0, 0, 0, 1, 1 },
                        { 0, 1, 1, 1, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0 }
                }, 1, 3, 2);

        for(int i=0; i < answer.length; i++){
            for(int j=0; j < answer[0].length; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

}
