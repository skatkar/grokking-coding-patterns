package org.educative.graph.traversal.bfs;

import org.educative.tree.bfs.TreeNode;

import java.util.*;

public class Leetcode863 {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>();

        // We need to build a mapping similar to an adjacency matrix.
        // Since we are given a tree, each node is already aware of its left and right child
        // Instead of the typical adjacency matrix, we'll store the mapping of a node and its parent
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, null, parentMap);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);

        Set<Integer> visited = new HashSet<>();
        visited.add(target.val);
        int distance = 0;
        // Level by level traversal
        // We get to the next level if process all the elements at the given level
        while(!queue.isEmpty()) {
            // If the traversed distance is k then remove all the current elements in the queue
            if(distance == k){
                for(TreeNode node : queue) {
                    result.add(node.val);
                }

                return result;
            }

            int size = queue.size();
            for(int i=0; i < size; i++) {
                TreeNode current = queue.poll();

                // If the left child is not null and not visited
                if(current.left != null && visited.add(current.left.val)){
                    queue.add(current.left);
                }

                // If the right child is not null and not visited
                if(current.right != null && visited.add(current.right.val)) {
                    queue.add(current.right);
                }

                // If the parent is not null and enqueue it as well
                TreeNode parent = parentMap.get(current);
                if(parent != null && visited.add(parent.val)){
                    queue.add(parent);
                }
            }
            distance++;
        }

        return Collections.emptyList();
    }

    private void buildParentMap(TreeNode root, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {
        if(root == null)
            return;
        parentMap.put(root, parent);
        buildParentMap(root.left, root, parentMap);
        buildParentMap(root.right, root, parentMap);
    }
}
