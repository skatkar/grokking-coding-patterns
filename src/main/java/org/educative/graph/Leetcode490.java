package org.educative.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode490 {
    int m, n;
    int[][] dirs;
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if(maze == null || maze.length == 0) return false;

        m = maze.length;
        n = maze[0].length;

        //// BFS approach
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1]});

        // For each position, we would try to go up, dow, left, right as long as there is no blocker
        dirs = new int[][]{
                {-1,0}, // UP
                {1,0},  // DOWN
                {0,1},  // RIGHT
                {0,-1}  // LEFT
        };

        // Mark this position as visited
        maze[start[0]][start[1]] = 2;

        while(!queue.isEmpty()) {
            int[] poll = queue.poll();

            for(int[] dir : dirs) {
                int r = poll[0];
                int c = poll[1];

                // For a given location and the current direction, start going as much as we can
                // unless we are withing the bounds and that node is not a wall
                while(r >= 0 && r < m && c >= 0 && c < n && maze[r][c] != 1) {
                    r = r + dir[0];
                    c = c + dir[1];
                }

                // Now, need to step back as we went beyond the limits
                r = r - dir[0];
                c = c - dir[1];

                // Only if this is not visited
                if(maze[r][c] != 2){
                    if(r == destination[0] && c == destination[1])
                        return true;
                    maze[r][c] = 2;
                    queue.add(new int[]{r,c});
                }
            }
        }

        return false;
    }

    public boolean hasPathDFS(int[][] maze, int[] start, int[] destination) {
        if(maze == null || maze.length == 0) return false;

        m = maze.length;
        n = maze[0].length;
        dirs = new int[][]{
                {-1,0}, // UP
                {1,0},  // DOWN
                {0,1},  // RIGHT
                {0,-1}  // LEFT
        };
        
        return dfs(maze, start, destination);
    }

    private boolean dfs(int[][] maze, int[] start, int[] destination) {
        if(start[0] == destination[0] && start[1] == destination[1])
            return true;

        maze[start[0]][start[1]] = 2;

        for(int[] dir : dirs) {
            int r = start[0];
            int c = start[1];

            while(r >= 0 && r < m && c >= 0 && c < n && maze[r][c] != 1) {
                r = r + dir[0];
                c = c + dir[1];
            }
            r = r - dir[0];
            c = c - dir[1];

            if(maze[r][c] != 2 && dfs(maze, new int[]{r,c}, destination))
                return true;
        }

        return false;
    }


}
