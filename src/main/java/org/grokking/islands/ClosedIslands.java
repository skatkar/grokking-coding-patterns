package org.grokking.islands;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Question : <a href="https://leetcode.com/problems/number-of-closed-islands/description/">...</a>
 */
public class ClosedIslands {

    // 0 - land
    // 1 - water
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int count = 0;

        for(int i=0; i < rows; i++) {
            for(int j=0; j < columns; j++) {
                if(grid[i][j] == 0 && !visited[i][j] && bfs(grid, i, j, visited)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean bfs(int[][] grid, int i, int j, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i,j});
        boolean isClosed = true;

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();

            int row = curr[0];
            int col = curr[1];

            if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
                isClosed = false;
            }else if(grid[row][col] == 0 && !visited[row][col]) {
                visited[row][col] = true;

                queue.add(new int[]{row + 1, col});
                queue.add(new int[]{row - 1, col});
                queue.add(new int[]{row, col + 1});
                queue.add(new int[]{row, col - 1});
            }
        }

        return isClosed;
    }
}
