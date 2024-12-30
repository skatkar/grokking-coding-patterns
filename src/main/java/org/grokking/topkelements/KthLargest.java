package org.grokking.topkelements;

import java.util.PriorityQueue;

/**
 * Kth largest element in a stream
 */
public class KthLargest {

    PriorityQueue<Integer> minHeap;
    int maxSize;

    // constructor to initialize topKHeap and add values in it
    public KthLargest(int k, int[] nums) {
        this.minHeap = new PriorityQueue<>();
        this.maxSize = k;

        for(int num : nums){
            minHeap.add(num);
        }

        while(minHeap.size() > maxSize){
            minHeap.poll();
        }
    }
    // adds an element in the topKHeap
    public int add(int val) {
        minHeap.add(val);

        if(minHeap.size() > maxSize){
            minHeap.poll();
        }

        return returnKthLargest();
    }
    // returns kth largest element from topKHeap
    public int returnKthLargest() {
        return minHeap.peek();
    }

}
