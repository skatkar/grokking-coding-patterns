package org.educative.treedfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreePathSumII {
    // TC: The time complexity of the above algorithm is O(N^2), where ‘N’ is the total number of nodes in the tree.
    // This is due to the fact that we traverse each node once (which will take O(N)), and for every leaf node, we might have to store its path (by making a copy of the current path) which will take O(N).
    // SC: O(n log n)
    // Detailed Explanation : https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63dd748dc496b56f43554c4e
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if(root == null) return Collections.emptyList();

        List<List<Integer>> result = new ArrayList<>();

        helper(root, 0, targetSum, new ArrayList<>(), result);

        return result;
    }

    private void helper(TreeNode root, int currentSum, int targetSum, List<Integer> path, List<List<Integer>> result) {
        // end of the tree/crossed the leaf node
        if(root == null) return;

        currentSum += root.val;
        path.add(root.val);

        // If the current node is a leaf node
        if(root.left == null && root.right == null && (currentSum == targetSum)) {
                result.add(new ArrayList<>(path)); // causes extra time complexity as each element is copied to the new list
        }

        helper(root.left, currentSum, targetSum, path, result);
        helper(root.right, currentSum, targetSum, path, result);

        //once we come back out of the leaf node, remove it from the path collected so far
        path.remove(path.size() - 1);
    }
}
