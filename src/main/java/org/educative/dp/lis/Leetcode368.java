package org.educative.dp.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Leetcode368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        return dynamic(nums);
    }

    // Leetcode 300 with printing Longest Increasing subsequence
    private List<Integer> dynamic(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        // To store the indexes of the last element in the longest increasing subsequence element obtained so far
        int[] hash = new int[n];
        List<Integer> result = new ArrayList<>();

        int maxi = 1, lastIndex = 0;
        for(int i=0; i < n;i++) {
            hash[i] = i;
            for(int prev=0; prev < i; prev++){
                // Example - [1 2 4 7 28]
                // Here, while we are at 28, there are two possible subsets - 1 2 4 28 & 1 7 28
                // Out of these two subsets the first one is the longest
                // The below condition will consider this.
                if(nums[i] % nums[prev] == 0 && dp[i] < 1 + dp[prev]){
                    dp[i] = Math.max(dp[i], 1 + dp[prev]);
                    hash[i] = prev;
                }
            }
            if(dp[i] > maxi) {
                maxi = dp[i];
                lastIndex = i;
            }
        }

        result.add(nums[lastIndex]);
        while(hash[lastIndex] != lastIndex){
            lastIndex = hash[lastIndex];
            result.add(nums[lastIndex]);
        }

        Collections.reverse(result);
        return result;
    }
}
