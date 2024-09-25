package org.educative.dp.string.transform;

public class Leetcode72 {
    public int minDistance(String word1, String word2) {
        // return recursion(word1.length() - 1, word2.length() - 1, word1, word2);
//        int[][] memo = new int[word1.length()][word2.length()];
//        for(int[] sub : memo) {
//            Arrays.fill(sub, -1);
//        }
//
//        return memoization(word1.length() - 1, word2.length() - 1, word1, word2, memo);
        return dynamic(word1, word2);
    }

    private int dynamic(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        int rows = word1.length(), columns = word2.length();

        int[][] dp = new int[rows + 1][columns + 1];

        // 1 based indexing
        // if(index1 < 0) condition
        for(int i=0; i <= columns; i++) {
            dp[0][i] = i + 1;
        }

        // if(index2 < 0) condition
        for(int i=0; i <= rows; i++) {
            dp[i][0] = i + 1;
        }

        for(int i=1; i <= rows; i++) {
            for(int j=1; j <= columns; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i][j - 1],
                            Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                }
            }
        }

        return dp[rows][columns];
    }

    private int memoization(int index1, int index2, String word1, String word2, int[][] memo) {
        // Base cases
        // Case #1 - index1 is exhausted - insert all the remaining characters of word2
        if(index1 < 0) return index2 + 1; // remaining characters of word2

        // Case #2 - index2 is exhausted - delete all the remaining characters of word1
        if(index2 < 0) return index1 + 1;

        if(memo[index1][index2] != -1) return memo[index1][index2];

        // If the characters in both the strings match
        if(word1.charAt(index1) == word2.charAt(index2)) {
            memo[index1][index2] = memoization(index1 - 1, index2 - 1, word1, word2, memo);
            return memo[index1][index2];
        }

        // Else, we can do either of these operations - Insert, Delete, Replace
        // Insert
        int insert = 1 + memoization(index1, index2 - 1, word1, word2, memo);

        // Delete
        int delete = 1 + memoization(index1 - 1, index2, word1, word2, memo);

        // Replace
        int replace = 1 + memoization(index1 - 1, index2 - 1, word1, word2, memo);

        memo[index1][index2] = Math.min(insert, Math.min(delete, replace));
        return memo[index1][index2];
    }

    private int recursion(int index1, int index2, String word1, String word2) {

        // Base cases
        // Case #1 - index1 is exhausted - insert all the remaining characters of word2
        if(index1 < 0) return index2 + 1; // remaining characters of word2

        // Case #2 - index2 is exhausted - delete all the remaining characters of word1
        if(index2 < 0) return index1 + 1;

        // If the characters in both the strings match
        if(word1.charAt(index1) == word2.charAt(index2))
            return recursion(index1 - 1, index2 - 1, word1, word2);

        // Else, we can do either of these operations - Insert, Delete, Replace
        // Insert
        int insert = 1 + recursion(index1, index2 - 1, word1, word2);

        // Delete
        int delete = 1 + recursion(index1 - 1, index2, word1, word2);

        // Replace
        int replace = 1 + recursion(index1 - 1, index2 - 1, word1, word2);

        return Math.min(insert, Math.min(delete, replace));
    }
}
