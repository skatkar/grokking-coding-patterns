package org.educative.tree.bfs;

import java.util.*;

/**
 * <a href="https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1">...</a>
 */
public class Topview {
    // This is similar to vertical order traversal. The only difference is that we don't want the condition related to the rows.
    public List<Integer> topView(TreeNode root) {
        // To store the result
        List<Integer> ans = new ArrayList<>();

        // To maintain the node by each column
        Map<Integer, Integer> nodeByColumn = new TreeMap<>();

        // Maintain a queue for level by level traversal
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while(!queue.isEmpty()) {
            Pair current = queue.poll();

            TreeNode treeNode = current.node;
            int value = current.node.val;
            int column = current.column;

            // If this is the first time we are encountering this column.
            if(!nodeByColumn.containsKey(column))
                nodeByColumn.put(column, value);

            if(treeNode.left != null)
                queue.add(new Pair(treeNode.left, column - 1));

            if(treeNode.right != null)
                queue.add(new Pair(treeNode.right, column + 1));
        }

        nodeByColumn.forEach((column, value) -> ans.add(value));
        return ans;
    }

    class Pair {
        TreeNode node;
        int column;

        public Pair(TreeNode node, int column) {
            this.node = node;
            this.column = column;
        }
    }
}
