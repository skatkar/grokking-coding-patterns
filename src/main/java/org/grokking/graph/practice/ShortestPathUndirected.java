package org.grokking.graph.practice;

import java.util.*;

public class ShortestPathUndirected {
    public int[] shortestPath(int[][] edges,int n,int m ,int src) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Now, populate the adjacency matrix
        for(int i=0; i < m; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);

        int[] distance = new int[n];
        Arrays.fill(distance, (int)1e9);
        distance[src] = 0;

        while(!queue.isEmpty()) {
            int node = queue.poll();
            for(int neighbor : adj.get(node)) {
                if(distance[node] + 1 < distance[neighbor]) {
                    distance[neighbor] = distance[node] + 1;
                    queue.add(neighbor);
                }
            }
        }

        for(int i=0; i < distance.length; i++) {
            if(distance[i] == 1e9) {
                distance[i] = -1;
            }
        }

        return distance;
    }
}
