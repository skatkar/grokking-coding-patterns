package org.educative.dp.lis;

import java.util.Arrays;
import java.util.Comparator;

public class Leetcode1048 {
    public int longestStrChain(String[] words) {
        // If the input is like this - [xb, ......, xbc].
        // As per the question, the difference between wordA and wordB is one character.
        // If we sort based on the length then
        // the input array will automatically consist of strings
        // - having a difference of 1 length (if they are of different length)
        // - having a difference of 0 length (if they are of the same length)
        Arrays.sort(words, Comparator.comparingInt(String::length));
        return dynamic(words);
    }

    private int dynamic(String[] words) {
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxi = 1;
        for(int i=0; i < n; i++) {
            for(int prev=0; prev < i; prev++) {
                if(compare(words[i], words[prev]) && dp[prev] + 1 > dp[i]) {
                    dp[i] = 1 + dp[prev];
                }
            }

            maxi = Math.max(maxi, dp[i]);
        }

        return maxi;
    }

    private boolean compare(String firstStr, String secondStr) {
        // firstStr is always greater than secondStr as per the question
        int firstLen = firstStr.length();
        int secondLen = secondStr.length();

        if(firstStr.length() - secondStr.length() != 1)
            return false;

        int first = 0 , second = 0;
        while(first != firstLen) {
            if(firstStr.charAt(first) == secondStr.charAt(second)){
                first++;
                second++;
            }else {
                first++;
            }
        }
        return first == second;
    }
}
