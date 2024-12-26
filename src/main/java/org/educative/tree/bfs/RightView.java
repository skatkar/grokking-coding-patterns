package org.educative.tree.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightView {
    public static void main(String[] args) {
        RightView question = new RightView();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        List<Integer> traverse = question.traverse(root);
        traverse.forEach(System.out::println);
    }

    public List<Integer> traverse(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        List<TreeNode> trees = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i=0; i < size; i++) {
                TreeNode current = queue.poll();
                if(i == size - 1)
                    trees.add(current);

                if(current.left != null) queue.add(current.left);
                if(current.right != null) queue.add(current.right);
            }
        }

        //Do not modify this
        for(TreeNode tree : trees){
            result.add(tree.val);
        }
        return result;
    }
}
