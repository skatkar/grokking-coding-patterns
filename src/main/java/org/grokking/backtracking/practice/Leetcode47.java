package org.grokking.backtracking.practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode47 {
    // TC : O(n! * n)
    // - Total number of permutations for an array of size = n!
    // This would be a worst case only if all the elements are unique else T.C. will be lower than this.
    // SC : O(n) + O(n) - permutations arraylist and visited set

    public static void main(String[] args) {
        Leetcode47 q = new Leetcode47();
        List<List<Integer>> lists = q.permuteUnique(new int[]{1, 1, 2});
        System.out.println("lists = " + lists);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();

        generatePermutations(nums, new HashSet<>(), new ArrayList<>(), result);

        return new ArrayList<>(result);
    }

    private void generatePermutations(int[] nums, Set<Integer> visited, List<Integer> permutation, Set<List<Integer>> result) {
        if(permutation.size() == nums.length){
            result.add(new ArrayList<>(permutation));
            return;
        }

        for(int i=0; i < nums.length; i++) {
            if(!visited.contains(i)){
                permutation.add(nums[i]);
                visited.add(i);

                generatePermutations(nums, visited, permutation, result);

                permutation.remove(permutation.size() - 1);
                visited.remove(i);
            }
        }
    }
}
