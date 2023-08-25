package org.educative.subsets;

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
}
