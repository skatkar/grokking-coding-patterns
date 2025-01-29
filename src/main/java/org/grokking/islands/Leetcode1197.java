package org.grokking.islands;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode1197 {
    public int minKnightMoves(int x, int y) {
        int M = 300;
        int[][] dirs = new int[][]{
                {2,1},
                {1,2},
                {2,-1},
                {-1,2},
                {-2,1},
                {1,-2},
                {-2, -1},
                {-1, -2}
        };

        Queue<int[]> queue = new LinkedList<>();
        // We are shifting coordinates from -300 to the right of 0
        // So, on right 0, there will be 600 coordinates instead of 300 on the left and 300 on right
        boolean[][] visited = new boolean[2 * M + 1][2 * M + 1];

        int minSteps = 0;
        queue.add(new int[]{0,0});

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i < size; i++) {
                int[] curr = queue.poll();
                int currX = curr[0], currY = curr[1];

                // Found the target
                if(currX == x && currY == y) return minSteps;

                for(int[] dir : dirs) {
                    int dx = currX + dir[0];
                    int dy = currY + dir[1];

                    // Beyond the limit
                    if(dx < -M || dx > M || dy < -M || dy > M || visited[dx + M][dy + M]) continue;

                    // Visit this new position
                    visited[dx + M][dy + M] = true;
                    queue.add(new int[]{dx, dy});
                }

            }
            minSteps++;
        }

        // As the questions state, it is guaranteed that the answer exists
        return minSteps;
    }
}
