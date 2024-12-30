package org.grokking.tree.bfs;

import java.util.*;

/**
 * Question : <a href="https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63989c048d812da0aa12bff7">...</a>
 */
public class ConnectSuccessor {
    public static List<Integer> connect(TreeNode root) {
        if(root == null) return Collections.emptyList();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeNode prevNode = null;
        while(!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if(current.left != null) queue.add(current.left);
            if(current.right != null) queue.add(current.right);

            if(prevNode != null)
                prevNode.next = current;
            prevNode = current;
        }

        // Do no modify this
        return printLevelOrder(root);
    }

    // level order traversal using 'next' pointer
    public static List<Integer> printLevelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        List<Integer> result = ConnectSuccessor.connect(root);
        System.out.println("result = " + result);
    }
}
