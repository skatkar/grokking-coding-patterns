package org.educative.graph.traversal.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode1466 {
    public static void main(String[] args) {
        Leetcode1466 q = new Leetcode1466();
        int minReorder = q.minReorder(6, new int[][]{
                {0, 1},
                {1, 3},
                {2, 3},
                {4, 0},
                {4, 5}
        });
        System.out.println("minReorder = " + minReorder);
    }

    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<Pair>> adj = generateAdj(n, connections);
        boolean[] visited = new boolean[n];
        return dfs(0, adj, visited);
    }

    // If there is a from a to b, then we maintain two entries
    // a -> b with 1
    // b -> a with 0
    // 1 indicate that we need to reverse this path
    private Map<Integer, List<Pair>> generateAdj(int n, int[][] connections){
        Map<Integer, List<Pair>> adj = new HashMap<>();
        for(int[] connection : connections) {
            adj.computeIfAbsent(connection[0], k -> new ArrayList<>()).add(new Pair(connection[1], 1));
            adj.computeIfAbsent(connection[1], k -> new ArrayList<>()).add(new Pair(connection[0], 0));
        }
        return adj;
    }

    private int dfs(int currNode, Map<Integer, List<Pair>> adj, boolean[] visited) {
        int count = 0;
        visited[currNode] = true;
        for(Pair neighbor : adj.get(currNode)) {
            if(!visited[neighbor.node])
                count += neighbor.direction + dfs(neighbor.node, adj, visited);
        }

        return count;
    }

    class Pair{
        int node;
        // 1 - direction needs to be reversed
        int direction;

        public Pair(int node, int direction) {
            this.node = node;
            this.direction = direction;
        }
    }
}
