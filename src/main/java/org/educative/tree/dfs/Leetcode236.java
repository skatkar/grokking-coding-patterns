package org.educative.tree.dfs;

import org.educative.tree.bfs.TreeNode;

public class Leetcode236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // If it is a leaf node or its is non-leaf node and one of p & q
        if(root == null || root == p || root == q)
            return root;

        TreeNode leftChild = lowestCommonAncestor(root.left, p, q);
        TreeNode rightChild = lowestCommonAncestor(root.right, p, q);

        // If left and right, both are not returning null that means this node is a common ancestor
        if(leftChild != null && rightChild != null)
            return root;
        return leftChild == null ? rightChild : leftChild;
    }
}
