package org.grokking.binarysearch;

public class Leetcode153 {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int low = 0, high = nums.length - 1;
        int min = Integer.MAX_VALUE;
        while(low <= high) {
            int mid = low + (high - low) / 2;

            // Right now, the left half is sorted.
            // Pick the smallest from this half and discard this half.
            if(nums[low] <= nums[mid]){
                min = Math.min(min, nums[low]);
                low = mid + 1;
            }else{ // in this half, mid points to the minimum
                min = Math.min(min, nums[mid]);
                high = mid - 1;
            }
        }

        return min;
    }
}
