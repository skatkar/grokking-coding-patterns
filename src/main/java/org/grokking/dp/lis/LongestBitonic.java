package org.grokking.dp.lis;

import java.util.Arrays;

public class LongestBitonic {
    /**
     * <a href="https://www.naukri.com/code360/problems/longest-bitonic-sequence_1062688">...</a>
     * @param nums
     * @param n
     * @return
     */
    public static int longestBitonicSequence(int[] nums, int n) {
        int maxLength = -1;
        int length = nums.length;
        int[] dp1 = new int[length];
        int[] dp2 = new int[length];

        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);
        // Increasing DP array
        for(int i=0; i < length; i++) {
            for(int prev=0; prev < i; prev++) {
                if(nums[prev] < nums[i] && dp1[prev] + 1 > dp1[i]) {
                    dp1[i] = 1 + dp1[prev];
                }
            }
        }

        // Decreasing DP array
        for(int i=length - 1; i >= 0; i--) {
            for(int prev=length - 1; prev > i; prev--) {
                if(nums[prev] < nums[i] && dp2[prev] + 1 > dp2[i]) {
                    dp2[i] = 1 + dp2[prev];
                }
            }
        }

        for(int i=0; i < length; i++) {
            maxLength = Math.max(maxLength, dp1[i] + dp2[i] - 1);
        }

        return maxLength;
    }
}
