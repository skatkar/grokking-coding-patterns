package org.educative.unionfind;

import java.util.HashMap;
import java.util.Map;

public class Leetcode947 {
    // Compute connected components with Union-Find.
    public int removeStones(int[][] stones) {
        // We assign each row and column to a unique index in the Union-Find array.
        Map<String, Integer> mapping = new HashMap<>();
        for (int[] stone : stones) {
            mapping.putIfAbsent("r" + stone[0], mapping.size());
            mapping.putIfAbsent("c" + stone[1], mapping.size());
        }

        UnionFind uf = new UnionFind(mapping.size());    // Create a Union-Find object that initially has exactly one component for each row and each column.
        for (int[] stone : stones) {
            // Map row stone[0] and column stone[1] to the IDs of their UF elements (their indices in the UF parent array), then union these elements (merge the components of these two elements if they aren't already merged).
            uf.union(mapping.get("r" + stone[0]), mapping.get("c" + stone[1]));
        }
        return stones.length - uf.getNumComponents();
    }



    // This is just copy-paste:
// Standard Union-Find implementation (weighted unions, path compression).
// This is the best UF implementation that you should always be using (UF + weighted + path compression):  https://algs4.cs.princeton.edu/15uf/WeightedQuickUnionPathCompressionUF.java.html
    private class UnionFind {

        private int[] parent;          // parent[i] is the ID (root node) of the set (tree) that contains element i.
        private int[] weight;          // weight[i] is the height of the tree whose ID (root node) is i. Note that this value only makes sense if i is the ID (root node) of a set (tree).
        // You can also define weight[i] as the number of nodes in a tree (number of elements in a set) instead of the treee depth, UF still works efficiently in practice, union code needs to be adjusted (add up sizes when unioning instead of incrementing the tree depth).
        private int numComponents;             // Number of sets/components/trees.

        // private int maxComponentSize;  // You can maintain the number of elements in each component if necessary. You can then maintian the max size component so you can access it in O(1) time.
        // private int[] size;            // size[i] is the number of elements in the component whose root is i. Note that this value is only valid if i is a root.

        // Inizialize UF with n elements.
        public UnionFind(int n) {
            parent = new int[n];
            weight = new int[n];
            numComponents = n;
            for (int i = 0; i < parent.length; ++i) {
                parent[i] = i;
                weight[i] = 0;
            }
        }

        // Merges the set containing element p with the set containing element q.
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;     // p and q are already in the same set.

            // Weighted union: make the smaller tree a subtree of the greater tree (this way we only increase the depth of the deepest tree if we really have to).
            if (weight[pRoot] < weight[qRoot]) {
                parent[pRoot] = qRoot;                  // Tree with ID pRoot is now a subtree of the tree with ID qRoot.
            } else if (weight[pRoot] > weight[qRoot])  {
                parent[qRoot] = pRoot;
            } else {
                parent[qRoot] = pRoot;
                weight[pRoot]++;        // Trees have equal depth so by adding one tree as a subtree of the other the depth increases by one.
            }
            numComponents--;    // We have one less set since we just turned two sets into one.
        }

        // Returns the ID of the set containing the given element p.
        public int find2(int p) {
            int pRoot = p;
            while (pRoot != parent[pRoot]) {
                pRoot = parent[pRoot];          // Travel up the tree until we find the ID (root) of the set (tree) that element p belongs to.
            }

            // Path compression: set the parent of every node on the path from p to its root to the root. This speeds up future find operations because they only do one step (parent of a node is the root).
            while (p != pRoot) {
                int temp = parent[p];
                parent[p] = pRoot;     // Compress the path.
                p = temp;              // Move up one node in the path towards the root.
            }
            return pRoot;
        }

        public int find(int node) {
            if(node == parent[node])
                return node;
            int ulp = find(parent[node]); // find the ultimate parent
            parent[node] = ulp;
            return ulp;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public int getNumComponents() {
            return numComponents;
        }
    }
}
