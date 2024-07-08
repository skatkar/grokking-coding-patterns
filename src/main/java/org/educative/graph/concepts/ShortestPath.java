package org.educative.graph.concepts;

import java.util.*;

public class ShortestPath {
    // Problem statement: https://www.geeksforgeeks.org/problems/shortest-path-in-weighted-undirected-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=shortest-path-in-weighted-undirected-graph
    // The only difference is, we want to print the shortest path
    private List<List<Pair>> generateAdjacency(int n, int[][] edges) {
        List<List<Pair>> adj = new ArrayList<>();
        for(int i=0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(new Pair(edge[1], edge[2]));
            adj.get(edge[1]).add(new Pair(edge[0], edge[2]));
        }

        return adj;
    }

    private List<Integer> findParents(int n, int[] parent) {
        List<Integer> result = new ArrayList<>();
        int node = n;
        while(parent[node] != node) {
            result.add(node);
            node = parent[node];
        }
        result.add(1);
        return result;
    }

    public List<Integer> shortestPath(int n, int m, int[][] edges) {
        List<List<Pair>> adj = generateAdjacency(n, edges);

        int[] distance = new int[n + 1];

        int[] parent = new int[n + 1];
        for(int i=1; i < parent.length; i++) {
            distance[i] = (int)1e9;
            parent[i] = i;
        }
        distance[1] = 0; //distance of the start node from itself

        // 0 - distance, 1 - node
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, 1});

        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int currDistance = current[0];
            int node = current[1];

            for(Pair neighbor : adj.get(node)) {
                if(currDistance + neighbor.weight < distance[neighbor.node]){
                    distance[neighbor.node] = currDistance + neighbor.weight;
                    pq.add(new int[]{distance[neighbor.node], neighbor.node});
                    parent[neighbor.node] = node;
                }
            }
        }

        // If the path does not exist, return -1
        if(distance[n] == (int)1e9) return Arrays.asList(-1);

        List<Integer> result = findParents(n, parent);
        Collections.reverse(result);
        return result;
    }

    class Pair {
        int node;
        int weight;

        public Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
