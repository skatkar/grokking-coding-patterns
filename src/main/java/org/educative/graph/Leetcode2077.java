package org.educative.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode2077 {
    // TC : O(E) -> number of edges in the graph, corridor
    // SC : O(E)
    Map<Integer, Set<Integer>> map;
    public int numberOfPaths(int n, int[][] corridors) {
        map = new HashMap<>();

        buildGraph(corridors);

        int count = 0;
        for(int[] corridor : corridors) {
            int room1 = corridor[0];
            int room2 = corridor[1];

            Set<Integer> room1Neighbors = map.get(room1);
            Set<Integer> room2Neighbors = map.get(room2);

            // Input corridors-> [1,2] [3,1] [2,3]
            // Find the intersection
            // 1 - [3]
            // 2 - [3]
            // That means there is edge 1 -> 2 -> 3. So we got one cycle
            if(room1Neighbors != null && room2Neighbors != null)
                count += room1Neighbors.stream().filter(room2Neighbors::contains).count();
        }

        return count;
    }

    /*
    Note - Even though the graph given in the problem is undirected we create a directed graph by creating an edge from smaller number to higher number (and not reverse).
    This avoids generating duplicate cycles and makes the graph smaller. If an undirected graph is used then the final result will need to be divided by 3 because we will count the same cycle three times (one from each vertex of the cycle).
    Also, note that we don't need to use the input param n anywhere.
    * */
    private void buildGraph(int[][] corridors){
        for(int[] corridor : corridors) {
            int room1 = corridor[0];
            int room2 = corridor[1];

            // Initialize the room and it's neighbors' set
            // The if else conditions mentioned below will make the undirected graph as directed graph
            // So, there will be an edge from smaller node to larger node and not the other way
            // This will avoid the duplicate cycles
            // E,g, If the nodes are 1,3,4 (assuming all of them are connected)
            // Possible cycles are : 1 -> 3 -> 4 -> 1, 4 -> 1 -> 3 -> 4, 3 -> 4 -> 1 -> 3
            // The below logic will get only the first cycle and will ignore the other two.
            if (room1 < room2){
                map.putIfAbsent(room1, new HashSet<>());
                map.get(room1).add(room2);
            }
            else {
                map.putIfAbsent(room2, new HashSet<>());
                map.get(room2).add(room1);
            }
        }
    }
}
