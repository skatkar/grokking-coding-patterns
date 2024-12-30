package org.grokking.graph.traversal.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode1306 {
    public static void main(String[] args) {
        Leetcode1306 q = new Leetcode1306();
        boolean canReach = q.canReach(new int[]{58,48,64,36,19,19,67,13,32,2,59,50,29,68,50,0,69,31,54,20,22,43,30,9,68,71,20,22,48,74,2,65,27,54,30,5,66,24,64,68,9,31,50,59,15,72,6,49,11,71,12,61,5,66,30,1,2,39,59,35,53,21,76,17,71,40,68,57,64,53,70,21,50,49,25,63,35}, 46);
        System.out.println("canReach = " + canReach);
    }

    public boolean canReach(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[arr.length];
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int current = queue.poll();
            if(arr[current] == 0) return true;

            int[] nextPositions = new int[]{current + arr[current], current - arr[current]};

            for(int next : nextPositions) {
                if(next >= 0 && next < arr.length && !visited[next]){
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }

        return false;
    }

    // This gives TLE
    public boolean canReach2(int[] arr, int start) {
        List<List<Integer>> adj = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[arr.length];
        queue.add(start);
        visited[start] = true;

        for(int i=0; i < arr.length; i++) {
            adj.add(new ArrayList<>());
            int right = i + arr[i];
            int left = i - arr[i];
            if(right < arr.length){
                adj.get(i).add(right);
            }

            if(left >= 0){
                adj.get(i).add(left);
            }
        }

        while(!queue.isEmpty()){
            int curr = queue.poll();
            System.out.println("curr = " + curr + " visited " + visited[curr]);
            if(arr[curr] == 0)
                return true;
            for(int neighbor : adj.get(curr)) {
                if(!visited[neighbor]){
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return false;
    }
}
