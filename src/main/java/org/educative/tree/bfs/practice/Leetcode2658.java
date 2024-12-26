package org.educative.tree.bfs.practice;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode2658 {
    private int[][] dirs = new int[][]{
            {-1,0}, // UP
            {0, 1}, // RIGHT
            {1,0},  // DOWN
            {0,-1}  // LEFT
    };

    public static void main(String[] args) {
        Leetcode2658 question = new Leetcode2658();
        int maxFish = question.findMaxFish(new int[][]{
                {0, 2, 1, 0},
                {4, 0, 0, 3},
                {1, 0, 0, 4},
                {0, 3, 2, 0}
        });
        System.out.println("maxFish = " + maxFish);
    }

    public int findMaxFish(int[][] grid) {
        int totalCount = 0;
        for(int i=0; i < grid.length; i++) {
            for(int j=0; j < grid[0].length; j++) {
                if(grid[i][j] > 0) {
                    totalCount = Math.max(totalCount, bfs(grid, new int[]{i,j}));
                }
            }
        }

        return totalCount;
    }

    private int bfs(int[][] grid, int[] startPosition) {
        Queue<int[]> queue = new LinkedList<>();
        int count = 0;
        queue.add(startPosition);

        // marking the starting position as visited
        count += grid[startPosition[0]][startPosition[1]];
        grid[startPosition[0]][startPosition[1]] = -1;

        while(!queue.isEmpty()) {
            int[] currPosition = queue.poll();
            int row = currPosition[0];
            int column = currPosition[1];

            for(int[] dir : dirs) {
                int newRow = row + dir[0];
                int newColumn = column + dir[1];
                if (newRow >= 0 && newRow < grid.length &&
                        newColumn >= 0 && newColumn < grid[0].length &&
                        grid[newRow][newColumn] > 0) {
                    count += grid[newRow][newColumn];
                    grid[newRow][newColumn] = -1;
                    queue.add(new int[]{newRow, newColumn});
                }
            }
        }
        return count;
    }
}
