package org.educative.graph.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinMultSteps {
    int minimumMultiplications(int[] arr, int start, int end) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.steps));
        pq.add(new Pair(0, start));

        final int FACTOR = (int) Math.pow(10,5);
        int[] dist = new int[FACTOR];
        Arrays.fill(dist, (int)1e9);
        dist[start]=0;

        while(!pq.isEmpty()){
            Pair current = pq.poll();
            int currSteps = current.steps;
            int currNode = current.node;

            for(int multiplier : arr) {
                int result = (currNode * multiplier) % FACTOR;
                if(result == end) return currSteps + 1;

                if(currSteps + 1 < dist[result]){
                    dist[result] = currSteps + 1;
                    pq.add(new Pair(currSteps + 1, result));
                }
            }
        }

        return -1;
    }

    class Pair {
        int steps;
        int node;

        public Pair(int steps, int node) {
            this.steps = steps;
            this.node = node;
        }
    }
}
