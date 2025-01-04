package org.grokking.tree.bst;

import org.grokking.tree.bfs.TreeNode;

public class Leetcode700 {
    public TreeNode searchBST(TreeNode root, int val) {
        while(root != null && root.val != val) {
            root = val < root.val ? root.left : root.right;
        }

        return root;
    }
    public TreeNode searchBST_rec(TreeNode root, int val) {
        if(root == null || root.val == val)
            return root;
        if(val < root.val)
            return searchBST(root.left, val);
        return searchBST(root.right,val);
    }
}
