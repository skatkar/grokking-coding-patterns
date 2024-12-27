package org.educative.tree.concepts;

import org.educative.tree.bfs.TreeNode;

public class Leetcode110 {
    boolean result = true;
    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return result;
    }

    // The depth of a node is the number of edges present in path from the root node of a tree to that node.
    //The height of a node is the number of edges present in the longest path connecting that node to a leaf node.
    private int maxDepth(TreeNode root) {
        if(root == null) return 0;

        // Find out the max depth of the left subtree and the right subtree
        // Refer Leetcode # 104 - Maximum Depth of Binary Tree for this.
        int leftSubTreeMaxDepth = 1 + maxDepth(root.left);
        int rightSubTreeMaxDepth = 1 + maxDepth(root.right);

        // Find out the difference between these
        if(Math.abs(leftSubTreeMaxDepth - rightSubTreeMaxDepth) > 1)
            result = false;

        // End the current recursion call by returning the max of left depth and right depth
        return Math.max(leftSubTreeMaxDepth, rightSubTreeMaxDepth);
    }
}
