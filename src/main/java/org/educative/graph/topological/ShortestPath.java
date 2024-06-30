package org.educative.graph.topological;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ShortestPath {
    /**
     * Link: <a href="https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1">...</a>
     * @param N
     * @param M
     * @param edges
     * @return
     */
    public int[] shortestPath(int N,int M, int[][] edges) {
        List<List<Pair>> adj = new ArrayList<>();

        // Phase 1 - Topology sort of the nodes
        // For each node, create an arraylist
        for(int i=0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        // For each edge, create a Pair
        for(int i=0; i < M; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            adj.get(u).add(new Pair(v, wt));
        }

        boolean[] visited = new boolean[N];
        Stack<Integer> stack = new Stack<>();

        for(int node=0; node < N; node++) {
            if(!visited[node]){
                topoSort(node, adj, visited, stack);
            }
        }

        // Phase 2 - Find out the minimum distance of each node
        int[] distance = new int[N];
        Arrays.fill(distance, (int) 1e9);

        // The distance of the start node should be.
        // If this start node is already provided as an argument, set its distance to be 0
        distance[0] = 0;
        // Pop out the nodes in topologically sorted order
        while(!stack.isEmpty()) {
            int current = stack.pop();
            for(Pair neighbor : adj.get(current)) {
                int v = neighbor.v;
                int wt = neighbor.weight;
                if(distance[current] + wt < distance[v]){
                    distance[v] = distance[current] + wt;
                }
            }
        }

        for(int i=0; i < N; i++) {
            if(distance[i] == (int)1e9)
                distance[i] = -1;
        }

        return distance;
    }

    // This is a topological sort using DFS
    private void topoSort(int node, List<List<Pair>> adj, boolean[] visited, Stack<Integer> stack){
        visited[node] = true;
        for(int i = 0; i < adj.get(node).size(); i++) {
            int v = adj.get(node).get(i).v;
            if(!visited[v]){
                topoSort(v, adj,visited, stack);
            }
        }

        stack.push(node);
    }

    class Pair {
        int v;
        int weight;

        public Pair(int weight, int v) {
            this.weight = weight;
            this.v = v;
        }
    }
}
