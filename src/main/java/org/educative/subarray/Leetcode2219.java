package org.educative.subarray;

import java.util.Arrays;

public class Leetcode2219 {
    public static void main(String[] args) {
        Leetcode2219 question = new Leetcode2219();
        long answer = question.maximumSumScore(new int[]{4,3,-2,5});
        System.out.println("answer = " + answer);
    }

    public long maximumSumScore(int[] nums) {
        if(nums == null || nums.length == 0) return 0l;

        long rSum = Arrays.stream(nums).sum(), lSum = 0;
        long max = Long.MIN_VALUE;

        for(int i=0; i < nums.length; i++) {
            lSum += nums[i];
            max = Math.max(max, Math.max(lSum, rSum - lSum + nums[i]));
        }

        return max;
    }
}
