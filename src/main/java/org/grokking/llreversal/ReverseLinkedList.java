package org.grokking.llreversal;

class LinkedListNode {
    public int data;
    public LinkedListNode next;
    // Constructor will be used to make a LinkedListNode type object
    public LinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }
}

/**
 * Question: Given the head of a singly linked list, reverse the linked list and return its updated head.
 * Link : <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/xV8w3jqQ4Xr">...</a>
 */
public class ReverseLinkedList {
    public static LinkedListNode reverse(LinkedListNode head) {
        if(head == null || head.next == null) return head;

        LinkedListNode prev, current, next;
        prev = null;
        current = head;
        next = current.next;

        while(next != null) {
            current.next = prev;
            prev = current;
            current = next;
            next = next.next;
        }

        current.next = prev;

        return current;
    }
}
