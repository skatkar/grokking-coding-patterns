package org.educative.graph;

import java.util.*;

public class Leetcode815 {
    public static void main(String[] args) {
        Leetcode815 question = new Leetcode815();
        int busesTaken = question.numBusesToDestination(new int[][]{
                {2, 5, 7},
                {4, 6, 7}
        }, 2, 6);
        System.out.println("busesTaken = " + busesTaken);
    }

    // TC : O(R*S) where R is the total number of routes and S is the number of stations.
    // SC : O(R*S)
    public int numBusesToDestination(int[][] routes, int source, int target) {
        Map<Integer, List<Integer>> stationToBusNumber = generateMap(routes);
        //System.out.println("map = " + stationToBusNumber);

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{source, 0});

        Set<Integer> visited = new HashSet<>();

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int station = poll[0];
            int busesTaken = poll[1];

            if(station == target)
                return busesTaken;

            if(stationToBusNumber.containsKey(station)){
                List<Integer> busNumbers = stationToBusNumber.get(station);
                for(int busNumber : busNumbers){
                    if(!visited.contains(busNumber)){
                        for(int s : routes[busNumber]){
                            queue.add(new int[]{s, busesTaken + 1});
                        }
                    }
                    visited.add(busNumber);
                }
            }
        }

        return -1;
    }

    private Map<Integer, List<Integer>> generateMap(int[][] routes){
        Map<Integer, List<Integer>> stationToBusNumber = new HashMap<>();

        for(int i=0; i < routes.length; i++){
            for(int station : routes[i]){
                stationToBusNumber.putIfAbsent(station, new ArrayList<>());

                stationToBusNumber.get(station).add(i);
            }
        }

        return stationToBusNumber;
    }
}
