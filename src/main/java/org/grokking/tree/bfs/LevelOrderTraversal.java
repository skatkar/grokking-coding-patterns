package org.grokking.tree.bfs;

import java.util.*;

public class LevelOrderTraversal {
    public static void main(String[] args) {
        LevelOrderTraversal question = new LevelOrderTraversal();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println(" *************** ");
        List<List<Integer>> traverse = question.traverse(root);
        traverse.forEach(System.out::println);

        System.out.println(" *************** ");
        List<List<Integer>> reverseTraverse = question.reverseLevelTraverse(root);
        reverseTraverse.forEach(System.out::println);

        System.out.println(" *************** ");
        List<List<Integer>> reversedTraverse = question.reverseTraverse(root);
        reversedTraverse.forEach(System.out::println);
    }

    public List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> level = new ArrayList<>();

            for(int i=0; i < size; i++) {
                TreeNode current = queue.poll();

                level.add(current.val);
                if(current.left != null) queue.add(current.left);
                if(current.right != null) queue.add(current.right);

            }

            result.add(level);
        }

        return result;
    }

    public List<List<Integer>> reverseLevelTraverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> level = new ArrayList<>();

            for(int i=0; i < size; i++) {
                TreeNode current = queue.poll();

                level.add(current.val);
                if(current.left != null) queue.add(current.left);
                if(current.right != null) queue.add(current.right);

            }
            Collections.reverse(level);
            result.add(level);
        }

        return result;
    }

    public List<List<Integer>> reverseTraverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> level = new ArrayList<>();

            for(int i=0; i < size; i++) {
                TreeNode current = queue.poll();

                level.add(current.val);
                if(current.left != null) queue.add(current.left);
                if(current.right != null) queue.add(current.right);

            }
            result.add(0,level);
        }

        return result;
    }
}
