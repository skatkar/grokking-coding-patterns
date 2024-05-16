package org.educative.dp.subsets;

import java.util.Arrays;

public class PartitionSubset {
    public static boolean canPartition(int[] arr, int n) {
        int totalSum = Arrays.stream(arr).sum();
        // If the sum is odd then it is not possible to find two subsets having the same sum.
        return totalSum % 2 == 0 && subsetSumToK(n, totalSum / 2, arr);
    }

    // Exact copy of SubsetSumK.java
    private static boolean subsetSumToK(int n, int k, int[] arr){
        boolean[][] dp = new boolean[n][k + 1]; // Target sum from 0 to k

        // Convert the recursion base cases - (target == 0) condition
        for(int row=0; row < n; row++){
            dp[row][0] = true;
        }

        // We can achieve the expected sum only at a[0]th index
        // Means if we are at 0th index and the target sum would be possible for a[0]th
        // if(index == 0) return arr[index] == target; condition
        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }

        // Now populate the rest of the matrix
        for(int index=1; index < n; index++) {
            for(int target=1; target <= k; target++) {
                boolean notTake = dp[index - 1][target];
                boolean take = false;
                if(target >= arr[index]) {
                    take = dp[index - 1][target - arr[index]];
                }

                dp[index][target] = notTake || take;
            }
        }

        return dp[n - 1][k];
    }
}
