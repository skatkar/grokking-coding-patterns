package org.educative.dp.practice;

import java.util.Arrays;

public class Leetcode279 {
    public int numSquares(int n) {
        // return recursion(n);
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return memoization(memo, n);
    }

    private int dynamic(int answer) {
        int[] dp = new int[answer + 1];

        for(int i=1; i <= answer; i++) {
            // Assume that the minimum number of combinations at the current location is that number itself
            // which we'll try to minimize it below
            dp[i] = i;
            for(int j=1; j * j <= i; j++) {
                int square = j * j;
                dp[i] = Math.min(dp[i], 1 + dp[i - square]);
            }
        }

        return dp[dp.length - 1];
    }

    private int memoization(int[] memo, int answer) {
        if(answer <= 0)
            return 0;

        if(memo[answer] != -1) return memo[answer];

        int min = answer;
        // Here, for any given number n,
        // the possible numbers up to that number are only till sqrt(n).
        // These numbers can possibly add up to the given number.
        // For example, if the given number is 12 then the possible numbers are 1,2,3 (up to sqrt(12))
        // Square of 4 will go beyond 12 and no possibility of making a sum up to 12.
        for(int i=1; i * i <= answer; i++) {
            int square = i * i;
            min = Math.min(min, 1 + recursion(answer - square));
        }

        memo[answer] = min;

        return memo[answer];
    }

    /**
     *
     * @param answer
     * @return The number of ways
     */
    private int recursion(int answer) {
        // This means we found one possible combination matching our criteria
        if(answer == 0){
            return 1;
        }

        int min = Integer.MAX_VALUE;
        // For any given number n, the possible square roots up to that number are only till sqrt(n)
        for(int i=1; i * i <= answer; i++) {
            int square = i * i;
            min = Math.min(min, 1 + recursion(answer - square));
        }

        return min;
    }

}
