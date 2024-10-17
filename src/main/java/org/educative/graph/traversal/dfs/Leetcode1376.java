package org.educative.graph.traversal.dfs;

import java.util.*;

public class Leetcode1376 {
    int totalTime = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Leetcode1376 q = new Leetcode1376();
        int numOfMinutes = q.numOfMinutes(7, 6, new int[]{1, 3, 3, 4, 6, 6, -1}, new int[]{0, 2, 0, 1, 2, 0, 1});
        System.out.println("numOfMinutes = " + numOfMinutes);
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // Employee and his list of subordinates
        Map<Integer, List<Integer>> adj = generateAdjacency(n, headID, manager);

        // Iterative approach
        // dfsIterative(adj, headID, informTime);

        // Recursive approach
        dfsRecursive(adj, headID, informTime, 0);
        return totalTime;
    }

    private void dfsIterative(Map<Integer, List<Integer>> adj, int headID, int[] informTime) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{headID, 0});

        while(!stack.isEmpty()) {
            int[] curr = stack.pop();
            int currEmployee = curr[0];
            int currTime = curr[1];

            totalTime = Math.max(totalTime, currTime);
            List<Integer> subs = adj.get(curr[0]);
            if(subs != null){
                for(int sub : subs) {
                    // Current employee's subordinate and the time needed to reach this subordinate
                    // time needed to reach this subordinate = previous time + time needed for this employee to inform his subordinates
                    stack.push(new int[]{sub, currTime + informTime[currEmployee]});
                }
            }
        }
    }

    /**
     *
     * @param adj
     * @param curr
     * @param informTime
     * @param time - Time needed to reach the current employee. Initialized with 0 for the headId
     */
    private void dfsRecursive(Map<Integer, List<Integer>> adj, int curr, int[] informTime, int time) {
        totalTime = Math.max(totalTime, time);
        List<Integer> subs = adj.get(curr);
        if(subs != null){
            for(int sub : adj.get(curr)) {
                dfsRecursive(adj, sub, informTime, time + informTime[curr]);
            }
        }
    }

    private Map<Integer, List<Integer>> generateAdjacency(int n, int headID, int[] manager) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for(int i=0; i < manager.length; i++) {
            if(manager[i] != -1){
                List<Integer> subs = adj.computeIfAbsent(manager[i], k -> new ArrayList<>());
                subs.add(i);
            }
        }

        return adj;
    }
}
