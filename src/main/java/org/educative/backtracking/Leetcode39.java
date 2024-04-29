package org.educative.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Leetcode39 {
    public static void main(String[] args) {
        Leetcode39 q = new Leetcode39();
        List<List<Integer>> lists = q.combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println("lists = " + lists);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> subsets = new ArrayList<>();
        generateCombinations(0, candidates, new ArrayList<Integer>(), subsets, target);
        return subsets;
    }

    private void generateCombinations(int index, int[] candidates, List<Integer> subset, List<List<Integer>> subsets, int target) {
        if(index == candidates.length){
            if(target == 0){
                subsets.add(new ArrayList<>(subset));
            }
            return;
        }

        // If the number is greater than the target, then we don't have to start the recursion
        // otherwise it will go in an infinite loop.

        // since we are allowed to choose the number as many times as possible,
        // we need some terminating condition else infinite loop.
        if(candidates[index] <= target){
            subset.add(candidates[index]);
            target -= candidates[index];
            generateCombinations(index, candidates, subset, subsets, target);

            subset.remove(subset.size() - 1);
            target += candidates[index];
        }

        generateCombinations(index + 1, candidates, subset, subsets, target);
    }
}
