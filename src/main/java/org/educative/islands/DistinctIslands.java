package org.educative.islands;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class DistinctIslands {
    int rows, columns;

    public static void main(String[] args) {
        DistinctIslands questions = new DistinctIslands();
        int answer = questions.numDistinctIslands(new int[][]{
                {1,1,0,1,1},
                {1,0,0,0,0},
                {0,0,0,0,1},
                {1,1,0,1,1}
        });

        System.out.println("answer = " + answer);
    }

    /**
     * This is not a complete solution. It passed 722 out of 759 test cases on leetcode
     * @param grid
     * @return
     */
    public int numDistinctIslands(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;

        Set<String> traversalPaths = new HashSet<>();
        rows = grid.length;
        columns = grid[0].length;

        for(int i=0; i < rows; i++) {
            for(int j=0; j < columns; j++) {
                if(grid[i][j] == 1) {
                    String traversalPath = bfs(grid, i, j, 0);
                    traversalPaths.add(traversalPath);
                }
            }
        }

        return traversalPaths.size();
    }

    private String bfs(int[][] grid, int row, int col, int path) {
        StringBuilder result = new StringBuilder();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col, path});

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int currRow = current[0];
            int currCol = current[1];
            int currPath = current[2];

            if(currRow >=0 && currRow < rows && currCol >= 0 && currCol < columns && grid[currRow][currCol] == 1) {
                grid[currRow][currCol] = 2;
                result.append(currPath);

                queue.add(new int[]{currRow - 1, currCol, 1}); // UP
                queue.add(new int[]{currRow, currCol + 1, 2}); // right
                queue.add(new int[]{currRow + 1, currCol, 3}); // down
                queue.add(new int[]{currRow, currCol - 1, 4}); // left
            }
        }

        return result.toString();
    }
}
