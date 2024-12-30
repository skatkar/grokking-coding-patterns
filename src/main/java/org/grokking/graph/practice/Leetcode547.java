package org.grokking.graph.practice;

import java.util.ArrayList;
import java.util.List;

public class Leetcode547 {
    private void dfs(int node, List<List<Integer>> adj, boolean[] visited){
        visited[node] = true;
        for(int neighbor : adj.get(node)) {
            if(!visited[neighbor])
                dfs(neighbor, adj, visited);
        }

    }

    public int findCircleNum(int[][] isConnected) {
        int cities = isConnected.length;
        boolean[] visited = new boolean[cities];
        int count = 0;

        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i <= cities; i++){
            adj.add(new ArrayList<>());
        }

        // to change adjacency matrix to list
        for(int i = 0;i<cities;i++) {
            for(int j = 0;j<cities;j++) {
                // self nodes are not considered
                if(isConnected[i][j] == 1 && i != j) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        for(int i=0; i <= cities; i++) {
            if(!visited[i]){
                count++;
                dfs(i, adj, visited);
            }
        }

        return count;
    }
}
