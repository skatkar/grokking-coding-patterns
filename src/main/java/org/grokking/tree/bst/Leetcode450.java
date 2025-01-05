package org.grokking.tree.bst;

import org.grokking.tree.bfs.TreeNode;

public class Leetcode450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)
            return null;

        // 1. Identify the correct subtree where we have to do the modifications
        if(root.val < key){
            root.right = deleteNode(root.right, key);
        }else if(root.val > key){
            root.left = deleteNode(root.left, key);
        }else { // 2. BY this time, we identified the correct subtree, and the current node matches the key
            // So, let's actually delete it.
            // 2.1 If the node to be deleted is a leaf node
            if(root.left == null && root.right == null){
                return null;
            }

            // 2.2 If this node has one child
            if(root.left == null)
                return root.right;

            if(root.right == null)
                return root.left;

            // 2.3 None of the above then find the inorder successor - leftmost node on the right subtree.
            TreeNode inSuccessor = findInorderSuccessor(root.right);
            root.val = inSuccessor.val;

            // 2.4 We took a node from the right subtree, so adjust the right subtree
            root.right = deleteNode(root.right, inSuccessor.val);
        }

        return root;
    }

    private TreeNode findInorderSuccessor(TreeNode root) {
        while(root.left != null) {
            root = root.left;
        }
        return root;
    }
}
