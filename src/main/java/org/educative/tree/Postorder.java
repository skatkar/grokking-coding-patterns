package org.educative.tree;

import org.educative.tree.bfs.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Postorder {
    public List<Integer> getPostorder_iterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> nodes = new ArrayList<>();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()) {
            // Keep on going to the left - L
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else {
                TreeNode temp = stack.peek().right;
                // If nothing on the right
                if(temp == null) {
                    temp = stack.pop();
                    nodes.add(temp.val); // Use this value - D
                    // We are done with LRD of the parent node so pop it out
                    while(!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.pop();
                        nodes.add(temp.val);
                    }
                }else { // go to the right - R
                    curr = temp;
                }
            }
        }

        return nodes;
    }

    public List<Integer> getPostorder_rec(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        postorder(root, nodes);
        return nodes;
    }

    private void postorder(TreeNode root, List<Integer> nodes) {
        postorder(root.left, nodes);
        postorder(root.right, nodes);
        nodes.add(root.val);
    }
}
