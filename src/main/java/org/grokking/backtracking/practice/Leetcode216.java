package org.grokking.backtracking.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Leetcode216 {
    public static void main(String[] args) {
        Leetcode216 q = new Leetcode216();
        List<List<Integer>> combinationSum3 = q.combinationSum3(4, 1);
        System.out.println("combinationSum3 = " + combinationSum3);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        int[] input = IntStream.range(1, 10).toArray();

        generateCombinations(0, 0, n, k, input, result, new ArrayList<>());

        return result;
    }

    private void generateCombinations(int index, int sum, int n, int k,
                                      int[] input, List<List<Integer>> result, List<Integer> subset) {
        if(index == input.length){
            if(subset.size() == k && sum == n){
                result.add(new ArrayList<>(subset));
            }
            return;
        }

        subset.add(input[index]);
        sum += input[index];
        generateCombinations(index + 1, sum, n, k, input, result, subset);

        subset.remove(subset.size() - 1);
        sum -=input[index];
        generateCombinations(index + 1, sum, n, k, input, result, subset);
    }
}
