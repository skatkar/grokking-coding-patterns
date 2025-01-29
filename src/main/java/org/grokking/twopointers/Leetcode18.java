package org.grokking.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, 0, 4, target);
    }

    // Make the target as long.
    // One of the test cases was failing for int data type
    private List<List<Integer>> kSum(int[] nums, int start, int k, long target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (k == 2) { // two pointers from left and right
            int left = start, right = len - 1;

            // This is exactly similar to 3-sum problem
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> path = new ArrayList<Integer>();
                    path.add(nums[left]);
                    path.add(nums[right]);
                    res.add(path);
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                } else if (sum < target) { // move left
                    left++;
                } else { // move right
                    right--;
                }
            }
        } else {
            // reduce it to 2-sum problem
            for (int i = start; i < len - (k - 1); i++) {
                if (i > start && nums[i] == nums[i - 1])
                    continue;
                List<List<Integer>> temp = kSum(nums, i + 1, k - 1, target - nums[i]);
                for (List<Integer> t : temp) {
                    t.add(0, nums[i]);
                }
                res.addAll(temp);
            }
        }
        return res;
    }
}
