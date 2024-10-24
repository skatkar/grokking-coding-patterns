package org.educative.graph.concepts;

import java.util.Arrays;

public class Bipartite {
    int[] colors;

    public boolean isBipartite(int[][] graph) {
        int nodes = graph.length;
        UnionFind uf = new UnionFind(nodes);
        for(int node=0; node < graph.length; node++) {
            for(int neighbor =0; neighbor < graph[node].length;  neighbor++) {
                if(uf.isConnected(node, graph[node][neighbor]))
                    return false;
                uf.union(graph[node][0], graph[node][neighbor]);
            }
        }

        return true;
    }

    public boolean isBipartite_DFS(int[][] graph) {
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

    class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            for(int i=0; i < size; i++) {
                parent[i] = i;
            }
            rank = new int[size];
        }

        private int find(int node) {
            if(parent[node] == node)
                return node;
            parent[node] = find(parent[node]);
            return parent[node];
        }

        private void union(int nodeA, int nodeB) {
            int parentA = find(nodeA);
            int parentB = find(nodeB);

            if(rank[parentB] < rank[parentA]) {
                parent[parentB] = parentA;
            }else if(rank[parentA] < rank[parentB]) {
                parent[parentA] = parentB;
            }else {
                parent[parentB] = parentA;
                rank[parentA]++;
            }
        }

        private boolean isConnected(int nodeA, int nodeB) {
            return find(nodeA) == find(nodeB);
        }
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
