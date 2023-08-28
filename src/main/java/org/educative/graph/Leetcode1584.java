package org.educative.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// This covers the optimized version of Prim's algorithm
public class Leetcode1584 {
    public int minCostConnectPoints(int[][] points) {
        Set<Integer> visited = new HashSet<>();

        int n = points.length;

        int[] minDist = new int[n];
        minDist[0] = 0;
        Arrays.fill(minDist, 1, minDist.length, Integer.MAX_VALUE);

        int minCost = 0, edgesUsed = 0;

        while(edgesUsed < n){
            int currMinEdge = Integer.MAX_VALUE;
            int currNode = -1;

            // 1. First find out the distance of each node and find the node which is at the smallest distance
            for(int node=0; node < n; node++) {
                if(!visited.contains(node) && currMinEdge > minDist[node]){
                    currMinEdge = minDist[node];
                    currNode = node;
                }
            }
            // since we found the min weight edge, add it to the min cost
            minCost += currMinEdge;

            // keep track of the edges covered so far
            edgesUsed++;

            // Mark the current node as visited to avoid the cycle
            visited.add(currNode);

            // 2. Update the distance of each node and
            // see if the previously computed distance got better because of adding a new node
            for(int nextNode = 0; nextNode < n; nextNode++){
                // Manhattan distance
                int weight = Math.abs(points[currNode][0] - points[nextNode][0]) +
                        Math.abs(points[currNode][1] - points[nextNode][1]);

                if(!visited.contains(nextNode) && minDist[nextNode] > weight){
                    minDist[nextNode] = weight;
                }
            }
        }

        return minCost;
    }
}
