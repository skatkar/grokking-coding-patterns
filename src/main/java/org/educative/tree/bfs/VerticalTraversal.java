package org.educative.tree.bfs;

import java.util.*;

class Element {
    TreeNode node;
    int row,col;

    public Element(int row, int col,TreeNode node) {
        this.node = node;
        this.row = row;
        this.col = col;
    }
}

/**
 * Question: LeetCode 987
 */
public class VerticalTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        VerticalTraversal question = new VerticalTraversal();
        List list = question.verticalTraversal(root);
        list.forEach(System.out::println);
    }

    // TC: O(n log n + k log k) => n log n for the PriorityQueue, k log k for the TreeMap (k is the number of columns)
    // SC: O(n) => worst case, we'll be storing n/2 elements into the queue.
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if(root == null) return Collections.emptyList();

        List<List<Integer>> result = new ArrayList<>();
        TreeMap<Integer, List<Integer>> valsByColumn = new TreeMap<>();
        PriorityQueue<Element> queue = new PriorityQueue<>((a,b) -> {
            if(a.row == b.row)
                return a.node.val - b.node.val;
            return a.row - b.row;
        });
        queue.add(new Element(0,0, root));

        while(!queue.isEmpty()) {
            Element current = queue.poll();
            List<Integer> list = valsByColumn.getOrDefault(current.col, new ArrayList<>());
            list.add(current.node.val);
            valsByColumn.put(current.col, list);

            if(current.node.left != null)
                queue.add(new Element(current.row + 1, current.col- 1, current.node.left));
            if(current.node.right != null)
                queue.add(new Element(current.row + 1,  current.col + 1, current.node.right));
        }

        valsByColumn.forEach((k,v) -> result.add(v));
        return result;
    }
}
