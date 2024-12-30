package org.grokking.graph.practice;

import java.util.*;

public class Leetcode207 {
    int[] indegress;
    Map<Integer, List<Integer>> map;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        indegress = new int[numCourses];
        map = new HashMap<>();

        int coursesCanComplete = 0;
        buildGraph(numCourses, prerequisites);

        Queue<Integer> courseQueue = new LinkedList<>();
        for(int i=0; i < indegress.length; i++) {
            if(indegress[i] == 0){
                courseQueue.add(i);
                coursesCanComplete++;
            }
        }

        while (!courseQueue.isEmpty()){
            int current = courseQueue.poll();
            List<Integer> neighbors = map.get(current);
            for(int neighbor : neighbors) {
                indegress[neighbor]--;
                if(indegress[neighbor] == 0){
                    courseQueue.add(neighbor);
                    coursesCanComplete++;
                }
            }
        }

        return coursesCanComplete == numCourses;
    }

    private void buildGraph(int numCourses, int[][] prerequisites) {
        for(int[] prereq : prerequisites) {
            int out = prereq[1];
            int in = prereq[0];

            map.putIfAbsent(out, new ArrayList<>());
            map.get(out).add(in);

            indegress[in]++;
        }
    }
}
