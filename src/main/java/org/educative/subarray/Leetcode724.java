package org.educative.subarray;

import java.util.Arrays;

public class Leetcode724 {
    public static void main(String[] args) {
        Leetcode724 question = new Leetcode724();
        int pivotIndex = question.pivotIndex(new int[]{1, 7, 3, 6, 5, 6});
        System.out.println("pivotIndex = " + pivotIndex);
    }

    public int pivotIndex(int[] nums) {
        if(nums == null || nums.length == 0) return -1;

        int lSum = 0;
        int rSum = Arrays.stream(nums).sum();
        for(int i=0; i < nums.length; i++) {
            rSum -= nums[i];

            // numbers from left are deducted from rSum and it is holding the sum of right numbers only
            if(lSum == rSum) return i;

            lSum += nums[i];
        }

        return -1;
    }
}
