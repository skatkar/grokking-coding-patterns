package org.educative.backtracking.practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        generatePermutations(nums, new HashSet<>(), new ArrayList<Integer>(), result);
        return result;
    }

    private void generatePermutations(int[] nums, Set<Integer> visited, List<Integer> sublist, List<List<Integer>> result) {
        if(sublist.size() == nums.length){
            result.add(new ArrayList<>(sublist));
            return;
        }

        for(int num : nums) {
            if(!visited.contains(num)){
                sublist.add(num);
                visited.add(num);

                generatePermutations(nums, visited, sublist, result);

                sublist.remove(sublist.size() - 1);
                visited.remove(num);
            }
        }
    }


}
