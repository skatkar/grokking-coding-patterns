package org.educative.llreversal;

public class RotateList {
    /**
     * Leetcode : <a href="https://leetcode.com/problems/rotate-list/description/">...</a>
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;

        int totalNodes = 1;
        ListNode lastNodeInGivenList = head;
        while(lastNodeInGivenList.next != null) {
            totalNodes++;
            lastNodeInGivenList = lastNodeInGivenList.next;
        }

        // we found the last node of a given linked list
        // Point it to the existing head
        lastNodeInGivenList.next = head;

        // k might be beyond the available nodes
        k %= totalNodes;

        ListNode lastNodeOfListToRotate = head;
        for(int i=1; i < totalNodes - k; i++) { // skipping these many nodes first then doing the manipulation
            lastNodeOfListToRotate = lastNodeOfListToRotate.next;
        }
        head = lastNodeOfListToRotate.next;
        lastNodeOfListToRotate.next = null;

        return head;
    }
}
