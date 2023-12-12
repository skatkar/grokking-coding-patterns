package org.educative.subarray.practice;

import java.util.HashMap;
import java.util.Map;

public class Leetcode325 {
    // An array can contain negative numbers as well.
    // Sliding window can't guarantee that increasing size would give the max sub array sequentially.
    public int maxSubArrayLen(int[] nums, int k) {
        if(nums == null || nums.length < k) return 0;

        int sum = 0;
        int maxSize = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i < nums.length; i++){
            sum += nums[i];

            // The Cumulative sum is exactly k
            if(sum == k)
                maxSize = i + 1;
            else if (map.containsKey(sum - k)) { // The cumulative sum is less/more than k
                maxSize = Math.max(maxSize, i - map.get(sum - k));
            }

            // We are not resetting it as we want the max sub array size.
            // If we get the same sum again, then whichever occurred first should be considered so that it'll possibly give the longest sub array.
            map.putIfAbsent(sum, i);
        }

        return maxSize == Integer.MIN_VALUE ? 0 : maxSize;
    }
}
