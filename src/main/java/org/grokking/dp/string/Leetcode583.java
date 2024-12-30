package org.grokking.dp.string;

public class Leetcode583 {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int lcs = longestCommonSubsequence(word1, word2);

        return len1 + len2 - 2 * lcs;
    }

    // Leetcode 1143
    public int longestCommonSubsequence(String text1, String text2) {
        // return recursion(text1.length() - 1, text2.length() - 1, text1, text2);

        /*int m = text1.length();
        int n = text2.length();
        int[][] memo = new int[m][n];
        for(int[] sub : memo) {
            Arrays.fill(sub, -1);
        }
        return memoization(m - 1, n - 1, text1, text2, memo);*/

        return dynamic(text1, text2);
    }

    private int dynamic(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        // In DP approach, we can't have -1 index. So, lets assume 0th row and 0th column as 0
        // This is just like these for loops. We don't have to write it as the 2D matrix is initialized with 0
        // for(int i=0; i <= n; i++) dp[0][i] = 0;
        // for(int i=0; i <= n; i++) dp[i][0] = 0;

        for(int index1=1; index1 <= m; index1++) {
            for(int index2=1; index2 <= n; index2++) {
                if(text1.charAt(index1 - 1) == text2.charAt(index2 - 1)){
                    dp[index1][index2] = 1 + dp[index1 - 1][index2 - 1];
                }else{
                    dp[index1][index2] = Math.max(dp[index1 - 1][index2], dp[index1][index2 - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
