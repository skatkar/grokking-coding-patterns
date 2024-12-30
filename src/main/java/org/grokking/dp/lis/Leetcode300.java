package org.grokking.dp.lis;

import java.util.Arrays;

public class Leetcode300 {
    public int lengthOfLIS_dp(int[] nums){
        // return recursion(0, -1, nums);
        int n = nums.length;
        int[][] memo = new int[n][n + 1];
        for(int[] sub : memo) {
            Arrays.fill(sub, -1);
        }

        return memoization(0, -1, nums, memo);
    }

    // Using binary search
    // Intuition -
    // Initialize the DP array with the first element.
    // If the next element is greater than the last element, add to the DP array
    // Else, find the next greater element than the current element and replace it there.
    // This method works because while replacing, we're replacing the existing element with a new one.
    // This approach tries to make the array in increasing order.
    public int lengthOfLIS(int[] nums){
        if(nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int len = 1;

        for(int i=1; i < n; i++) {
            if(nums[i] > dp[len-1]){
                dp[len] = nums[i];
                len++;
            }else {
                int searchIndex = binarySearch(dp, 0, len - 1, nums[i]);
                dp[searchIndex] = nums[i];
            }
        }

        return len;
    }

    private int binarySearch(int[] dp, int low, int high, int target) {
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(dp[mid] == target){
                return mid;
            }else if(target < dp[mid]) {
                high = mid - 1;
            }else
                low = mid + 1;
        }

        return low;
    }

    // This is the simplified DP approach than the other one.
    // At each index, we maintain the longest increasing subsequence so far
    // Also, we keep track of the max length of LIS so far, and that's the answer.
    private int dynamic2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxi = 1;
        for(int i=0; i < n;i++) {
            for(int prev=0; prev < i; prev++){
                if(nums[i] > nums[prev]){
                    dp[i] = Math.max(dp[i], 1 + dp[prev]);
                }
            }
            maxi = Math.max(maxi, dp[i]);
        }

        return maxi;
    }

    private int dynamic(int[] nums) {
        int n = nums.length;
        // rows as index & columns as prevIndex
        int[][] dp = new int[n + 1][n + 1];
        for(int index = n - 1; index >= 0; index--) {
            for(int prevIndex=index - 1; prevIndex >= -1; prevIndex--) {
                // Pick - Not pick scenario
                int len = dp[index + 1][prevIndex + 1]; // the second parameter is always +1 state

                // Pick condition
                if(prevIndex == -1 || nums[index] > nums[prevIndex]){
                    len = Math.max(len, 1 + dp[index + 1][index + 1]); // for prevIndex, we changed the co-ordinates
                }

                dp[index][prevIndex + 1] = len;
            }
        }

        // memoization started with -1 but here prevIndex is always +1. So, -1 + 1 = 0 for column
        return dp[0][0];
    }

    private int memoization(int index, int prevIndex, int[] nums, int[][] memo) {
        if(index == nums.length)
            return 0;

        // We're changing the coordinates of prev index
        if(memo[index][prevIndex + 1] != -1) return memo[index][prevIndex + 1];

        // Pick - Not pick scenario
        int len = memoization(index + 1, prevIndex, nums, memo);

        // Pick condition
        if(prevIndex == -1 || nums[index] > nums[prevIndex]){
            len = Math.max(len, 1 + memoization(index + 1, index, nums, memo));
        }

        return memo[index][prevIndex + 1] = len;
    }

    private int recursion(int index, int prevIndex, int[] nums) {
        if(index == nums.length)
            return 0;

        // Pick - Not pick scenario
        int notPick = recursion(index + 1, prevIndex, nums);

        int pick = 0;
        // Pick condition
        if(prevIndex == -1 || nums[index] > nums[prevIndex]){
            pick = 1 + recursion(index + 1, index, nums);
        }

        return Math.max(pick, notPick);
    }
}
