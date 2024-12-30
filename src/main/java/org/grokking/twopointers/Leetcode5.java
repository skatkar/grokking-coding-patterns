package org.grokking.twopointers;

public class Leetcode5 {
    int low, maxLen;

    public static void main(String[] args) {
        Leetcode5 q = new Leetcode5();
        String answer = q.longestPalindrome("aba");
        System.out.println("answer = " + answer);
    }

    public String longestPalindrome(String s) {
        int length = s.length();
        if(length < 2)
            return s;

        for(int i=0; i < length - 1; i++) {
            extendPalindrome(s, i, i); // odd length palindromes
            extendPalindrome(s, i, i + 1); // even length palindrome
        }

        return s.substring(low, low + maxLen);
    }

    private void extendPalindrome(String s, int left, int right) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // consider string aba. As soon as left points at -1 & right points at 3, above condition breaks
        if(maxLen < right - left - 1) {
            low = left + 1; // because at left char the above condition violated. So, we should look at left + 1
            maxLen = right - left - 1;
        }
    }
}
