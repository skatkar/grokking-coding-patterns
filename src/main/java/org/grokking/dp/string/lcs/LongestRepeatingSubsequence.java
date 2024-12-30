package org.grokking.dp.string.lcs;

public class LongestRepeatingSubsequence {

    /**
     * <a href="https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/longest-repeating-subsequence">...</a>
     * @param str
     * @return
     */
    public int findLRSLength(String str) {
        int length = str.length();
        return recursion(length - 1, length - 1, str);
    }

    // This is similar to Longest Common Subsequence question
    // The only difference is that we've only one string
    // and check the if block below
    private int recursion(int index1, int index2, String text) {
        if(index1 < 0 || index2 < 0) return 0;

        // The first condition will ensure that we are not comparing the same character
        // Even though the two characters are same, they should at the different indexes
        if(index1 != index2 && text.charAt(index1) == text.charAt(index2))
            return 1 + recursion(index1 - 1, index2 - 1, text);

        int left = recursion(index1 - 1, index2, text);
        int right = recursion(index1, index2 - 1, text);

        return Math.max(left, right);
    }
}
