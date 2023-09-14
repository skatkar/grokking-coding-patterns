package org.educative.graph.djikstras.practice;

import java.util.*;

public class Leetcode1514 {
    public static void main(String[] args) {
        Leetcode1514 question = new Leetcode1514();
        double result = question.maxProbability(5, new int[][]{
                {1,4},
                {2,4},
                {0,4},
                {0,3},
                {0,2},
                {2,3}
        }, new double[]{0.37,0.17,0.93,0.23,0.39,0.04}, 3,4);
        System.out.println("result = " + result);
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        Map<Integer, List<Pair>> map = new HashMap<>();
        for(int i=0; i < edges.length; i++){
            int[] edge = edges[i];
            double prob = succProb[i];

            map.putIfAbsent(edge[0], new ArrayList<>());
            map.putIfAbsent(edge[1], new ArrayList<>());

            map.get(edge[0]).add(new Pair(prob, edge[1]));
            map.get(edge[1]).add(new Pair(prob, edge[0]));
        }

        System.out.println("map = " + map);
        
        Set<Integer> visited = new HashSet<>();
        double result = 1.0;
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a,b) -> Double.compare(b.prob,a.prob));
        maxHeap.add(new Pair(1.0, start_node));
        
        while(!maxHeap.isEmpty()){
            Pair curr = maxHeap.poll();
            double currProb = curr.prob;
            int currNode = curr.node;

            if(currNode == end_node)
                return currProb;

            if(visited.contains(currNode)) continue;
            visited.add(currNode);
            result = currProb;
            if(map.containsKey(currNode)){
                for(Pair neighbor : map.get(currNode)){
                    maxHeap.add(new Pair(currProb * neighbor.prob, neighbor.node));
                }
            }
        }

        return result == 1.0 ? 0.0 : result;
    }

    class Pair {
        double prob;
        int node;

        public Pair(double prob, int node){
            this.prob = prob;
            this.node = node;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "prob=" + prob +
                    ", node=" + node +
                    '}';
        }
    }
}
