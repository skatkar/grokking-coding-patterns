package org.educative.graph.unionfind;

import java.util.HashMap;
import java.util.Map;

public class Leetcode128 {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        UnionFind uf = new UnionFind(nums);
        for(int num : nums){
            uf.union(num, num + 1);
        }

        return uf.getMaxLength();
    }

    class UnionFind {
        Map<Integer, Integer> parent;
        Map<Integer, Integer> size;

        int maxLength;

        public UnionFind(int[] nums){
            parent = new HashMap<>();
            size = new HashMap<>();
            maxLength = 1; // There will always be a set having size 1. Assuming each node is having its own set initially

            for (int num : nums) {
                parent.put(num, num);
                size.put(num, 1);
            }
        }

        public int find(int node){
            if(parent.get(node) == node){
                return node;
            }
            parent.put(node, find(parent.get(node)));
            return parent.get(node);
        }

        public void union(int nodeA, int nodeB){
            if(!parent.containsKey(nodeB)) return;
            int parentA = find(nodeA);
            int parentB = find(nodeB);

            if(parentA == parentB) return;

            if(size.get(parentB) < size.get(parentA)){
                parent.put(parentB, parentA);
                size.put(parentA, size.get(parentA) + size.get(parentB));
                maxLength = Math.max(maxLength, size.get(parentA));
            }else {
                parent.put(parentA, parentB);
                size.put(parentB, size.get(parentB) + size.get(parentA));
                maxLength = Math.max(maxLength, size.get(parentB));
            }
        }

        public int getMaxLength(){
            return maxLength;
        }
    }
}
