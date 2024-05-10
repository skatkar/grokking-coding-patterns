package org.educative.dp.oned;

import java.util.ArrayList;
import java.util.List;

public class Leetcode213 {
    public int rob(int[] nums) {

        int n = nums.length;
        if(n == 1) return nums[0];

        List<Integer> excludeLast = new ArrayList<>(n - 1);
        List<Integer> excludeFirst = new ArrayList<>(n - 1);

        for(int i=0; i < n; i++) {
            if(i != 0) excludeFirst.add(nums[i]);
            if(i != n - 1) excludeLast.add(nums[i]);
        }

        // First and last can't be considered at a time
        // Get the max from excluding the first or excluding the last
        return Math.max(
                helper(excludeFirst.stream().mapToInt(Integer::intValue).toArray()),
                helper(excludeLast.stream().mapToInt(Integer::intValue).toArray()));
    }


    // Same as Leetcode 198
    private int helper(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

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
}
