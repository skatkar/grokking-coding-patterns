package org.educative.tree.treebfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelAverage {
    public static void main(String[] args) {
        LevelAverage question = new LevelAverage();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        List<Double> levelAverages = question.findLevelAverages(root);
        levelAverages.forEach(System.out::println);
    }

    public List<Double> findLevelAverages(TreeNode root) {
        List<Double> result = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();

            double levelSum = 0.0;

            for(int i=0; i < size; i++) {
                TreeNode current = queue.poll();

                levelSum += current.val;
                if(current.left != null) queue.add(current.left);
                if(current.right != null) queue.add(current.right);

            }

            result.add(levelSum / size);
        }

        return result;
    }
}
