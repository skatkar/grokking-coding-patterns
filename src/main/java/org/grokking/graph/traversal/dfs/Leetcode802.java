package org.grokking.graph.traversal.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leetcode802 {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // Any node having a cycle or leading to a cycle can't be a safe node ever
        // We need to keep track of this. So, we are using 3 different statuses in the visited array.
        // 0 - unvisited
        // 1 - visiting/currently working on this node
        // -1 - visited this, and it is a terminal node
        int[] visited = new int[graph.length];
        List<Integer> result = new ArrayList<>();
        for(int i=0; i < graph.length; i++) {
            if(dfs(i, graph, visited)){
                result.add(i);
            }
        }

        Collections.sort(result);
        return result;
    }

    private boolean dfs(int node, int[][] graph, int[] visited){
        if(visited[node] == -1) return true;
        if(visited[node] == 1) return false;

        // started processing the current node
        // setting it to 1 will help in finding the cycle
        visited[node] = 1;
        for(int neighbor : graph[node]) {
            if(!dfs(neighbor, graph, visited)){
                return false;
            }
        }
        // After traversing all the neighbors, we identified that there is no cycle.
        // So, marking it as a safe node.
        // It may be a terminal node itself or reaching to a terminal node.
        visited[node] = -1;
        return true;
    }
}
