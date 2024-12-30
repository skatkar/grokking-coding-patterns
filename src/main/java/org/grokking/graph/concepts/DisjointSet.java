package org.grokking.graph.concepts;

import java.util.Arrays;

public class DisjointSet {
    int[] parent;
    int[] rank;
    int[] size;
    public DisjointSet(int n){
        rank = new int[n + 1];
        Arrays.fill(rank, 1);

        parent = new int[n + 1];
        for(int i=0; i < n; i++) {
            parent[i] = i;
        }

        size = new int[n + 1];
        Arrays.fill(size, 1);
    }

    public int find(int node) {
        if(node == this.parent[node]){
            return node;
        }

        int ulParent = find(parent[node]);
        parent[node] = ulParent;
        return ulParent;
    }

    public void unionByRank(int nodeA, int nodeB){
        int ulParentNodeA = find(nodeA);
        int ulParentNodeB = find(nodeB);


        if(rank[ulParentNodeA] < rank[ulParentNodeB]){
            parent[ulParentNodeA] = ulParentNodeB;
        }else if(rank[ulParentNodeB] < rank[ulParentNodeA]) {
            parent[ulParentNodeB] = ulParentNodeA;
        }else{
            parent[ulParentNodeB] = ulParentNodeA;
            rank[ulParentNodeA]++;
        }
    }

    public void unionBySize(int nodeA, int nodeB) {
        int ulParentNodeA = find(nodeA);
        int ulParentNodeB = find(nodeB);

        if(ulParentNodeA == ulParentNodeB) return;
        if(size[ulParentNodeA] < size[ulParentNodeB]){
            parent[ulParentNodeA] = ulParentNodeB;
            size[ulParentNodeB] += size[ulParentNodeA];
        }else {
            parent[ulParentNodeB] = ulParentNodeA;
            size[ulParentNodeA] += size[ulParentNodeB];
        }
    }

}
