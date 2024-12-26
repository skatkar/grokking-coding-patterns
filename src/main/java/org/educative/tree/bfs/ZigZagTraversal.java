package org.educative.tree.bfs;

import java.util.*;

public class ZigZagTraversal {

    public static void main(String[] args) {
        ZigZagTraversal question = new ZigZagTraversal();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        List<List<Integer>> traverse = question.traverse(root);
        traverse.forEach(System.out::println);

        List<List<Integer>> traverse2 = question.traverse2(root);
        traverse2.forEach(System.out::println);
    }

    public List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int qLevel = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> level = new ArrayList<>();

            for(int i=0; i < size; i++) {
                TreeNode current = queue.poll();

                level.add(current.val);
                if(current.left != null) queue.add(current.left);
                if(current.right != null) queue.add(current.right);

            }

            if(qLevel++ % 2 != 0) Collections.reverse(level);
            result.add(level);
        }

        return result;
    }

    public List<List<Integer>> traverse2(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean leftToRight = true;
        while(!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> level = new ArrayList<>();

            for(int i=0; i < size; i++) {
                TreeNode current = queue.poll();

                if(leftToRight) level.add(current.val);
                else level.add(0, current.val);

                if(current.left != null) queue.add(current.left);
                if(current.right != null) queue.add(current.right);

            }

            leftToRight = !leftToRight;
            result.add(level);
        }

        return result;
    }
}
