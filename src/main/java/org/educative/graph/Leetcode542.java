package org.educative.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode542 {

    private Queue<int[]> initializeQueue(int[][] mat, int rows, int columns) {
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0; i < rows; i++) {
            for(int j=0; j < columns; j++) {
                if(mat[i][j] == 0){
                    queue.add(new int[]{i, j, 0});
                }
            }
        }
        return queue;
    }

    // BFS would help here as we will be moving level by level
    // DFS does not fit as it will go to the depth and will come back.
    // So,even though we are closer, we won't be tracking the shortest distance which is possible in BFS
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int columns = mat[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int[][] distance = new int[rows][columns];

        // 0 - row, 1 - column, 2 - distance from nearest 0
        // Step 1 -
        // Iterate through the matrix to find the existing 0s
        // This will be the first level
        Queue<int[]> queue = initializeQueue(mat, rows, columns);

        // Step 2 - Iterate through the queue and find out the next level cells.
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int column = current[1];
            int dist = current[2];

            if(row >= 0 && row < rows && column >= 0 && column < columns && !visited[row][column]) {
                // Step 3 - Mark the distance of the cell in a separate matrix
                distance[row][column] = dist;

                // Step 4 - Mark it visited
                visited[row][column] = true;

                // Step 5 - Now move to the 4 adjacent cells
                queue.add(new int[]{row + 1, column, dist + 1});
                queue.add(new int[]{row - 1, column, dist + 1});
                queue.add(new int[]{row, column - 1, dist + 1});
                queue.add(new int[]{row, column + 1, dist + 1});
            }
        }
        return distance;
    }
}
