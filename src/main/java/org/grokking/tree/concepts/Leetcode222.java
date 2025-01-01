package org.grokking.tree.concepts;

import org.grokking.tree.bfs.TreeNode;

public class Leetcode222 {
    // Explanation - https://www.youtube.com/watch?v=u-yWemKGWO0&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=33
    // TC : O(log n ^ 2)
    // SC : O(log n)
    public int countNodes(TreeNode root) {
        if(root == null) return 0;

        // Since this is a complete binary tree, we'll see if left and right heights are same or not
        // If not that means, some nodes must be missing on the right side as nodes are filled from left.
        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);

        if(leftHeight == rightHeight)
            return (2 << leftHeight) - 1; // Math.pow(2, leftHeight) - 1
        else
            return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int getLeftHeight(TreeNode root){
        int count = 0;
        while(root.left != null){
            count++;
            root = root.left;
        }

        return count;
    }

    private int getRightHeight(TreeNode root){
        int count = 0;
        while(root.right != null){
            count++;
            root = root.right;
        }

        return count;
    }

}
