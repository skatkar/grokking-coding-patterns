package org.educative.treedfs;

/**
 * Leetcode 543
 */
public class Diameter {
    public static void main(String[] args) {
        Diameter diameter = new Diameter();
        TreeNode root = new TreeNode(1);
        TreeNode firstLeft = new TreeNode(2);
        TreeNode firstRight = new TreeNode(3);
        TreeNode secondLeft = new TreeNode(4);
        TreeNode secondRight = new TreeNode(5);

        root.left = firstLeft;
        root.right = firstRight;
        firstLeft.left = secondLeft;
        firstLeft.right = secondRight;

        int answer = diameter.diameterOfBinaryTree(root);
        System.out.println("answer = " + answer);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[1];
        findDiameter(root, diameter);
        return diameter[0];
    }

    private int findDiameter(TreeNode root, int[] treeDiameter) {
        if(root == null) return 0;

        int left = findDiameter(root.left, treeDiameter);
        int right = findDiameter(root.right, treeDiameter);

        treeDiameter[0] = Math.max(treeDiameter[0], left + right);

        return 1 + Math.max(left, right);
    }
}
