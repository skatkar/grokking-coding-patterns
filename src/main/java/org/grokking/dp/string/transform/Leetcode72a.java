package org.grokking.dp.string.transform;

import java.util.ArrayList;
import java.util.List;

public class Leetcode72a {

    /**
     * <a href="https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/minimum-deletions-insertions-to-transform-a-string-into-another">...</a>
     * @param s1
     * @param s2
     * @return
     */
    public List<Integer> findMDI(String s1, String s2) {
        List<Integer> result = new ArrayList<>();
        int commonSubSeqLen = dynamic(s1, s2);
        int deletions = s1.length() - commonSubSeqLen;
        int insertions = s2.length() - commonSubSeqLen;
        result.add(deletions);
        result.add(insertions);
        return result;
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
