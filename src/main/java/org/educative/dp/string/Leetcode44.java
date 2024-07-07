package org.educative.dp.string;

public class Leetcode44 {
    private boolean isAllStars(String S1, int i) {
        for (int j = 1; j <= i; j++) {
            if (S1.charAt(j - 1) != '*')
                return false;
        }
        return true;
    }

    public boolean isMatch(String s, String p) {
        // return recursion(s.length() - 1, p.length() - 1, s,p);
        /*
        int sLength = s.length();
        int pLength = p.length();
        int[][] memo = new int[sLength][pLength];
        for(int[] sub : memo) {
            Arrays.fill(sub, -1);
        }

        int result  = memoization(sLength - 1, pLength - 1, s, p , memo);
        return result == 1;
        */
        return dynamic(p, s);
    }

    private boolean dynamic(String S1, String S2) {
        int n = S1.length();
        int m = S2.length();

        // Create a 2D array to store the matching results
        boolean dp[][] = new boolean[n + 1][m + 1];
        dp[0][0] = true;

        // Initialize the first row and column based on wildcard '*' in S1
        for (int j = 1; j <= m; j++) {
            dp[0][j] = false;
        }
        for (int i = 1; i <= n; i++) {
            dp[i][0] = isAllStars(S1, i);
        }

        // Fill the dp array using a bottom-up approach
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (S1.charAt(i - 1) == S2.charAt(j - 1) || S1.charAt(i - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1]; // Characters match or '?' is encountered.
                } else {
                    if (S1.charAt(i - 1) == '*') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1]; // '*' matches one or more characters.
                    } else {
                        dp[i][j] = false; // Characters don't match, and S1[i-1] is not '*'.
                    }
                }
            }
        }

        return dp[n][m]; // The final result indicates whether S1 matches S2.
    }

    private int memoization(int sIndex, int pIndex, String s, String p, int[][] memo) {
        // Base condition
        if(sIndex < 0 && pIndex < 0) return 1;

        // The pattern string is exhausted, but there are certain characters left in the s string
        if(pIndex < 0) return 0;

        // The s string exhausted but the pattern string has still a few characters
        // We'll return true only if all the pending characters of the p string are *
        // Because * can match with 0 occurrences of any characters
        if(sIndex < 0){
            for(int i=0; i <= pIndex; i++)
                if(p.charAt(pIndex) != '*') return 0;
            return 1;
        }

        if(memo[sIndex][pIndex] != -1) return memo[sIndex][pIndex];

        // If the characters match or the current character in the pattern is '?'
        if(s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '?'){
            memo[sIndex][pIndex] = memoization(sIndex - 1, pIndex - 1, s, p, memo);
            return memo[sIndex][pIndex];
        }

        if(p.charAt(pIndex) == '*'){
            memo[sIndex][pIndex] = (memoization(sIndex - 1, pIndex, s, p, memo) == 1 ||
                    memoization(sIndex, pIndex - 1, s, p, memo) == 1) ? 1 : 0;
            return memo[sIndex][pIndex];
        }

        return 0;
    }

    private boolean recursion(int sIndex, int pIndex, String s, String p){
        // Base condition
        if(sIndex < 0 && pIndex < 0) return true;

        // The pattern string is exhausted, but there are certain characters left in the s string
        if(pIndex < 0) return false;

        // The s string exhausted but the pattern string has still a few characters
        // We'll return true only if all the pending characters of the p string are *
        // Because * can match with 0 occurrences of any characters
        if(sIndex < 0){
            for(int i=0; i <= pIndex; i++)
                if(p.charAt(pIndex) != '*') return false;
            return true;
        }

        // If the characters match or the current character in the pattern is '?'
        if(s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '?'){
            return recursion(sIndex - 1, pIndex - 1, s, p);
        }

        if(p.charAt(pIndex) == '*'){
            return recursion(sIndex - 1, pIndex, s, p) ||
                    recursion(sIndex, pIndex - 1, s, p);
        }

        return false;
    }
}
