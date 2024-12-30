package org.grokking.dp.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrintLIS {
    public static void main(String[] args) {
        PrintLIS lis = new PrintLIS();
        List<Integer> dynamic = lis.dynamic(new int[]{5, 4, 11, 1, 16, 8});
        System.out.println(dynamic);
    }

    public List<Integer> printLIS(int[] nums){
        return dynamic(nums);
    }

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
                // The second condition is important. Consider this example
                // [ 5 4 1 11] - Till 11, the subsequences can be 5, 11; 4,11, 1,11
                // Second condition will trigger only if the previous subsequence is really contributing to a new subsequence ending at 11
                if(nums[i] > nums[prev] && dp[i] < 1 + dp[prev]){
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
