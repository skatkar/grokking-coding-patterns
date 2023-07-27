package org.educative.treedfs;

/**
 * Question : Leetcode129
 */
public class SumPathNumbers {
    public int sumNumbers(TreeNode root) {
        return helper(root, root.val);
    }

    private int helper(TreeNode root, int currentSum) {
        if(root == null) return 0;

        if(root.left == null && root.right == null)
            return currentSum * 10 + root.val;

        int leftSubtreeSum = helper(root.left, currentSum * 10 + root.val);
        int rightSubtreeSum = helper(root.right, currentSum * 10 + root.val);

        return leftSubtreeSum + rightSubtreeSum;
    }
}
