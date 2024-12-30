package org.grokking.dp.subsets;

import java.util.Arrays;

public class SubsetSumK {

    public static boolean subsetSumToK(int n, int k, int[] arr){
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

    /**
     * Link- <a href="https://www.naukri.com/code360/problems/subset-sum-equal-to-k_1550954?leftPanelTab=1%3Fsource%3Dyoutube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos">...</a>
     * @param n
     * @param k
     * @param arr
     * @return boolean
     */
    public static boolean subsetSumToK2(int n, int k, int[] arr){
        // We have to reach till n, so the array must be n + 1
        // Similarly for columns size - k + 1
        boolean[][] memo = new boolean[n + 1][k + 1];
        for(boolean[] sub : memo) {
            Arrays.fill(sub, false);
        }
        return memoization(n - 1, k, memo, arr);

        //return recursion(n - 1, k, arr);
    }

    private static boolean memoization(int index, int target, boolean[][] memo, int[] arr) {
        if(target == 0) return true;
        if(index == 0) return arr[index] == target;

        if(memo[index][target]) return true;

        boolean notTake = memoization(index - 1, target, memo, arr);
        boolean take = false;

        // If the current number is already greater than target then skip it.
        if(target >= arr[index]){
            take = memoization(index - 1, target - arr[index], memo, arr);
        }

        memo[index][target] = take || notTake;

        return memo[index][target];
    }

    private static boolean recursion(int index, int target, int[] arr) {
        // If the target is 0 then either we found the expected subset or there is no sense in proceeding
        if(target == 0) return true;
        if(index == 0) return arr[index] == target;

        boolean notTake = recursion(index - 1, target, arr);
        boolean take = false;

        // If the current number is already greater than target then skip it.
        if(target >= arr[index]){
            take = recursion(index - 1, target - arr[index], arr);
        }

        return notTake || take;
    }
}
