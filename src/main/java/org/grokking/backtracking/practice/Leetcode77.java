package org.grokking.backtracking.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Leetcode77 {
    public static void main(String[] args) {
        Leetcode77 q = new Leetcode77();
        List<List<Integer>> lists = q.combine(1, 1);
        System.out.println("lists = " + lists);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int[] input = IntStream.range(1, n + 1).toArray();
        generateCombinations(0, new ArrayList<>(), result, input,k);
        return result;
    }

    private void generateCombinations(int index, List<Integer> subset,
                                      List<List<Integer>> result, int[] input, int k) {
        if(index == input.length){
            if(subset.size() == k) {
                result.add(new ArrayList<>(subset));
            }
            return;
        }


        subset.add(input[index]);
        generateCombinations(index + 1, subset,
                result, input, k);

        subset.remove(subset.size() - 1);
        generateCombinations(index+ 1, subset, result, input, k);
    }
}
