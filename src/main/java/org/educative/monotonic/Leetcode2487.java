package org.educative.monotonic;

import java.util.Stack;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }


public class Leetcode2487 {
    public ListNode removeNodes(ListNode head) {
        Stack<ListNode> stack = new Stack<>();

        ListNode curr = head;

        while(curr != null) {

            while(!stack.isEmpty() && curr.val > stack.peek().val) {
                stack.pop();
            }

            if(stack.isEmpty())
                head = curr;
            else
                stack.peek().next = curr;
            stack.push(curr);
            curr = curr.next;
        }

        return head;
    }

    public ListNode removeNodes2(ListNode head) {
        Stack<ListNode> stack = new Stack<>();

        ListNode cur = head;
        while (cur != null) {
            while (!stack.isEmpty() && stack.peek().val < cur.val) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().next = cur;
            }
            stack.push(cur);
            cur = cur.next;
        }

        return stack.isEmpty() ? null : stack.get(0);
    }
}
