package org.educative.binarysearch;

import java.util.Arrays;

public class Leetcode1011 {
    // TC: O(log n * n)
    public int shipWithinDays(int[] weights, int days) {
        int max = Arrays.stream(weights).max().getAsInt();
        int totalSum = Arrays.stream(weights).sum();

        // whatever is the capacity, it ranges between the max weight among the array and the total sum of all the weights
        return binarySearch(weights,days, max, totalSum);
    }

    private int binarySearch(int[] weights, int days, int max, int totalSum) {
        int low = max, high = totalSum;

        while(low <= high) {
            int mid = low + (high - low) / 2;
            int daysRequired = getNoOfDays(weights, mid);
            if(daysRequired <= days) { // trying to find out further smaller number of days
                high = mid - 1;
            }else { // the required number of days does not meet the day threshold
                low = mid + 1;
            }
        }

        return low;
    }

    // Given a capacity, this will help us find out the number of days required to ship the weights up to that capacity.
    private int getNoOfDays(int[] weights, int capacity) {
        int days = 1, load = 0;
        for (int weight : weights) {
            if (load + weight > capacity) {
                days++;
                load = weight;
            } else {
                load += weight;
            }
        }
        return days;
    }
}
