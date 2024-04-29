package org.educative.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(candidates);
        generateCombinations(0, candidates, new ArrayList<Integer>(), subsets, target);
        return new ArrayList<>(subsets);
    }

    private void generateCombinations(int index, int[] candidates, List<Integer> subset, List<List<Integer>> subsets, int target) {
        if(index == candidates.length){
            if(target == 0){
                subsets.add(new ArrayList<>(subset));
            }
            return;
        }

        for(int i = index; i < candidates.length; i++) {
            // Don't take it scenario - If the current number is same as previously assumed start number, skp it
            // Because it will lead to the same subsequence
            if(i > index && candidates[i] == candidates[i - 1]) continue;
            if(candidates[i] > target) break;

            // Take it scenario
            subset.add(candidates[i]);
            generateCombinations(index + 1, candidates, subset, subsets, target - candidates[i]);
            subset.remove(subset.size() - 1);
        }
    }
}
