package org.educative.tree.bfs;

import java.util.*;

public class Bottomview {
    public List<Integer> bottomView(TreeNode root) {
        // To store the result
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return null;
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

            // This is similar to top view question.
            // The only difference is that we'll keep on updating the TreeMap for a given column.
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
