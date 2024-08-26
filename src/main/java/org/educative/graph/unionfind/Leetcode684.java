package org.educative.graph.unionfind;

import java.util.Arrays;

public class Leetcode684 {

    public static void main(String[] args) {
        Leetcode684 question = new Leetcode684();
        int[] redundantConnection = question.findRedundantConnection(new int[][]{{1,2},{2,3},{3,4},{1,4},{1,5}});
        Arrays.stream(redundantConnection)
                .forEach(n -> System.out.println("n = " + n));
    }

    // Best reference : https://youtu.be/wU6udHRIkcc
    // TC : O(N)
    // SC : O(N)
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        // number of edges is equal to the number of nodes
        int[] parents = new int[n + 1];
        Arrays.fill(parents, -1);

        for(int[] edge : edges) {
            int nodeA = edge[0];
            int nodeB = edge[1];

            int parentA = find(parents, nodeA);
            int parentB = find(parents, nodeB);

            if(parentA == parentB){
                return edge;
            }
            union(parents, parentA, parentB);
        }

        return new int[]{-1, -1};
    }

    private void union(int[] parents, int nodeA, int nodeB) {
        if(parents[nodeB] < parents[nodeA])
            union(parents, nodeB, nodeA);

        // Both of these approaches will work.Path compression is optimized one for the lookup
        // Try this example : [[9,10],[5,8],[2,6],[1,5],[3,8],[4,9],[8,10],[4,10],[6,8],[7,9]] to understand it better
        // UnionFind by rank
        parents[nodeB] = nodeA;
        // path compression technique
        //parents[nodeB] = find(parents,nodeA);
    }

    private int find(int[] parents, int node) {
        if(parents[node] < 0)
            return node;
        return find(parents, parents[node]);
    }
}
