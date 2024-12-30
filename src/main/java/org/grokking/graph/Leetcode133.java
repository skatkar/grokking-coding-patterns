package org.grokking.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode133 {

    Map<Node, Node> map;

    public Node cloneGraph(Node node) {
        if(node == null) return null;

        map = new HashMap<>();
        dfs(node);

        return map.get(node);
    }

    private void dfs(Node node) {
        // This will act like a visited set and avoid the cycle of handling the same node again and again.
        if(map.containsKey(node)) return;

        // 1. CLone the current node
        cloneDfs(node);

        // 2. repeat this process for all the neighbors of the given node
        for(Node neighbor : node.neighbors) {
            dfs(neighbor);

            // 3. Once the DFS of the neighbors is done, add their mapping to the map
            map.get(node).neighbors.add(map.get(neighbor));
        }
    }

    private void cloneDfs(Node node) {
        if(map.containsKey(node)) return;

        Node copyNode = new Node(node.val);
        map.put(node, copyNode);
    }

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
