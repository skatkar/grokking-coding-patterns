package org.grokking.tree.dfs;

import org.grokking.tree.bfs.TreeNode;

public class Leetcode2236 {
    public boolean checkTree(TreeNode root) {
        return sum(root.left) + sum(root.right) == root.val;
    }

    private int sum(TreeNode root){
        if(root.left == null && root.right == null)
            return root.val;

        if(root.left == null || root.right == null)
            return 0;

        int left = sum(root.left);
        int right = sum(root.right);

        return left + right;
    }
}
