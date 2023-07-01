package org.educative.llreversal;

class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

public class ReverseSubList {
    /**
     * Leetcode : <a href="https://leetcode.com/problems/reverse-linked-list-ii/">...</a>
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverse(ListNode head, int left, int right) {
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;

        // Bring pre before left node
        for(int i=0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode start = pre.next;
        ListNode then = start.next;

        // If the given list is 1 -> 2 -> 3 -> 4 -> 5 then the below operation would be like this
        // First iteration:  1 -> 3 -> 2 -> 4 -> 5
        // Second iteration: 1 -> 4 -> 3 -> 2 -> 5
        // In short, first 2 nodes will be reversed then 3 then 4 and so on
        for(int i=0; i < right - left; i++) {// from left node onwards we just need to traverse right - left nodes
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }


        return dummy.next;
    }
}
