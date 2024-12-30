package org.grokking.tree.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class MinDepth {
    public static void main(String[] args) {
        MinDepth question = new MinDepth();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);

        int depth = question.findDepth(root);
        System.out.println("depth = " + depth);
    }

    public int findDepth(TreeNode root) {
        if(root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int depth = 0;
        while (!queue.isEmpty()){
            depth++;
            int size = queue.size();

            for(int i=0; i < size; i++) {
                TreeNode current = queue.poll();
                if(current.left == null && current.right == null)
                    return depth;

                if(current.left != null) queue.add(current.left);
                if(current.right != null) queue.add(current.right);
            }
        }

        return 0;
    }
}
