package org.grokking.dp.string.lcs;

import java.util.Arrays;

public class SubsequencePatternMatching {
    /**
     * <a href="https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/subsequence-pattern-matching">...</a>
     * @param str
     * @param pat
     * @return
     */
    public int findSPMCount(String str, String pat) {
        // return recursion(str.length() - 1, pat.length() - 1, str, pat);
        int[][] memo = new int[str.length()][pat.length()];
        for(int[] sub : memo) {
            Arrays.fill(sub, -1);
        }
        return memoization(str.length() - 1, pat.length() - 1, str, pat, memo);
    }

    private int memoization(int strIndex, int patIndex, String str, String pat, int[][] memo) {
        if(patIndex < 0)
            return 1;
        if(strIndex < 0)
            return 0;

        if(memo[strIndex][patIndex] != -1) return memo[strIndex][patIndex];
        // Pick - Not pick scenario - we'll either pick the current character or not.
        int notPick = memoization(strIndex - 1, patIndex, str, pat, memo);

        int pick = 0;
        if(str.charAt(strIndex) == pat.charAt(patIndex)){
            pick = memoization(strIndex - 1, patIndex - 1, str, pat, memo);
        }

        return memo[strIndex][patIndex] = pick + notPick;
    }

    private int recursion(int strIndex, int patIndex, String str, String pat) {
        // Base condition
        if(patIndex < 0)
            return 1;
        if(strIndex < 0)
            return 0;

        // Pick - Not pick scenario - we'll either pick the current character or not.
        int notPick = recursion(strIndex - 1, patIndex, str, pat);

        int pick = 0;
        if(str.charAt(strIndex) == pat.charAt(patIndex)){
            pick = recursion(strIndex - 1, patIndex - 1, str, pat);
        }

        return pick + notPick;
    }
}
