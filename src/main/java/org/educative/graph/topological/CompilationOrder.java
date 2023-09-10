package org.educative.graph.topological;

import java.util.*;

public class CompilationOrder {

    Map<Character, List<Character>> map;
    int[] inDegrees;

    public static void main(String[] args) {
        CompilationOrder question = new CompilationOrder();
        List<Character> compilationOrder = question.findCompilationOrder(List.of(Arrays.asList('A', 'B'), Arrays.asList('B', 'C'), Arrays.asList('A', 'D')));
        System.out.println("compilationOrder = " + compilationOrder);
    }

    public List<Character> findCompilationOrder(List<List<Character>> dependencies) {
        if(dependencies.isEmpty())
            return Collections.emptyList();

        map = new HashMap<>();
        inDegrees = new int[26];

        List<Character> result = new ArrayList<>();
        Queue<Character> queue = new LinkedList<>();

        buildGraph(dependencies);

        for (char currClass : map.keySet()) {
            if (inDegrees[currClass - 'A'] == 0) {
                queue.add(currClass);
                result.add(currClass);
            }
        }

        if(queue.isEmpty()) return Collections.emptyList();

        while(!queue.isEmpty()){
            char currClass = queue.poll();
            List<Character> dependentClasses = map.get(currClass);
            if(dependentClasses != null){
                for(Character dependentClass : dependentClasses){
                    inDegrees[dependentClass - 'A']--;
                    if(inDegrees[dependentClass - 'A'] == 0){
                        queue.add(dependentClass);
                        result.add(dependentClass);
                    }
                }
            }
        }

        return result;
    }

    private void buildGraph(List<List<Character>> dependencies){
        for(List<Character> dependency : dependencies){
            char out = dependency.get(1);
            char in = dependency.get(0);

            map.putIfAbsent(out, new ArrayList<>());
            map.get(out).add(in);

            inDegrees[in - 'A']++;
        }
    }
}
