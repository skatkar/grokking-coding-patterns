package org.educative.graph.traversal.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode797 {
    List<List<Integer>> result;

    public static void main(String[] args) {
        Leetcode797 q = new Leetcode797();
        q.allPathsSourceTarget(new int[][]{
                {1,2},
                {3},
                {3},
                {}
        });
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        result = new ArrayList<>();
        Map<Integer, List<Integer>> adj = generateAdjacency(graph);
        dfs(adj, 0, graph.length - 1, new ArrayList<>() {{
            add(0);
        }});
        return result;
    }

    private void dfs(Map<Integer, List<Integer>> adj, int currNode,
                     int endNode, List<Integer> subList) {
        if(currNode == endNode){
            // Get the current snapshot of the sublist
            // As subList is constantly changing, we need to wrap it in the new ArrayList
            result.add(new ArrayList<>(subList));
            return;
        }

        if(adj.containsKey(currNode)){
            for(int neighbor : adj.get(currNode)) {
                subList.add(neighbor);
                dfs(adj, neighbor, endNode, subList);
                subList.remove(subList.size() - 1);
            }
        }
    }

    private Map<Integer, List<Integer>> generateAdjacency(int[][] graph) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for(int i=0; i < graph.length; i++) {
            for(int j=0; j < graph[i].length; j++) {
                adj.putIfAbsent(i, new ArrayList<>());
                adj.get(i).add(graph[i][j]);
            }
        }

        return adj;
    }
}
