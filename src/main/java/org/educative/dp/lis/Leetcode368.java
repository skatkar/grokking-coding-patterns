package org.educative.dp.lis;

import java.util.ArrayList;
import java.util.List;

public class Leetcode368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
       return new ArrayList<>();
    }

    private int memoization(int index, int prevIndex, int[] nums, int[][] memo, List<Integer> result) {
        if(index == nums.length)
            return 0;

        // We're changing the coordinates of prev index
        if(memo[index][prevIndex + 1] != -1) return memo[index][prevIndex + 1];

        // Pick - Not pick scenario
        int len = memoization(index + 1, prevIndex, nums, memo, result);

        // Pick condition
        if(prevIndex == -1 || nums[index] % nums[prevIndex] == 0){
            len = Math.max(len, 1 + memoization(index + 1, index, nums, memo, result));
        }

        return memo[index][prevIndex + 1] = len;
    }
}
