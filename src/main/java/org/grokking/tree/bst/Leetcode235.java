package org.grokking.tree.bst;

import org.grokking.tree.bfs.TreeNode;

public class Leetcode235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;

        TreeNode current = root;

        while(current != null) {
            if(p.val < current.val && q.val < current.val) {
                current = current.left;
            }else if(p.val > current.val && q.val > current.val) {
                current = current.right;
            }else return current;

        }
        return null;
    }
}
