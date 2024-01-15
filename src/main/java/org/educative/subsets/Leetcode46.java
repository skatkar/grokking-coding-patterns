package org.educative.subsets;

import java.util.*;

public class Leetcode46 {
    public static void main(String[] args) {
        Leetcode46 question = new Leetcode46();
        List<List<Integer>> permutations = question.findPermutations(new int[]{1,2,3});
        System.out.println("permutations = " + permutations);

        List<List<Character>> possibleCharSequences = question.findPossibleCharSequences("GOD");
        System.out.println("possibleCharSequences = " + possibleCharSequences);
    }

    // TC :
    // We know that there are a total of N! permutations of a set with ‘N’ numbers. In the algorithm above, we are iterating through all of these permutations with the help of the two ‘for’ loops.
    // In each iteration, we go through all the current permutations to insert a new number in them on line 17 (line 23 for C++ solution).
    // To insert a number into a permutation of size ‘N’ will take O(N), which makes the overall time complexity of our algorithm O(N*N!).
    public List<List<Integer>> findPermutations2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int size = nums.length;

        // This will hold all the permutations in first come first served basis
        Queue<List<Integer>> permutations = new LinkedList<>();
        permutations.add(new ArrayList<>());
        for(int currentNumber : nums){
            int n = permutations.size();

            // for every permutation
            for(int i=0; i < n; i++){
                List<Integer> oldPermutation = permutations.poll();
                for(int j=0; j <= oldPermutation.size(); j++){
                    List<Integer> newPermutation = new ArrayList<>(oldPermutation);
                    newPermutation.add(j, currentNumber);

                    if(newPermutation.size() == size)
                        result.add(newPermutation);
                    else
                        permutations.add(newPermutation);
                }
            }
        }

        return result;
    }

    public List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateCombination(nums, new ArrayList<>(), result, new HashSet<>());
        return result;
    }

    private void generateCombination(int[] nums, List<Integer> subset, List<List<Integer>> subsets, Set<Integer> visited) {
        if(subset.size() == nums.length)
            subsets.add(new ArrayList<>(subset));

        for (int num : nums) {
            if (!visited.contains(num)) {
                subset.add(num);
                visited.add(num);
                generateCombination(nums, subset, subsets, visited);
                subset.remove(subset.size() - 1);
                visited.remove(num);
            }
        }
    }

    public List<List<Character>> findPossibleCharSequences(String str){
        List<List<Character>> result = new ArrayList<>();
        char[] charArray = str.toCharArray();
        int size = charArray.length;

        Queue<List<Character>> permutations = new LinkedList<>();
        permutations.add(new ArrayList<>());

        for(char currentChar : charArray){
            int n = permutations.size();

            for(int i=0; i < n; i++){
                List<Character> oldPermutation  = permutations.poll();
                for(int j=0; j <= oldPermutation.size(); j++){
                    List<Character> newPermutations = new ArrayList<>(oldPermutation);
                    newPermutations.add(j, currentChar);

                    if(newPermutations.size() == size)
                        result.add(newPermutations);
                    else
                        permutations.add(newPermutations);
                }
            }
        }

        return result;
    }
}
