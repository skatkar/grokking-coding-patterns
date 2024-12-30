package org.grokking.graph.topological;

import java.util.*;
import java.util.stream.IntStream;

public class Leetcode210 {
    Map<Integer, List<Integer>> map;
    int[] inDegrees;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(prerequisites == null) return new int[0];
        if(prerequisites.length == 0){
            return IntStream.range(0, numCourses)
                    .toArray();
        }

        map = new HashMap<>();
        inDegrees = new int[numCourses];

        List<Integer> countOfCoursesCanComplete = new ArrayList<>();
        Queue<Integer> courseQueue = new LinkedList<>();

        buildGraph(prerequisites);

        for(int course=0; course < numCourses; course++){
            if(inDegrees[course] == 0){
                courseQueue.add(course);
                countOfCoursesCanComplete.add(course);
            }
        }

        if(courseQueue.isEmpty()) return new int[0];

        while(!courseQueue.isEmpty()){
            int currCourse = courseQueue.poll();
            List<Integer> neighbors = map.get(currCourse);
            if(neighbors != null){
                for(int neighbor : neighbors){
                    inDegrees[neighbor]--;
                    if(inDegrees[neighbor] == 0){
                        courseQueue.add(neighbor);
                        countOfCoursesCanComplete.add(neighbor);
                    }
                }
            }
        }

        return countOfCoursesCanComplete.size() == numCourses ?
                countOfCoursesCanComplete.stream().mapToInt(Integer::intValue).toArray() :
                new int[0];
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
