package org.educative.graph.concepts;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TopoSort {


    /**
     * Pick a node, DFS its neighbors and once all the neighbors are visited, put it in a stack.
     * Using DFS
     * @param V
     * @param adj
     * @return
     */
    public int[] topoSort(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[V];
        int index = 0;

        for(int i=0; i < V; i++) {
            if(!visited[i]){
                dfs(i, stack, adj, visited);
            }
        }

        while(!stack.isEmpty()) {
            int current = stack.pop();
            result[index++] = current;
        }

        return result;
    }

    private void dfs(int node, Stack<Integer> stack, List<List<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        for(int neighbor : adj.get(node)) {
            if(!visited[neighbor]) {
                dfs(neighbor, stack, adj, visited);
            }
        }
        stack.push(node);
    }

    /**
     * Kahn's algorithm
     * Topologically sorted vertices
     * @param V - Number of vertices
     * @param adj - Adjacency list
     * @return sorted array
     */
    public int[] topoSort_BFS(int V, List<List<Integer>> adj) {
        int[] inDegrees = new int[V];
        int[] sorted = new int[V];
        int pointer = 0;

        // Step #1 - Make a note of indegrees of each node
        for(int i=0; i < V; i++) {
            for(int node : adj.get(i)) {
                inDegrees[node]++;
            }
        }

        // Step #2 - Add all nodes having indegrees as 0 to the queue
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i < inDegrees.length; i++) {
            if(inDegrees[i] == 0){
                queue.add(i);
            }
        }

        // Step #3 - Iterate through the queue and update the indegrees of their connected nodes
        while(!queue.isEmpty()) {
            int currNode = queue.poll();
            sorted[pointer++] = currNode;

            for(int neighbor : adj.get(currNode)) {
                inDegrees[neighbor]--;
                if(inDegrees[neighbor] == 0){
                    queue.add(neighbor);
                }
            }
        }

        return sorted;
    }
}
