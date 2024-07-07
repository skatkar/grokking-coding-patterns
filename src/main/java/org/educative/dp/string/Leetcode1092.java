package org.educative.dp.string;

public class Leetcode1092 {
    public String shortestCommonSupersequence(String str1, String str2) {
        StringBuilder result = new StringBuilder();
        int[][] dp = generateDynamicArray(str1,str2);
        int i= str1.length(), j = str2.length();

        // Decoding the DP array
        while(i > 0 && j > 0){
            // If the characters are current DP index match, then go diagonally
            if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                result.append(str1.charAt(i -1));
                i--;
                j--;
            }else if(dp[i - 1][j] > dp[i][j - 1]) { // Else if, go to the above row
                result.append(str1.charAt(i -1));
                i--;
            }else{
                result.append(str2.charAt(j -1)); // Else, go to the left column
                j--;
            }
        }

        while(i > 0) {
            result.append(str1.charAt(i -1));
            i--;
        }

        while(j > 0){
            result.append(str2.charAt(j -1));
            j--;
        }

        return result.reverse().toString();
    }

    // Leetcode 1143
    public int[][] generateDynamicArray(String str1, String str2) {
        int rows = str1.length(), columns = str2.length();
        int[][] dp = new int[rows + 1][columns + 1];
        for(int i=1; i <= rows; i++) {
            for(int j=1; j <= columns; j++) {
                if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j - 1]);
                }
            }
        }

        return dp;
    }
}
