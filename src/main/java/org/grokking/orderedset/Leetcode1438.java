package org.grokking.orderedset;

import java.util.TreeSet;

public class Leetcode1438 {
    public int longestSubarray(int[] nums, int limit) {
        // Create a treeSet of the indexes. If they are same then sort based on the number they are pointing to.
        TreeSet<Integer> treeSet = new TreeSet<>(
                (a,b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]
        );
        treeSet.add(0);

        int left = 0, res = 1;
        for(int right=1; right < nums.length;right++) {
            treeSet.add(right);
            while(nums[treeSet.last()] - nums[treeSet.first()] > limit) {
                treeSet.remove(left);
                left++;
            }

            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
