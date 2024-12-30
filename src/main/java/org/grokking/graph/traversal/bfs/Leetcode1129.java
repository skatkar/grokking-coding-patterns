package org.grokking.graph.traversal.bfs;

import java.util.*;

public class Leetcode1129 {
    public static void main(String[] args) {
        Leetcode1129 q = new Leetcode1129();
//        int[] paths = q.shortestAlternatingPaths(4, new int[][]{
//                {0, 1},
//                {1, 2},
//                {2, 3}
//        }, new int[][]{
//                {0, 2},
//                {2, 1},
//                {1, 3}
//        });
        int[] paths = q.shortestAlternatingPaths(5, new int[][]{
                {0,1},
                {1,2},
                {2,3},
                {3,4}
        }, new int[][]{
                {1,2},
                {2,3},
                {3,1}
        });

        Arrays.stream(paths)
                .forEach(path -> System.out.print(path + " "));
    }

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        int[] distance = new int[n];
        Arrays.fill(distance, (int)1e5);
        distance[0] = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, -1));

        // Each node will have its node number and the color
        // 0 - red color
        // 1 - blue color
        List<List<Node>> adj = new ArrayList<>();
        for(int i=0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] red : redEdges) {
            int from = red[0];
            int to = red[1];
            adj.get(from).add(new Node(to, 0));
        }

        for(int[] blue : blueEdges) {
            int from = blue[0];
            int to = blue[1];
            adj.get(from).add(new Node(to, 1));
        }

        while(!queue.isEmpty()){
            Node currNode = queue.poll();
            for(Node neighbor : adj.get(currNode.nodeNumber)) {
                if(neighbor.color != currNode.color &&
                        distance[neighbor.nodeNumber] > currNode.currDist + 1){
                    neighbor.currDist = currNode.currDist + 1;
                    distance[neighbor.nodeNumber] = currNode.currDist + 1;
                    queue.add(neighbor);
                }
            }
        }

        for(int i=1; i < distance.length; i++) {
            if(distance[i] == (int)1e5)
                distance[i] = -1;
        }
        return distance;
    }

    class Node {
        int nodeNumber;
        int color;
        int currDist;

        public Node(int nodeNumber, int color) {
            this.nodeNumber = nodeNumber;
            this.color = color;
            this.currDist = 0;
        }
    }
}
