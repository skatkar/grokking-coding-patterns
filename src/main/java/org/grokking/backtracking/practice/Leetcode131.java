package org.grokking.backtracking.practice;

import java.util.ArrayList;
import java.util.List;

public class Leetcode131 {
    public static void main(String[] args) {
        Leetcode131 q = new Leetcode131();
        List<List<String>> partitioned = q.partition("aab");
        System.out.println("partitioned = " + partitioned);
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        partitions(0, s, new ArrayList<>(), result);
        return result;
    }

    private void partitions(int index, String s, List<String> substr, List<List<String>> result) {
        if(index == s.length()){
            result.add(new ArrayList<>(substr));
            return;
        }

        for(int i=index; i < s.length(); i++) {
            if(isPalindrome(s, index, i)){
                substr.add(s.substring(index, i + 1));
                partitions(i + 1, s, substr, result);
                substr.remove(substr.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str, int low, int high) {
        while(low <= high) {
            if(str.charAt(low++) != str.charAt(high--)){
                return false;
            }
        }
        return true;
    }
}
