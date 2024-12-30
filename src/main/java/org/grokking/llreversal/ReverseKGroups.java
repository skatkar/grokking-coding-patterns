package org.grokking.llreversal;

public class ReverseKGroups {
    /**
     * Leetcode : <a href="https://leetcode.com/problems/reverse-nodes-in-k-group/">...</a>
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        ListNode begin = dummy;
        dummy.next = head;

        int i=1;
        while(head != null) {
            if(i % k == 0){
                begin = reverse(begin, head.next);
                head = begin.next;
            }else{
                head = head.next;
            }
            i++;
        }

        return dummy.next;
    }

    /**
     * Design gurus: <a href="https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6394dfe0a05eca17327534ff">...</a>
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        ListNode begin = dummy;
        dummy.next = head;

        int i=1;
        while(head != null) {
            if(i % k == 0){
                begin = reverse(begin, head.next);
                head = begin.next;
            }else{
                head = head.next;
            }
            i++;
        }

        // leftover nodes which not fitting in the group of k nodes
        if(begin.next != null) {
            reverse(begin, head);
        }

        return dummy.next;
    }

    private ListNode reverse(ListNode begin, ListNode end){
        ListNode prev = begin;
        ListNode curr = begin.next;
        ListNode first = begin.next;
        ListNode fast = curr.next;

        while(fast != end) {
            curr.next = prev;
            prev = curr;
            curr = fast;
            fast = fast.next;
        }

        curr.next = prev;
        begin.next = curr;
        first.next = end;

        return first;
    }


}
