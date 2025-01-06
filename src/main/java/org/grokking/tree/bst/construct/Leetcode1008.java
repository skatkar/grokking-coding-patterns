package org.grokking.tree.bst.construct;

import org.grokking.tree.bfs.TreeNode;

public class Leetcode1008 {
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);

        for(int i=1; i < preorder.length; i++) {
            root = create(root, preorder[i]);
        }

        return root;
    }

    private TreeNode create(TreeNode root, int val) {
        if(root == null){
            root = new TreeNode(val);
            return root;
        }

        if(val <= root.val){
            root.left = create(root.left, val);
        }else {
            root.right = create(root.right, val);
        }

        return root;
    }
}
