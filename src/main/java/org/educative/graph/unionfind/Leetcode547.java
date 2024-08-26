package org.educative.graph.unionfind;

public class Leetcode547 {
    public int findCircleNum(int[][] isConnected) {
        int cities = isConnected.length;
        DSU dsu = new DSU(cities);

        for(int i=0; i < cities;i++) {
            for(int j=0; j < cities; j++){
                if(isConnected[i][j] == 1)
                    dsu.union(i,j);
            }
        }

        return dsu.getConnectedComponents();
    }

    private class DSU {
        private int[] parents;
        private int[] rank;
        private int connectedComponents;

        public DSU(int n) {
            parents = new int[n];
            rank = new int[n];
            connectedComponents = 0;

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

            if(parentA == parentB) return;

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
    }
}
