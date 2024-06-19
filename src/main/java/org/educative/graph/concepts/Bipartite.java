package org.educative.graph.concepts;

import java.util.Arrays;

public class Bipartite {
    int[] colors;
    public boolean isBipartite(int[][] graph) {
        if(graph == null || graph.length == 0) return false;

        int n = graph.length;
        colors = new int[n];
        Arrays.fill(colors, -1);

        // The graph might be disconnected so check all the nodes
        for(int node=0; node < graph.length; node++) {
            if(colors[node] == -1 && !visited(graph, node, 0)){
                return false;
            }
        }
        return true;
    }

    private boolean visited(int[][] graph, int node, int color) {
        int expectedColor = color == 0 ? 1 : 0;

        // If this node is already visited
        if(colors[node] != -1) {
            // Then check if it has the same color as the expected one.
            // The expected one is passed as an argument and it is decided by its parent.
            return colors[node] == expectedColor;
        }

        colors[node] = expectedColor;

        for(int child : graph[node]) {
            if(!visited(graph, child, colors[node])){
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int currNode){
        if(colors[currNode] != -1){
            for(int child=0; child < graph[currNode].length; child++) {
                if(colors[child] != -1) {
                    if(colors[child] == colors[currNode]){
                        return false;
                    }
                }else{
                    colors[child] = colors[currNode] == 0 ? 1 : 0;
                    return dfs(graph, child);
                }
            }
        }

        return true;
    }
}
