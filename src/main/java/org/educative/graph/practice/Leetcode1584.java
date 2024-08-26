package org.educative.graph.practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Leetcode1584 {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int minCost = 0, edgesUsed = 0;

        int[] minDist = new int[n];
        Set<Integer> visited = new HashSet<>();
        Arrays.fill(minDist, (int)1e9);
        minDist[0]=0;

        while(edgesUsed < n) {
            int currMinEdge = Integer.MAX_VALUE;
            int currNode = -1;

            for(int node=0; node < n; node++) {
                if(!visited.contains(node) && currMinEdge > minDist[node]){
                    currMinEdge = minDist[node];
                    currNode = node;
                }
            }

            minCost += currMinEdge;
            visited.add(currNode);
            edgesUsed++;

            for(int nextNode =0; nextNode < n; nextNode++) {
                int weight = Math.abs(points[currNode][0] - points[nextNode][0]) +
                        Math.abs(points[nextNode][0] - points[nextNode][1]);

                if(!visited.contains(nextNode) && minDist[nextNode] > weight){
                    minDist[nextNode] = weight;
                }
            }
        }

        return minCost;
    }
}
