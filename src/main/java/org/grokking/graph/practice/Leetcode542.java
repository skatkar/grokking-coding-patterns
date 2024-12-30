package org.grokking.graph.practice;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode542 {

    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int columns = mat[0].length;

        // Calculate the distance of each cell
        int[][] distance = new int[rows][columns];
        boolean[][] visited = new boolean[rows][columns];
        // 0th - row number
        // 1st - column number
        // 2nd - distance
        Queue<int[]> queue = initializeQueue(rows, columns, mat);

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int currRow = current[0];
            int currCol = current[1];
            int currDist = current[2];

            // Check if this polled row, column is within the bounds or not and it is not 0
            if(currRow >= 0 && currRow < rows && currCol >= 0 && currCol < columns &&
                    !visited[currRow][currCol]) {
                distance[currRow][currCol] = currDist;
                visited[currRow][currCol] = true;

                queue.add(new int[]{currRow + 1, currCol, currDist + 1});
                queue.add(new int[]{currRow - 1, currCol, currDist + 1});
                queue.add(new int[]{currRow, currCol + 1, currDist + 1});
                queue.add(new int[]{currRow, currCol - 1, currDist + 1});
            }
        }

        return distance;
    }

    private Queue<int[]> initializeQueue(int rows, int columns, int[][] mat) {
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
}
