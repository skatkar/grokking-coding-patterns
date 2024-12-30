package org.grokking.topkelements;

import java.util.PriorityQueue;

/**
 * Kth Largest Element in an Array
 */
public class KthLargestElement {
    public static int findKthLargest(int[] nums, int k) {

        if(nums == null || nums.length == 0) return 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int i=0;
        while(i < k) {
            minHeap.add(nums[i++]);
        }

        while(i < nums.length) {
            if(nums[i] > minHeap.peek()){
                minHeap.poll();
                minHeap.add(nums[i]);
            }
            i++;
        }

        return minHeap.poll();
    }
}
