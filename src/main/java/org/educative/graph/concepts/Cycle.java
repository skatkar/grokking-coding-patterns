package org.educative.graph.concepts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Detect a cycle in an undirected graph
 */
public class Cycle {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);

        Cycle obj = new Cycle();
        boolean ans = obj.isCycle(4, adj);
        if (ans)
            System.out.println("1");
        else
            System.out.println("0");
    }

    private boolean bfs(int node, int parent, int[] visited, List<List<Integer>> adj){
        visited[node] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{node, parent});

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentParent = current[1];

            for(int adjacentNode : adj.get(currentNode)) {
                if(visited[adjacentNode] == 0) {
                    visited[adjacentNode] = 1;
                    queue.add(new int[]{adjacentNode, currentNode});
                }else if(currentParent != adjacentNode) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int node, int parent, int[] vis, List<ArrayList<Integer>>
            adj) {
        vis[node] = 1;
        // go to all adjacent nodes
        for (int adjacentNode : adj.get(node)) {
            if (vis[adjacentNode] == 0) {
                if (dfs(adjacentNode, node, vis, adj))
                    return true;
            }
            // if an adjacent node is visited and is not its own parent node
            else if (adjacentNode != parent) return true;
        }
        return false;
    }

    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, List<ArrayList<Integer>> adj) {
        int[] vis = new int[V];
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                if (dfs(i, -1, vis, adj)) return true;
            }
        }
        return false;
    }
}

