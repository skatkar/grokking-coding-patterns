package org.grokking.graph;

import java.util.*;

public class Leetcode261 {
    public static void main(String[] args) {
        Leetcode261 question = new Leetcode261();
        boolean validTree = question.validTree(4, new int[][]{
                {2, 3},
                {1, 2},
                {1, 3}
        });

        System.out.println("validTree = " + validTree);
    }

    public boolean validTree2(int n, int[][] edges) {
        if(edges.length != n - 1)
            return false;

        Map<Integer, List<Integer>> map = generateAdjacency(edges);
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        Set<Integer> visited = new HashSet<>();
        visited.add(0);

        while(!stack.isEmpty()) {
            int currNode = stack.pop();
            if(map.containsKey(currNode)){
                List<Integer> neighbors = map.get(currNode);
                for(int neighbor : neighbors) {
                    if(visited.contains(neighbor))
                        continue;
                    visited.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }

        return visited.size() == n;
    }

    // n - number of nodes
    // m - number of edges
    // TC : O(n)
    // SC : O(mn)
    // Valid tree - All nodes are connected and there are n - 1 edges in the given graph
    public boolean validTree(int n, int[][] edges) {
        // if the number of edges equals the number of nodes, then there is a cycle, not a tree
        if(edges.length != n - 1)
            return false;
        Map<Integer, List<Integer>> map = generateAdjacency(edges);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);

        dfs(map, 0, visited);
        return visited.size() == n; // visited contains the number of nodes that can be visited somehow through the given edges.
        // for this input-> n = 4, edges = [[2,3], [1,2], [1,3]]
        // all nodes can't be "visited" as there is no edge from 0 to any other nodes. The visited set will always contain only one node.
    }

    private void dfs(Map<Integer, List<Integer>> map, int currNode, Set<Integer> visited){
        List<Integer> neighbors = map.get(currNode);
        if (null != neighbors){ // there can be no neighbors for a given node
            for (int neighbor : neighbors) {
                if (visited.contains(neighbor))
                    continue;
                visited.add(neighbor);
                dfs(map, neighbor, visited);
            }
        }
    }

    private Map<Integer, List<Integer>> generateAdjacency(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            map.putIfAbsent(node1, new ArrayList<>());
            map.putIfAbsent(node2, new ArrayList<>());

            map.get(node1).add(node2);
            map.get(node2).add(node1);
        }
        System.out.println("map = " + map);
        return map;
    }
}
