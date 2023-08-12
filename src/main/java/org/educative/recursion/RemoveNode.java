package org.educative.recursion;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }
}
public class RemoveNode {
    public static ListNode removeNodes(ListNode head, int target) {
        // Reached the end of the linked list
        if(head == null)
            return null;

        // The next of the current node will be decided by the recursive function
        head.next = removeNodes(head.next, target);

        // once we are out of the recursion, let's check the current node value
        // if it is target then return it's next
        if(head.val == target)
            return head.next;
        // else return itself
        return head;
    }



    public static void main(String[] args) {
        ListNode head = new ListNode(6);
        ListNode one = new ListNode(7);
        ListNode two = new ListNode(9);
        ListNode three = new ListNode(11);
        head.next = one;
        one.next = two;
        two.next = three;

        ListNode node = RemoveNode.removeNodes(head, 11);
        while(node != null) {
            System.out.println("node = " + node);
            node = node.next;
        }
    }
}
