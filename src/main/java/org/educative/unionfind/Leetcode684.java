package org.educative.unionfind;

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
        parents[nodeB] = nodeA;
    }

    private int find(int[] parents, int node) {
        if(parents[node] < 0)
            return node;
        return find(parents, parents[node]);
    }
}
