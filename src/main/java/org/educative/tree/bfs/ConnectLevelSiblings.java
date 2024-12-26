package org.educative.tree.bfs;

import java.util.*;

/**
 * Question : <a href="https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63dd79d18dcac62aa5cc19bb">...</a>
 */
public class ConnectLevelSiblings {
    public static List<List<Integer>> connect(TreeNode root) {
        if(root == null) return Collections.emptyList();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();

            TreeNode current = queue.poll();

            if(current.left != null) queue.add(current.left);
            if(current.right != null) queue.add(current.right);

            for(int i=1; i < size; i++) {
                TreeNode temp = queue.poll();
                current.next = temp;
                current = temp;

                if(temp.left != null) queue.add(temp.left);
                if(temp.right != null) queue.add(temp.right);
            }
        }

        // Do not modify this
        return printLevelOrder(root);
    }

    public static List<List<Integer>> connect2(TreeNode root) {
        if(root == null) return Collections.emptyList();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();

            TreeNode prevNode = null;
            for(int i=0; i < size; i++) {
                TreeNode current = queue.poll();
                if(prevNode != null)
                    prevNode.next = current;
                prevNode = current;

                if(current.left != null) queue.add(current.left);
                if(current.right != null) queue.add(current.right);
            }
        }

        // Do not modify this
        return printLevelOrder(root);
    }

    // level order traversal using 'next' pointer
    public static List<List<Integer>> printLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        TreeNode nextLevelRoot = root;
        while (nextLevelRoot != null) {
            List<Integer> innerResult = new ArrayList<>();
            TreeNode current = nextLevelRoot;
            nextLevelRoot = null;
            while (current != null) {
                innerResult.add(current.val);
                if (nextLevelRoot == null) {
                    if (current.left != null)
                        nextLevelRoot = current.left;
                    else if (current.right != null)
                        nextLevelRoot = current.right;
                }
                current = current.next;
            }
            result.add(innerResult);
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

        List<List<Integer>> connect = ConnectLevelSiblings.connect2(root);
        connect.forEach(System.out::println);
    }
}
