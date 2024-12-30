package org.grokking.graph;

import java.util.*;

public class ShortestDistance {
    static Map<Node, List<Node>> map = new HashMap<>();
    static Node node0 = new Node(0);
    static Node node1 = new Node(1);
    static Node node2 = new Node(2);
    static Node node3 = new Node(3);

    static {
        map.put(node0, Arrays.asList(node1, node2));
        map.put(node1, Arrays.asList(node0, node2, node3));
        map.put(node2, Arrays.asList(node0, node1));
        map.put(node3, Arrays.asList(node1));
    }

    // Given an (unweighted) connected graph, return the length of the shortest path between two nodes A and B, in terms of the number of edges.
    // Question : https://algo.monster/problems/shortest_path_unweight
    public static int bfs(Node root, int target) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        Set<Node> visited = new HashSet<>();
        visited.add(root);

        int level = 0;
        while(!queue.isEmpty()){
            // get current queue length. Those elements belong to the same level.
            int n = queue.size();
            for(int i=0; i < n; i++) {
                Node currentNode = queue.poll();
                if(currentNode.data == target) return level;
                List<Node> neighbours = map.get(currentNode);
                for(Node currNeighbour : neighbours) {
                    if(visited.contains(currNeighbour))
                        continue;
                    visited.add(currNeighbour);
                    queue.add(currNeighbour);
                }
            }
            level++;
        }

        return level;
    }

    public static void main(String[] args) {
        int bfs = ShortestDistance.bfs(node0, 3);
        System.out.println("bfs = " + bfs);
    }
}
