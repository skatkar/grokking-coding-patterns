package org.grokking.tree.concepts;

import org.grokking.tree.bfs.TreeNode;

import java.util.Stack;

public class Leetcode98 {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long lower, long upper){
        if(root == null) return true;
        if(root.val <= lower || root.val >= upper) return false;

        return isValidBST(root.left, lower, root.val) &&
                isValidBST(root.right, root.val, upper);
    }

    public boolean isValidBST_iterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode prev = null;
        while(root != null || !stack.isEmpty()) {
            // Go as left as possible
            while(root != null) {
                stack.push(root);
                root = root.left;
            }

            // Reached the leaf node
            root = stack.pop();

            // prev node shouldn't be greater than the current node
            if(prev != null && prev.val >= root.val) return false;
            prev = root;
            root = root.right;
        }

        return true;
    }
}
