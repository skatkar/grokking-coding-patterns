package org.educative.treedfs;

public class Diameter {
    public int diameterOfBinaryTree(TreeNode root) {
        int treeDiameter = 0;
        findDiameter(root, treeDiameter);
        return treeDiameter;
    }

    private int findDiameter(TreeNode root, int treeDiameter) {
        if(root == null) return 0;

        int left = findDiameter(root.left, treeDiameter);
        int right = findDiameter(root.right, treeDiameter);

        treeDiameter = Math.max(treeDiameter, left + right);

        return 1 + Math.max(left, right);
    }
}
