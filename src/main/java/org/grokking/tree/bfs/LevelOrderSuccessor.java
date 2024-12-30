package org.grokking.tree.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderSuccessor {
    public static void main(String[] args) {
        LevelOrderSuccessor question = new LevelOrderSuccessor();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);


        TreeNode successor = question.findSuccessor(root, 3);
        System.out.println("successor.val = " + successor.val);
    }

    public TreeNode findSuccessor(TreeNode root, int key) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeNode result = null;

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i=0; i < size; i++) {
                TreeNode current = queue.poll();

                if(current.left != null) queue.add(current.left);
                if(current.right != null) queue.add(current.right);

                if(current.val == key) return queue.peek();
            }
        }

        return result;
    }
}
