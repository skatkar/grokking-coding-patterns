package org.educative.dp.subsets;

import java.util.Arrays;

public class Leetcode416 {

    /**
     * Link : <a href="https://www.naukri.com/code360/problems/partition-equal-subset-sum_892980?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos">...</a>
     * @param arr
     * @param n
     * @return
     */
    public static boolean canPartition(int[] arr, int n) {
        int totalSum = Arrays.stream(arr).sum();
        // If the sum is odd then it is not possible to find two subsets having the same sum.
        return totalSum % 2 == 0 && subsetSumToK(n, totalSum / 2, arr);
    }

    // DP
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
        if (arr[0] == k) {
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

    private boolean memoization(int index, int target, int[] nums, boolean[][] memo) {
        if(target == 0) return true;
        if(index == 0)
            return nums[index] == target;

        if(memo[index][target]) return true;

        boolean pick = false;
        if(nums[index] <= target){
            pick = recursion(index - 1, target - nums[index], nums);
        }

        boolean notPick = recursion(index - 1, target, nums);
        memo[index][target] = pick || notPick;
        return memo[index][target];
    }

    private boolean recursion(int index, int target, int[] nums) {
        if(target == 0) return true;
        if(index == 0)
            return nums[index] == target;

        boolean pick = false;
        if(nums[index] <= target){
            pick = recursion(index - 1, target - nums[index], nums);
        }

        boolean notPick = recursion(index - 1, target, nums);

        return pick || notPick;
    }
}
