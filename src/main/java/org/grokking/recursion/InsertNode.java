package org.grokking.recursion;

public class InsertNode {
    public TreeNode insert(TreeNode root, int value) {
        if(root == null)
            return new TreeNode(value);

        if(value < root.val)
            root.left = insert(root.left, value);
        else if(value > root.val)
            root.right = insert(root.right, value);
        return root;
    }
}
