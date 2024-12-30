package org.grokking.dp.string;

import java.util.Arrays;

public class Leetcode115 {
    public int numDistinct(String s, String t) {
        // return recursion(s.length() - 1, t.length() - 1, s, t);
        int sLength = s.length();
        int tLength = t.length();
        int[][] memo = new int[sLength][tLength];
        for(int[] sub : memo){
            Arrays.fill(sub, -1);
        }

        return memoization(sLength - 1, tLength - 1, s, t, memo);
    }

    private int memoization(int sIndex, int tIndex, String s, String t, int[][] memo) {
        if(tIndex < 0) return 1;
        if(sIndex < 0) return 0;

        if(memo[sIndex][tIndex] != -1) return memo[sIndex][tIndex];


        // s = "babgbag", t = "bag"
        // if we are at the last 'g', then either we can move to the next character in both the strings
        // or move to the next character in only s string
        // Meaning of the second case - we want to find the other occurrence of the matching character
        if(s.charAt(sIndex) == t.charAt(tIndex)){
            memo[sIndex][tIndex] = memoization(sIndex - 1, tIndex - 1, s,t, memo) +
                    memoization(sIndex - 1, tIndex, s,t, memo);
        }else {
            memo[sIndex][tIndex] = memoization(sIndex - 1, tIndex, s,t, memo);
        }

        return memo[sIndex][tIndex];
    }

    private int recursion(int sIndex, int tIndex, String s, String t){
        if(tIndex < 0) return 1;
        if(sIndex < 0) return 0;

        if(s.charAt(sIndex) == t.charAt(tIndex))
            return recursion(sIndex - 1, tIndex - 1, s,t) +
                    recursion(sIndex - 1, tIndex, s,t);

        return recursion(sIndex - 1, tIndex, s,t);
    }
}
