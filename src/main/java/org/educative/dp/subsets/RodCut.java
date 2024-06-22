package org.educative.dp.subsets;

import java.util.Arrays;

public class RodCut {
    /**
     * Link: <a href="https://www.naukri.com/code360/problems/rod-cutting-problem_800284">...</a>
     * Description - Each sub-array size represents the rod length
     * 0th index - rod length 1, 1st index - rod length 2, etc.
     * @param price
     * @param n
     * @return
     */
    public static int cutRod(int price[], int n) {
        int[][] memo = new int[n][n+1];
        for(int[] subArray : memo) {
            Arrays.fill(subArray, -1);
        }
        //return recursion(n - 1, n, price);
        return memoization(memo, n - 1, n, price);
    }

    private static int memoization(int[][] memo, int index, int N, int[] price) {
        if(index == 0){
            return N * price[0];
        }

        if(memo[index][N] != -1) return memo[index][N];

        int notTake = memoization(memo,index - 1, N, price);

        int take = Integer.MIN_VALUE;
        int rodLength = index + 1;
        if(rodLength <= N){
            take = price[index] + memoization(memo, index, N - rodLength, price);
        }
        memo[index][N] = Math.max(take, notTake);
        return memo[index][N];
    }

    // Each index value represents the rod length
    private static int recursion(int index, int N, int[] price) {
        if(index == 0){
            // (remaining length / current rod length) * price[0]
            // At 0th index, rod length is 1
            return N * price[0];
        }

        int notTake = recursion(index - 1, N, price);

        int take = Integer.MIN_VALUE;
        int rodLength = index + 1;
        if(rodLength <= N){
            take = price[index] + recursion(index, N - rodLength, price);
        }

        return Math.max(take, notTake);
    }
}
