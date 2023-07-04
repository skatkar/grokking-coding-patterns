package org.educative.twoheaps;

import java.util.*;

class Solution {

    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    public Solution() {
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

    public double[] medianSlidingWindow2(int[] nums, int k) {
        // To store the medians
        List<Double> medians = new ArrayList<>();

        // To keep track of the numbers that need to be removed from the heaps
        HashMap<Integer, Integer> outgoingNum = new HashMap<>();

        // Max heap
        PriorityQueue<Integer> smallList = new PriorityQueue<>(Collections.reverseOrder());

        // Min heap
        PriorityQueue<Integer> largeList = new PriorityQueue<>();

        // Initialize the max heap
        for (int i = 0; i < k; i++)
            smallList.offer(nums[i]);

        // Transfer the top 50% of the numbers from max heap to min heap
        for (int i = 0; i < k / 2; i++)
            largeList.offer(smallList.poll());

        // Variable to keep the heaps balanced
        int balance = 0;

        int i = k;
        while (true) {
            // If the window size is odd
            if ((k & 1) == 1)
                medians.add((double) (smallList.peek()));
            else
                medians.add(((long)smallList.peek() + (long)largeList.peek()) * 0.5);

            // Break the loop if all elements have been processed
            if (i >= nums.length)
                break;

            // Outgoing number
            int outNum = nums[i - k];

            // Incoming number
            int inNum = nums[i];
            i++;

            // If the outgoing number is from max heap
            if (outNum <= smallList.peek())
                balance -= 1;
            else
                balance += 1;

            // Add/Update the outgoing number in the hash map
            if (outgoingNum.containsKey(outNum))
                outgoingNum.put(outNum, outgoingNum.get(outNum) + 1);
            else
                outgoingNum.put(outNum, 1);

            // If the incoming number is less than the top of the max heap, add it in that heap
            // Otherwise, add it in the min heap
            if (!smallList.isEmpty() && inNum <= smallList.peek()) {
                balance += 1;
                smallList.offer(inNum);
            } else {
                balance -= 1;
                largeList.offer(inNum);
            }

            // Re-balance the heaps
            if (balance < 0)
                smallList.offer(largeList.poll());
            else if (balance > 0)
                largeList.offer(smallList.poll());

            // Since the heaps have been balanced, we reset the balance variable to 0.
            // This ensures that the two heaps contain the correct elements for the calculations to be performed on the elements in the next window.
            balance = 0;

            // Remove invalid numbers present in the hash map from top of max heap
            while (!smallList.isEmpty() && outgoingNum.containsKey(smallList.peek()) && outgoingNum.get(smallList.peek()) > 0)
                outgoingNum.put(smallList.peek(), outgoingNum.get(smallList.poll()) - 1);

            // Remove invalid numbers present in the hash map from top of min heap
            while (!largeList.isEmpty() && outgoingNum.containsKey(largeList.peek()) && outgoingNum.get(largeList.peek()) > 0)
                outgoingNum.put(largeList.peek(), outgoingNum.get(largeList.poll()) - 1);
        }

        return medians.stream().mapToDouble(Double::doubleValue).toArray();
    }
}

