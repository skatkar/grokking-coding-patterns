package org.educative.dp.lis;

import java.util.Arrays;

public class Leetcode673 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        // To keep track of number of subsequences ending at the current position
        int[] count = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        // Leetcode 300 with the extra else condition
        int maxi = 1;
        for(int i=0; i < n; i++) {
            for(int prev = 0; prev < i; prev++) {
                if(nums[i] > nums[prev] && 1 + dp[prev] > dp[i]){
                    dp[i] = 1 + dp[prev];

                    // Copy the count value as it is
                    count[i] = count[prev];
                }else if(nums[i] > nums[prev] && 1 + dp[prev] == dp[i]) {
                    count[i] += count[prev];
                }
            }

            maxi = Math.max(maxi, dp[i]);
        }

        int total = 0;
        for(int i=0; i < n; i++) {
            if(dp[i] == maxi)
                total += count[i];
        }

        return total;
    }
}
