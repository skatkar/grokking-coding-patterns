package org.educative.graph.concepts;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopoSort {

    /**
     * Topologically sorted vertices
     * @param V - Number of vertices
     * @param adj - Adjacency list
     * @return sorted array
     */
    public int[] topoSort(int V, List<List<Integer>> adj) {
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
