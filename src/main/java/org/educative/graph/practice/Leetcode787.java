package org.educative.graph.practice;

import java.util.*;

public class Leetcode787 {

    public static void main(String[] args) {
        Leetcode787 question = new Leetcode787();
        int cheapestPrice = question.findCheapestPrice(11, new int[][]{
                {0,3,3},
                {3,4,3},
                {4,1,3},
                {0,5,1},
                {5,1,100},
                {0,6,2},
                {6,1,100},
                {0,7,1},
                {7,8,1},
                {8,9,1},
                {9,1,1},
                {1,10,1},
                {10,2,1},
                {1,2,100}
        }, 0, 2, 4);

        System.out.println("cheapestPrice = " + cheapestPrice);
    }

    // Using no. of stop as a first criteria and then using distance as a second criteris
    // No need to use a priority queue as each new element getting added will be having +1 stop
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Generate an adjacency list
        List<List<Pair>> adj = new ArrayList<>();
        for(int i=0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] flight : flights) {
            adj.get(flight[0]).add(new Pair(flight[2], flight[1]));
        }

        // Maintaining the shortest distance to each city
        int[] distance = new int[n];
        Arrays.fill(distance, (int) 1e9);

        // 0 - stops
        // 1 - Node
        // 2 - distance
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, src, 0});

        while(!queue.isEmpty()) {
            int[] tuple = queue.poll();

            int stops = tuple[0];
            int node = tuple[1];
            int currDist = tuple[2];

            if(stops > k) continue;

            for(Pair neighbor : adj.get(node)) {
                if(currDist + neighbor.cost < distance[neighbor.node] && stops <= k){
                    distance[neighbor.node] = currDist + neighbor.cost;
                    queue.add(new int[]{stops + 1, neighbor.node, currDist + neighbor.cost});
                }
            }
        }

        if(distance[dst] == (int)1e9) return -1;
        return distance[dst];
    }

    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<Pair>> map = new HashMap<>();
        for(int[] flight : flights){
            map.putIfAbsent(flight[0], new ArrayList<>());
            map.get(flight[0]).add(new Pair(flight[2], flight[1]));
        }

        //System.out.println("map = " + map);

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // 0 - distance from the previous node which was part of the possible path
        // 1 - node
        // 2 - K + 1 - meaning we'll visit k + 1 nodes from this current node till we reach the dest node
        minHeap.add(new int[]{0, src, k + 1});
        Map<Integer, Integer> visited = new HashMap<>();

        while(!minHeap.isEmpty()){
            int[] curr = minHeap.poll();
            int distance = curr[0];
            int city = curr[1];
            int steps = curr[2];

            // couldn't understand the second part of the && condition
            // reference - https://leetcode.com/problems/cheapest-flights-within-k-stops/solutions/115541/java-c-python-priority-queue-solution-tle-now/
            if(visited.containsKey(city) && visited.get(city) >= steps) // avoid TLE
                continue;
            visited.put(city, steps);

            if(city == dst) return distance;
            if(steps > 0 && map.containsKey(city)){
                for(Pair neighbor : map.get(city)){

                    minHeap.add(new int[]{distance + neighbor.cost, neighbor.node, steps-1});
                }
            }
        }

        return -1;
    }

    class Pair {
        int cost, node;

        public Pair(int cost, int dest){
            this.cost = cost;
            this.node = dest;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "cost=" + cost +
                    ", dest=" + node +
                    '}';
        }
    }
}
