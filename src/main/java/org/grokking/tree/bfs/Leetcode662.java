package org.grokking.tree.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode662 {
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        int maxWidth = 0;

        while(!queue.isEmpty()) {
            int levelLength = queue.size();
            int levelStart = queue.peek().index;
            int index = 0;

            for(int i=0; i < levelLength; i++) {
                Pair current = queue.poll();
                TreeNode currentNode = current.node;
                index = current.index;

                // Left node is at the index-> 2 * i
                if(currentNode.left != null)
                    queue.add(new Pair(currentNode.left, 2* index));

                // Right node is at the index -> 2 * i + 1
                if(currentNode.right != null)
                    queue.add(new Pair(currentNode.right, 2 * index + 1));
            }

            // Once we iterate over all the nodes at this level, find out the length
            maxWidth = Math.max(maxWidth, index - levelStart + 1);
        }

        return maxWidth;
    }

    class Pair{
        TreeNode node;
        int index;

        public Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
