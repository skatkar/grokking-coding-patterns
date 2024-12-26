package org.educative.treedfs;

/**
 * Question : Leetcode 112
 */
public class TreePathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;

        // we found a leaf node where the sum is matching with the current node.
        if(root.val == targetSum && root.left == null && root.right == null)
            return true;

        // check the left and right subtree
        // every time we go to the child node, reduce the target sum by the current node.
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
