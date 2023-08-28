package org.educative.graph;

import java.util.*;
class Pair {
    int time;
    int node;

    public Pair(int time, int node){
        this.time = time;
        this.node = node;
    }
}
// This code uses Dijkstra's algorithm
public class Leetcode743 {
    public static void main(String[] args) {
        Leetcode743 question = new Leetcode743();
        int networkDelayTime = question.networkDelayTime(new int[][]{
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        }, 4, 2);
        System.out.println("networkDelayTime = " + networkDelayTime);
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Pair>> map = new HashMap<>();
        for(int[] time : times){
            map.putIfAbsent(time[0], new ArrayList<>());
            // source -> List of <time, destination>
            map.get(time[0]).add(new Pair(time[2], time[1]));
        }

        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));
        pq.add(new Pair(0, k)); // <time, node>
        int result = 0;

        while(!pq.isEmpty()){
            Pair pair = pq.poll();
            int currDist = pair.time;
            int currNode = pair.node;

            if(visited.contains(currNode)) continue;
            visited.add(currNode);
            result = currDist;
            n--;

            if(map.containsKey(currNode)){
                for(Pair next : map.get(currNode)){
                    pq.add(new Pair(currDist + next.time, next.node));
                }
            }
        }
        return n == 0 ? result : -1;
    }
}
