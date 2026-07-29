package org.grokking.twopointers;

import java.util.Arrays;

public class Leetcode16 {
    public int threeSumClosest(int[] nums, int target) {
        int smallDiff = Integer.MAX_VALUE;

        int length = nums.length;
        Arrays.sort(nums);

        int closest = nums[0] + nums[1] + nums[2];
        for(int pivot = 0; pivot < length; pivot++){
            int left = pivot + 1, right = length - 1;
            while(left < right){
                int sum = nums[pivot] + nums[left] + nums[right];

                if(Math.abs(target - sum) < Math.abs(target - closest)){
                    closest = sum;
                }

                if(sum == target){
                    return target;
                }else if(sum < target){
                    left++;
                } else {
                    right--;
                }
            }
        }

        return closest;
    }
}
