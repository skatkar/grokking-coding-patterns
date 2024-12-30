package org.grokking.graph.unionfind;

public class Leetcode1319 {
    public int makeConnected(int n, int[][] connections) {
        DSU dsu = new DSU(n);
        for(int[] connection : connections){
            dsu.union(connection[0], connection[1]);
        }

        int needed = dsu.getConnectedComponents() - 1;
        int available = dsu.getAvailableCables();

        return available >= needed ? needed : -1;
    }

    private class DSU {
        private int[] parents;
        private int[] rank;
        private int connectedComponents;

        private int availableCables;

        public DSU(int n) {
            parents = new int[n];
            rank = new int[n];
            connectedComponents = 0;
            availableCables = 0;

            for(int i=0; i < n; i++) {
                parents[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int node) {
            if(parents[node] == node) {
                return node;
            }
            parents[node] = find(parents[node]);
            return parents[node];
        }

        public void union(int nodeA, int nodeB){
            int parentA = find(nodeA);
            int parentB = find(nodeB);

            if(parentA == parentB) {
                // This is like detecting a cycle in the graph
                // if two nodes are already part of a set then this edge is redundant
                availableCables++;
                return;
            }

            if(rank[parentB] < rank[parentA]){
                parents[parentB] = parentA;
            }else if(rank[parentA] < rank[parentB]){
                parents[parentA] = parentB;
            }else{
                parents[parentB] = parentA;
                rank[parentA]++;
            }
        }

        public int getConnectedComponents(){
            for(int node=0; node < parents.length;node++) {
                if(parents[node] == node)
                    connectedComponents++;
            }
            return connectedComponents;
        }

        public int getAvailableCables(){
            return availableCables;
        }
    }
}
