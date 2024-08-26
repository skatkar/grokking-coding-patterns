package org.educative.graph.unionfind;

import java.util.Arrays;

public class Leetcode230 {
    public static void main(String[] args) {
        Leetcode230 q = new Leetcode230();
        int answer = q.countComponents(4, new int[][]{
                {2, 3},
                {1, 2},
                {1, 3}
        });
        System.out.println("answer = " + answer);
    }

    public int countComponents(int n, int[][] edges) {
        int[] parents = new int[n];
        Arrays.fill(parents, -1);

        int count = 0;
        for(int[] edge : edges) {
            int nodeA = edge[0];
            int nodeB = edge[1];

            int parentA = find(parents, nodeA);
            int parentB = find(parents, nodeB);

            if(parentA != parentB) {
                union(parents, parentA, parentB);
            }
        }

        for (int parent : parents) {
            if (parent == -1)
                count++;
        }

        return count;
    }

    private void union(int[] parents, int parentA, int parentB) {
        if(parents[parentB] < parents[parentA])
            union(parents, parentB, parentA);
        parents[parentB] = find(parents, parentA);
    }

    private int find(int[] parents, int node) {
        if(parents[node] < 0)
            return node;
        return find(parents, parents[node]);
    }
}
