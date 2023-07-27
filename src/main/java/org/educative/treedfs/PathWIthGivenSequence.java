package org.educative.treedfs;

/**
 * Question : Leetcode 1430
 */
public class PathWIthGivenSequence {

    public boolean findPath(TreeNode root, int[] sequence) {
        return helper(root, sequence, 0);
    }

    private boolean helper(TreeNode root, int[] sequence, int sequenceIndex) {
        if(root == null) return false;

        // current node value is not equal to sequenceIndex location number
        if(sequenceIndex >= sequence.length || root.val != sequence[sequenceIndex])
            return false;

        // The current node is a leaf node and it is matching with the last sequence number
        if(root.left == null && root.right == null && root.val == sequence[sequence.length - 1])
            return true;

        return helper(root.left, sequence, sequenceIndex + 1) ||
                helper(root.right, sequence, sequenceIndex + 1);
    }
}
