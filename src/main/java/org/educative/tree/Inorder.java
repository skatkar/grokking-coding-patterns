package org.educative.tree;

import org.educative.tree.bfs.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Inorder {
    public List<Integer> getInord_iterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> inorder = new ArrayList<>();
        TreeNode node = root;
        while(true) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            }else{
                if(stack.isEmpty()){
                    break;
                }
                node = stack.pop();
                inorder.add(node.val);
                node = node.right;
            }
        }

        return inorder;
    }

    public List<Integer> getInord_rec(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        inorder(root, nodes);
        return nodes;
    }

    private void inorder(TreeNode root, List<Integer> nodes) {
        if(root == null)
            return;

        inorder(root.left, nodes);
        nodes.add(root.val);
        inorder(root.right, nodes);
    }
}
