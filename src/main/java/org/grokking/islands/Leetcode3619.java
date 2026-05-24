package org.grokking.islands;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode3619 {
    public int countIslands(int[][] grid, int k) {
        int rows = grid.length, columns = grid[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int count = 0;
        for(int i=0; i < rows; i++) {
            for(int j=0; j < columns; j++) {
                if(grid[i][j] != 0 && !visited[i][j]){
                    int currSum = bfs(grid, i, j, visited);
                    if(currSum % k == 0){
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private int bfs(int[][] grid, int row, int column, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, column});
        int sum = 0;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int newRow = current[0], newColumn = current[1];
            if(newRow >= 0 && newRow < grid.length &&
                    newColumn >= 0 && newColumn < grid[0].length &&
                    !visited[newRow][newColumn] && grid[newRow][newColumn] != 0) {
                visited[newRow][newColumn] = true;
                sum += grid[newRow][newColumn];

                queue.add(new int[]{newRow + 1, newColumn});
                queue.add(new int[]{newRow, newColumn + 1});
                queue.add(new int[]{newRow - 1, newColumn});
                queue.add(new int[]{newRow, newColumn - 1});
            }
        }

        return sum;
    }
}
