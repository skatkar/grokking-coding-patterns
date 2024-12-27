package org.educative.tree.concepts;

import org.educative.tree.bfs.TreeNode;

public class Leetcode104 {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;

        int lDepth = 1 + maxDepth(root.left);
        int rDepth = 1 + maxDepth(root.right);

        return Math.max(lDepth, rDepth);
    }
}
