package org.grokking.treedfs;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TreePathSumIII {
    public int pathSum(TreeNode root, int targetSum) {
        List<Integer> paths = new ArrayList<>();

        return helper(root, paths, targetSum);
    }

    private int helper(TreeNode root, List<Integer> paths, int targetSum) {
        if(root == null) return 0;

        paths.add(root.val);
        int pathCount = 0;
        int pathSum = 0;

        // from each current node, traverse the tree in the reverse and see if we get any path having the expected sum
        ListIterator<Integer> iterator = paths.listIterator(paths.size()); // it will put the pointer at the back of the list
        while(iterator.hasPrevious()){
            pathSum += iterator.previous();
            if(pathSum == targetSum)
                pathCount++;
        }

        // find the path count in the left sub tree and the right sub tree
        pathCount += helper(root.left, paths, targetSum);
        pathCount += helper(root.right, paths, targetSum);

        // remove the current node from the path traversed so far
        paths.remove(paths.size() - 1);

        return pathCount;
    }
}
