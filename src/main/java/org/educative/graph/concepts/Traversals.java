package org.educative.graph.concepts;

import java.util.*;

public class Traversals {

    static Map<Node, List<Node>> map = new HashMap<>();
    static Node node1 = new Node(1);
    static Node node2 = new Node(2);
    static Node node3 = new Node(3);
    static Node node4 = new Node(4);

    static {

        map.put(node1, Arrays.asList(node2, node3));
        map.put(node2, Arrays.asList(node1, node3, node4));
        map.put(node3, Arrays.asList(node1, node2));
        map.put(node4, Arrays.asList(node2));
    }
    public static void bfs(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        Set<Node> visited = new HashSet<>();
        visited.add(root);

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            List<Node> neighbours = map.get(currentNode);
            for(Node node : neighbours) {
                if(visited.contains(node))
                    continue;
                queue.add(node);
                visited.add(node);
            }
        }

        visited.forEach(System.out::println);
    }

    public static void dfs(Node root){
        Set<Node> visited = new HashSet<>();
        visited.add(root);
        dfsRecursive(root, visited);
        visited.forEach(System.out::println);
    }

    private static void dfsRecursive(Node currNode, Set<Node> visited){
        if(currNode == null)
            return;
        List<Node> neighbours = map.get(currNode);
        for(Node node : neighbours) {
            if(visited.contains(node))
                continue;
            visited.add(node);
            dfsRecursive(node, visited);
        }
    }

    public static void main(String[] args) {
        //Traversals.bfs(node1);
        Traversals.dfs(node1);
    }
}
