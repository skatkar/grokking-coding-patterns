package org.educative.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode2077 {
    // TC : O(E) -> number of edges in the graph, corridor
    // SC : O(V^2) -> V = vertices in the graph, number of rooms
    public int numberOfPaths(int n, int[][] corridors) {
        int cycles = 0;
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for(int[] corridor : corridors) {
            int room1 = corridor[0];
            int room2 = corridor[1];

            // Initialize the room and it's neighbors' set
            map.putIfAbsent(room1, new HashSet<>());
            map.putIfAbsent(room2, new HashSet<>());

            map.get(room1).add(room2);
            map.get(room2).add(room1);

            // Now we know that room1 and room2 are connected to each other
            // we just need to find whether is there any other node which is common among them
            // If yes, then it forms a path of 3 nodes -> confusion score

            cycles += intersectionPaths(map.get(room1), map.get(room2));
        }

        return cycles;
    }

    private int intersectionPaths(Set<Integer> set1, Set<Integer> set2) {
        int count = 0;
        for(int node : set1) {
            if(set2.contains(node))
                count++;
        }
        return count;
    }
}
