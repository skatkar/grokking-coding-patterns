package org.grokking.tree.concepts;

import org.grokking.tree.bfs.TreeNode;

public class CeilInBST {
    public int findCeil(TreeNode root, int key) {
        int ceil = -1;
        while(root != null) {
            if(root.val == key)
                return root.val;

            if(key > root.val)
                root = root.right;
            else {
                ceil = root.val;
                root = root.left;
            }
        }

        return ceil;
    }
}
