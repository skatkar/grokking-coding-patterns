package org.grokking.graph.unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode886 {
    public static void main(String[] args) {
        Leetcode886 q = new Leetcode886();
        q.possibleBipartition(6, new int[][]{
                {1,2},
                {3,4},
                {5,6}
        });
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

        public int find(int node) {
            if(parent[node] == node)
                return parent[node];
            parent[node] = find(parent[node]);
            return parent[node];
        }

        public void union(int nodeA, int nodeB) {
            int parentA = find(nodeA);
            int parentB = find(nodeB);

            if(rank[parentB] < rank[parentA]) {
                parent[parentB] = parentA;
            } else if (rank[parentA] < rank[parentB]) {
                parent[parentA] = parentB;
            } else {
                parent[parentB] = parentA;
                rank[parentA]++;
            }
        }
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        // Nodes start from 1 so adding 1 more space in the required arrays
        UnionFind uf = new UnionFind(n + 1);

        // Adjacency matrix for the node and its neighbors
        // This will help us in finding out the nodes which should not be together
        Map<Integer, List<Integer>> adj = new HashMap<>();

        // Need to make it as bidirectional as a dislikes b means b dislikes a
        for(int[] dislike : dislikes) {
            adj.computeIfAbsent(dislike[0], k-> new ArrayList<>()).add(dislike[1]);
            adj.computeIfAbsent(dislike[1], k-> new ArrayList<>()).add(dislike[0]);
        }

        for(int node = 1; node <= n; node++) {
            if(!adj.containsKey(node))
                continue;
            for(int neighbor : adj.get(node)) {
                // As per our logic, we are putting together the nodes that do not belong to the dislike pair.
                // But if the current node and its neighbor are already grouped under the same parent,
                // we can't group them together at this point.
                // Example, [[1,2], [1,3], [2,3]]
                // Iterate adj matrix; 3's parent 2 -> 3's parent 1 -> 1 is already 3's parent -> false
                // The current node, and its neighbor can't be grouped together
                if(uf.find(neighbor) == uf.find(node)){
                    return false;
                }
                // Pick the first neighbor of the current node, and the current neighbor
                // No need to pick 0th neighbor, but code becomes simpler by this decision.
                uf.union(adj.get(node).get(0), neighbor);
            }
        }

        return true;
    }
}
