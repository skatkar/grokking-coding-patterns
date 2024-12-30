package org.grokking.dp.oned;

import java.util.Arrays;

public class Leetcode198 {

    // The space optimized version of the below approach
    public int rob(int[] nums) {
        int prev2 = 0, prev = nums[0];
        int n = nums.length;

        for(int i=1; i < n; i++) {
            int pick = nums[i];

            if(i > 1){
                pick += prev2;
            }

            int notPick = prev;
            int curr = Math.max(pick, notPick);

            prev2 = prev;
            prev = curr;
        }

        return prev;
    }

    // TC : O(n)
    // SC : O(n)
    // Tabulation - bottom-up approach
    public int rob1(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int n = nums.length;
        for(int i=1; i < n; i++) {
            int pick = nums[i];

            if(i > 1){
                pick += dp[i - 2];
            }

            int notPick = dp[i - 1];

            dp[i] = Math.max(pick, notPick);
        }

        return dp[n -1];
    }

    // Memoization - Top-down approach
    public int rob2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[nums.length];
        Arrays.fill(nums, -1);
        return helper(nums, dp,n -1);
    }

    private int helper(int[] nums, int[] dp, int index){
        if(index == 0) return nums[index];
        if(index < 0) return 0;

        if(dp[index] != -1) return dp[index];

        int pick = nums[index] + helper(nums, dp,index - 2);
        int nonPick = helper(nums, dp,index - 1);

        dp[index] = Math.max(pick, nonPick);
        return dp[index];
    }
}
