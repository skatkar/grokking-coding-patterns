package org.grokking.tree.construct;

import org.grokking.tree.bfs.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Leetcode105 {
    int idx;
    Map<Integer,Integer> indexes;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) return null;
        indexes = new HashMap<>();
        // Storing the position of each element in inorder array in a map
        // This will help us to do the lookup in a constant time
        for(int i=0; i < inorder.length; i++) {
            indexes.put(inorder[i], i);
        }

        return helper(preorder, 0, preorder.length - 1);
    }

    private TreeNode helper(int[] preorder, int start, int end) {
        // Child node condition reached
        if(start > end) return null;

        int rootVal = preorder[idx++];
        int rootIndex = indexes.get(rootVal);

        TreeNode root = new TreeNode(rootVal);
        root.left = helper(preorder, start, rootIndex - 1);
        root.right = helper(preorder, rootIndex + 1, end);

        return root;
    }
}
