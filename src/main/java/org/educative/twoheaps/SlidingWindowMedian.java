package org.educative.twoheaps;

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    public Solution() {
        initializeHeaps();
    }

    private void initializeHeaps() {
        this.minHeap = new PriorityQueue<>();
        this.maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    private void insertNum(int num) {
        // always try to maintain the size of maxHeap >= minHeap by 1
        if(maxHeap.isEmpty() || maxHeap.peek() > num) {
            maxHeap.add(num);
        }else
            minHeap.add(num);

        reBalance();
    }

    // The below methods are worst performing and would cause TLE on leetcode
    // Try to find out a way to handle this in O(1).
    // Possibly putting in a map and deleting only if the top of the heaps contain any of the number in map.
    private void remove(int num) {
        if(num > maxHeap.peek()) {
            minHeap.remove(num);
        }
        else
            maxHeap.remove(num);
        reBalance();
    }

    private void reBalance() {
        // time to re-balance the heaps
        if(maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        }else if(maxHeap.size() < minHeap.size())
            maxHeap.add(minHeap.poll());
    }

    // TC : O(1)
    // SC : O(1)
    public double findMedian() {
        if(maxHeap.size() == minHeap.size()) {
            return minHeap.peek() / 2.0 + maxHeap.peek() / 2.0;
        }
        return maxHeap.peek();
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        for(int i=0; i < nums.length; i++) {
            insertNum(nums[i]);
            // = condition will work only once and later all the indexes will use > condition
            if(i + 1 >= k) {
                result[i-k+1] = findMedian();
                remove(nums[i-k+1]);
            }
        }
        return result;
    }
}

