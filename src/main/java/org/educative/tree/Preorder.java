package org.educative.tree;

import org.educative.tree.treebfs.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Preorder {
    public List<Integer> getPreord_iterative(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while(!stack.isEmpty()) {
            TreeNode current = stack.pop();
            nodes.add(current.val);

            if(current.left != null){
                stack.push(current.left);
            }

            if(current.right != null){
                stack.push(current.right);
            }
        }

        return nodes;
    }

    public List<Integer> getPreord_rec(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        preorder(root, nodes);
        return nodes;
    }

    private void preorder(TreeNode root, List<Integer> nodes) {
        if(root == null)
            return;

        nodes.add(root.val);
        preorder(root.left, nodes);
        preorder(root.right, nodes);
    }

}
