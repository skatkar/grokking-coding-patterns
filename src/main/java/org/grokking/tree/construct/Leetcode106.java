package org.grokking.tree.construct;

import org.grokking.tree.bfs.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Leetcode106 {
    int idx;
    Map<Integer,Integer> indexes;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(postorder == null || inorder == null || postorder.length == 0 || inorder.length == 0) return null;
        indexes = new HashMap<>();
        idx = postorder.length - 1;
        // Storing the position of each element in inorder array in a map
        // This will help us to do the lookup in a constant time
        for(int i=0; i < inorder.length; i++) {
            indexes.put(inorder[i], i);
        }

        return helper(postorder, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] postorder, int start, int end){
        // Child node condition reached
        if(start > end) return null;

        int rootVal = postorder[idx--];
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = indexes.get(rootVal);

        // Postorder traversal is left, right, root. Since you have to start with the root, you must go through the postorder list in reverse order from right to left.
        // Therefore, the nodes will be seen in the order root, right, left.
        root.right = helper(postorder, rootIndex + 1, end);
        root.left = helper(postorder, start, rootIndex - 1);

        return root;
    }
}
