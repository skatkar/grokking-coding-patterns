package org.grokking.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Leetcode784 {
    public static void main(String[] args) {
        Leetcode784 question = new Leetcode784();
        List<String> permutations = question.letterCasePermutation("a1b2");
        System.out.println("permutations = " + permutations);
    }

    public List<String> letterCasePermutation(String s) {
        List<String> permutations = new ArrayList<>();
        permutations.add(s);

        for(int i=0; i < s.length(); i++) {
            if(Character.isLetter(s.charAt(i))){
                int n = permutations.size();

                // for each permutation, change the character at the ith index
                for(int j=0; j < n; j++){
                    char[] charArray = permutations.get(j).toCharArray();

                    // if it is already an upper-case char, make it lower-case
                    if(Character.isUpperCase(charArray[i])){
                        charArray[i] = Character.toLowerCase(charArray[i]);
                    }else{
                        charArray[i] = Character.toUpperCase(charArray[i]);
                    }
                    permutations.add(String.valueOf(charArray));
                }
            }
        }

        return  permutations;
    }
}
