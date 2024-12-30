package org.grokking.subarray;

public class Leetcode713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(nums == null || nums.length == 0) return 0;

        int product = 1;
        int left = 0, right = 0;
        int total = 0;

        for(; right < nums.length; right++) {
            product *= nums[right];

            while(product >= k) {
                product /= nums[left];
                left++;
            }
            total += right - left + 1;
        }

        return total;
    }
}
