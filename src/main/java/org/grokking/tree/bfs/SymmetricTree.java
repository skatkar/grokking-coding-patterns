package org.grokking.tree.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Question : Leetcode 101
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while(!queue.isEmpty()){
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if(left == null && right == null)
                continue;
            if(left == null || right == null)
                return false;

            if(left.val != right.val) return false;

            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }

        return true;
    }

    public boolean isSymmetric_DFS(TreeNode root) {
        if(root == null) return true;

        return helper(root.left, root.right);
    }

    private boolean helper(TreeNode left, TreeNode right) {
        if(left == null && right == null) return true;

        if(left == null || right == null || left.val != right.val) return false;

        return helper(left.left, right.right) && helper(left.right, right.left);
    }
}
