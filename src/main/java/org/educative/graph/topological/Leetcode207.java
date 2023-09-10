package org.educative.graph.topological;

import java.util.*;

public class Leetcode207 {
    Map<Integer, List<Integer>> map;
    int[] inDegrees;

    public static void main(String[] args) {
        Leetcode207 question = new Leetcode207();
        boolean canFinish = question.canFinish(5, new int[][]{
                {1, 4},
                {2, 4},
                {3, 1},
                {3, 2}
        });

        System.out.println("canFinish = " + canFinish);
    }

    // TC : O(V + E)
    // SC : O(V + E)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length == 0) return true;

        map = new HashMap<>();
        inDegrees = new int[numCourses];

        int countOfCoursesCanComplete = 0;
        Queue<Integer> courseQueue = new LinkedList<>();

        buildGraph(prerequisites);

        for(int course=0; course < numCourses; course++){
            if(inDegrees[course] == 0){
                courseQueue.add(course);
                countOfCoursesCanComplete++;
            }
        }

        if(courseQueue.isEmpty()) return false;

        while(!courseQueue.isEmpty()){
            int currCourse = courseQueue.poll();
            List<Integer> neighbors = map.get(currCourse);
            if(neighbors != null){
                for(int neighbor : neighbors){
                    inDegrees[neighbor]--;
                    if(inDegrees[neighbor] == 0){
                        courseQueue.add(neighbor);
                        countOfCoursesCanComplete++;
                    }
                }
            }
        }

        return countOfCoursesCanComplete == numCourses;
    }

    private void buildGraph(int[][] prerequisites) {
        for(int[] prerequisite : prerequisites){
            int out = prerequisite[1];
            int in = prerequisite[0];

            map.putIfAbsent(out, new ArrayList<>());
            map.get(out).add(in);
            inDegrees[in]++;
        }
    }
}
