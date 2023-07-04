package org.educative.twoheaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    public double[] medianSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) return new double[]{};

        List<Double> result = new ArrayList<>();
        minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a,b));
        maxHeap = new PriorityQueue<>((a,b)->(b.compareTo(a)));

        for(int i=0; i < nums.length; i++) {
            // 1. Add number to the correct heap
            if(maxHeap.isEmpty() || nums[i] <= maxHeap.peek()) {
                maxHeap.add(nums[i]);
            }else
                minHeap.add(nums[i]);

            // 2. re-balance the heap as we added a number to the heap
            rebalance();

            // 3. We reached the window limit, let's calculate the median
            if(i + 1 >= k) {
                if(maxHeap.size() == minHeap.size()) {
                    result.add(minHeap.peek() / 2.0 + maxHeap.peek() / 2.0);
                }else{
                    result.add(maxHeap.peek() * 1.0);
                }

                // 3.1. Reduce the window size by 1
                int outgoing = nums[i - k + 1];

                // the below methods are worst performing and would cause TLE on leetcode
                // Try to find out a way to handle this in O(1).
                // Possibly putting in a map and deleting only if the top of the heaps contain any of the number in map.
                if(outgoing <= maxHeap.peek()) maxHeap.remove(outgoing);
                else minHeap.remove(outgoing);

                rebalance();
            }
        }

        return result.stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
    }

    private void rebalance() {
        if(maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }else if(maxHeap.size() - minHeap.size() > 1)
            minHeap.add(maxHeap.poll());
    }
}
