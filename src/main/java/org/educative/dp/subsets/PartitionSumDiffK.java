package org.educative.dp.subsets;

import java.util.Arrays;

public class PartitionSumDiffK {

    /**
     * Link :
     * <a href="https://www.naukri.com/code360/problems/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum_842494?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos">...</a>
     *     The first half is exactly the same as previous problems in this category
     *     The second half reuses the dp array we calculated.
     *     The last row tells if we are standing at the last index, can we achieve the kth index as a target or not.
     *     Use this kth target sum as a first subarray sum.
     *     Total sum - this sum would be the second subarray sum.
     *
     */
    public static int minSubsetSumDifference(int []arr, int n) {
        int totalSum = Arrays.stream(arr).sum();

        /* The first half starts here */
        boolean[][] dp = new boolean[n][totalSum + 1]; // Target sum from 0 to k

        // Convert the recursion base cases - (target == 0) condition
        for(int row=0; row < n; row++){
            dp[row][0] = true;
        }

        // We can achieve the expected sum only at a[0]th index
        // Means if we are at 0th index and the target sum would be possible for a[0]th
        // if(index == 0) return arr[index] == target; condition
        if (arr[0] <= totalSum) {
            dp[0][arr[0]] = true;
        }

        // Now populate the rest of the matrix
        for(int index=1; index < n; index++) {
            for(int target = 1; target <= totalSum; target++) {
                boolean notTake = dp[index - 1][target];
                boolean take = false;
                if(target >= arr[index]) {
                    take = dp[index - 1][target - arr[index]];
                }

                dp[index][target] = notTake || take;
            }
        }

        // The last row signify that if we passed through all the elements of of a given array
        // is there a subset having a specific sum.This sum is nothing but the column number

        /* The second half starts here */
        int min = Integer.MAX_VALUE;
        // After the mid, the same pattern will occur.
        // Assume, if up to mid, s1 = 2, s2 = 7. After mid we will encounter s1 = 7, s2 = 2
        for(int s1=0; s1 <= totalSum / 2; s1++){
            if(dp[n-1][s1]){
                int s2 = totalSum - s1;
                min = Math.min(min,  s2 - s1);
            }
        }

        return min;
    }
}
