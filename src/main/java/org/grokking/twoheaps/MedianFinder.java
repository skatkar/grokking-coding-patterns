package org.grokking.twoheaps;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Question: Find Median from a Data Stream
 * Link : <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/7Xx1Y64gADA">...</a>
 */
public class MedianFinder {

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    // TC : O(log n)
    // SC : O(n)
    public void addNum(int num) {
        // maxHeap size should always be greater than minHeap by 1 (always)
        if(maxHeap.isEmpty() || num < maxHeap.peek()) {
            maxHeap.add(num);
        }else {
            minHeap.add(num);
        }

        // re-balance the heaps
        if(maxHeap.size() < minHeap.size()){
            maxHeap.add(minHeap.poll());
        }else if(maxHeap.size() - minHeap.size() > 1) {
            minHeap.add(maxHeap.poll());
        }
    }

    // TC : O(1)
    // SC : O(1)
    public double findMedian() {
        if(maxHeap.size() == minHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
        return maxHeap.peek() * 1.0;
    }
}
