package org.educative.dp.oned;

import java.util.*;
import java.util.stream.Collectors;

public class Leetcode740 {

    public static void main(String[] args) {


    }

    // This is a slight modification to a house robber problem.
    // The below code runs for 49 test cases out of 51.
    // TC : O(n)
    // SC : O(n)
    public int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();

        for(int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        Set<Integer> sorted = new HashSet<>(Arrays.stream(nums)
                .sorted()
                .boxed()
                .collect(Collectors.toList()));

        nums = new ArrayList<>(sorted).stream().mapToInt(i -> i).toArray();

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0] * count.get(nums[0]);

        for(int i=1; i < n; i++) {
            int currEarn = nums[i] * count.get(nums[i]);

            if(nums[i] == nums[i - 1] + 1){
                int pick = currEarn;

                if(i > 1){
                    pick += dp[i - 2];
                }

                int notPick = dp[i - 1];
                dp[i] = Math.max(pick, notPick);
            }else{
                dp[i] = currEarn + dp[i - 1];
            }
        }

        return dp[n - 1];
    }
}
