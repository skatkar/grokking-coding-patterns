package org.educative.islands;

import java.util.LinkedList;
import java.util.Queue;

public class IslandPerimeter {
    int rows;
    int columns;
    int[][] dirs = {
            {1,0},
            {0,1},
            {-1,0},
            {0,-1}
    };

    public static void main(String[] args) {
        IslandPerimeter question = new IslandPerimeter();
        int answer = question.islandPerimeter(new int[][]{
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        });

        System.out.println("answer = " + answer);
    }

    public int islandPerimeter(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;

        rows = grid.length;
        columns = grid[0].length;

        for(int i=0; i < rows; i++) {
            for(int j=0; j < columns; j++) {
                if(grid[i][j] == 1){
                    return bfs(grid, i, j);
                }
            }
        }

        return 0;
    }

    private int bfs(int[][] grid, int row, int column) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, column});
        int perimeter = 0;
        grid[row][column] = 2;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            int currRow = current[0];
            int currCol = current[1];

            for(int[] dir : dirs) {
                int newRow = currRow + dir[0];
                int newCol = currCol + dir[1];

                // touching the boundary
                if(newRow < 0 || newCol < 0|| newRow >= rows || newCol >= columns || grid[newRow][newCol] == 0){
                    perimeter++;
                }else if(grid[newRow][newCol] == 1){
                    grid[newRow][newCol] = 2;
                    queue.add(new int[]{newRow, newCol});
                }
            }
        }

        return perimeter;
    }
}
