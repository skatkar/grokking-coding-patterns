package org.grokking.twopointers;

import java.util.Arrays;

public class Leetcode259 {
    public int threeSumSmaller(int[] nums, int target) {
        int triplets = 0, length = nums.length;
        Arrays.sort(nums);

        for(int pivot = 0; pivot < length;pivot++) {
            int left = pivot + 1, right = length - 1;
            while(left < right) {
                int sum = nums[pivot] + nums[left] + nums[right];
                if(sum == target) {
                    left++;
                    right--;
                } else if(sum < target) {
                    triplets++;
                    left++;
                }else {
                    right--;
                }
            }
        }
        return triplets;
    }
}
