package org.grokking.tree.bfs.practice;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode2101 {
    public static void main(String[] args) {
        Leetcode2101 q = new Leetcode2101();
        int maximumDetonation = q.maximumDetonation(new int[][]{
                {1, 1, 100000},
                {100000, 100000, 1}
        });

        System.out.println("maximumDetonation = " + maximumDetonation);
    }

    public int maximumDetonation(int[][] bombs) {
        int maxDentoneCount = 0;

        for(int i=0; i < bombs.length; i++) {
            maxDentoneCount = Math.max(maxDentoneCount, bfs(bombs, i));
        }

        return maxDentoneCount;
    }

    // Given an index, this method will try to find out all the bombs which can be reached directly or indirectly from this index.
    private int bfs(int[][] bombs, int bombIndex){
        int detoneCount = 1;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[bombs.length];

        queue.add(bombIndex);
        visited[bombIndex] = true;

        while(!queue.isEmpty()) {
            int currBombIndex = queue.poll();
            for(int i=0; i < bombs.length; i++) {
                if(!visited[i] && isInRange(bombs[currBombIndex], bombs[i])){
                    visited[i] = true;
                    detoneCount++;
                    queue.add(i);
                }
            }
        }

        return detoneCount;
    }

    // This will check whether the point2 is in range from point1.
    // We won't do point2 to point1 as of now because that will be done later once we are iterating over that point later.
    private boolean isInRange(int[] point1, int[] point2) {
        int x1 = point1[0];
        int y1 = point1[1];
        int r1 = point1[2];

        int x2 = point2[0];
        int y2 = point2[1];

        int dx = x2 - x1;
        int dy = y2 - y1;
        int distance = dx * dx + dy * dy;

        return r1 * r1 >= distance;
    }
}
