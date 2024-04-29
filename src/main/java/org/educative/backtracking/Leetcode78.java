package org.educative.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Leetcode78 {

    // TC:
    //The outer loop runs nums.length times, so its time complexity is O(n), where n is the length of the input array nums.
    //The inner loop runs setsList.size() times in each iteration of the outer loop. Initially, setsList contains only one empty list, so the inner loop runs 1 time in the first iteration of the outer loop.
    // In the subsequent iterations, the size of setsList increases by a factor of 2 each time, resulting in a total of 1 + 2 + 4 + … + 2^(n-1) iterations of the inner loop. This sum is equal to 2^n - 1, so the time complexity of the inner loop is O(2^n).
    //The operations inside the inner loop have a constant time complexity, as they involve basic list operations like add and get, which have a time complexity of O(1).
    // SC:
    public static List<List<Integer>> findAllSubsets(int[] nums) {
        // Replace this placeholder return statement with your code

        List<List<Integer>> setsList = new ArrayList<>();
        setsList.add(new ArrayList<>()); // Initialize

        for (int num : nums) {
            int size = setsList.size();
            for (int j = 0; j < size; j++) {
                List<Integer> tempList = new ArrayList<>(setsList.get(j));
                tempList.add(num);
                setsList.add(tempList);
            }
        }

        return setsList;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        generateSubset(0, nums, new ArrayList<>(), subsets);
        return subsets;
    }

    private void generateSubset(int index, int[] nums, List<Integer> subset, List<List<Integer>> subsets) {
        if(index >= nums.length) {
            subsets.add(new ArrayList<>(subset));
            return;
        }
        // Take
        subset.add(nums[index]);
        generateSubset(index + 1, nums, subset, subsets);


        // Don't take it
        subset.remove(subset.size() - 1);
        generateSubset(index + 1, nums, subset, subsets);
    }

    public static void main(String[] args) {
        Leetcode78 q = new Leetcode78();
        List<List<Integer>> subsets = q.subsets(new int[]{1, 2, 3});
        System.out.println("subsets = " + subsets);
    }
}
