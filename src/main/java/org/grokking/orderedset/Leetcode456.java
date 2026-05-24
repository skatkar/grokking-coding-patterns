package org.grokking.orderedset;

import java.util.Stack;
import java.util.TreeSet;

public class Leetcode456 {
    public boolean find132pattern(int[] nums) {
        // This is to track the second greater number
        TreeSet<Integer> second = new TreeSet<>();
        Stack<Integer> stack = new Stack<>();

        for(int i=nums.length - 1; i >= 0; i--) {

            // Pop out all the elements from the stack which are smaller than this number
            while(!stack.isEmpty() && stack.peek() < nums[i]) {
                second.add(stack.pop());
            }

            // Check if there is still any number greater than the current number
            // The greatest number is already tracked in the stack.
            // This set data structure will track the second greater number
            if(!stack.isEmpty() && !second.isEmpty()) {
                Integer number = second.higher(nums[i]);
                if(number != null) return true;
            }

            // Add the elements to the stack
            stack.push(nums[i]);
        }

        return false;
    }
}
