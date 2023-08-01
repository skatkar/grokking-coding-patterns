package org.educative.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode90 {
    public static void main(String[] args) {
        Leetcode90 question = new Leetcode90();
        List<List<Integer>> subsets = question.findSubsets(new int[]{1, 2, 2});
        subsets.forEach(subset -> System.out.println("subset = " + subset));
    }

    // TC : O(n * 2^n)
    // SC : O(n * 2^n)
    public List<List<Integer>> findSubsets(int[] nums) {
        // sort the numbers to handle duplicates
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        int startIndex = 0, endIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            startIndex = 0;
            // if current and the previous elements are same, create new subsets only from the
            // subsets added in the previous step
            if (i > 0 && nums[i] == nums[i - 1])
                startIndex = endIndex + 1;
            endIndex = subsets.size() - 1;
            for (int j = startIndex; j <= endIndex; j++) {
                // create a new subset from the existing subset and add the current element to it
                List<Integer> set = new ArrayList<>(subsets.get(j));
                set.add(nums[i]);
                subsets.add(set);
            }
        }
        return subsets;
    }
}
