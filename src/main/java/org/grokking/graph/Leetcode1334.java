package org.grokking.graph;

import java.util.Arrays;

public class Leetcode1334 {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dist = new int[n][n];
        for(int[] sub : dist) {
            Arrays.fill(sub, (int)1e9);
        }

        // Populating the distance matrix for using Floyd Warshall algorithm.
        // For each edge, populate the distance matrix
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            dist[u][v] = weight;
            dist[v][u] = weight;

        }

        // Distance to the self-node
        for(int i=0; i < n; i++){
            dist[i][i] = 0;
        }

        // Floyd Warshall's algorithm starts here
        for(int k=0; k < n;k++) {
            for(int i=0; i < n;i++) {
                for(int j=0; j < n; j++) {
                    if(dist[i][k] == (int)1e9 || dist[k][j] == (int)1e9){
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // Based on the dist matrix generated above,
        // identify how many nodes have a path to another node within the threshold distance.
        int minCities = n, cityNo = -1;
        for(int city=0; city < n;city++) {
            int currCities = 0;
            for(int adjCity=0; adjCity < n; adjCity++) {
                if(dist[city][adjCity] <= distanceThreshold){
                    currCities++;
                }
            }

            if(currCities <= minCities) {
                minCities = currCities;
                cityNo = city;// This will cover the scenario if there is tie. If there is a tie, we need the city with a greater number.
            }
        }

        return cityNo;
    }
}
