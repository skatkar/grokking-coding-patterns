package org.educative.dp.subsets;

import java.util.Arrays;

public class CountSubsets {
    public static int findWays(int num[], int tar) {
        int n = num.length;
        int[][] memo = new int[n][tar + 1];
        for(int[] sub : memo){
            Arrays.fill(sub , -1);
        }
        //return recursion(n - 1, tar, num);
        return memoization(n - 1, tar, memo, num);
    }

    private static int memoization(int index, int target, int[][] memo, int[] num) {

        if(target == 0) return 1;
        if(index == 0) return num[index] == target ? 1 : 0;
        if(memo[index][target] != -1) return memo[index][target];

        int notPick = memoization(index - 1, target, memo, num);
        int pick = 0;
        if(target >= num[index]) {
            pick = memoization(index - 1, target - num[index], memo, num);
        }

        return memo[index][target] = pick + notPick;
    }

    private static int recursion(int index, int target, int[] num){
        if(target == 0) return 1;
        if(index == 0) return num[0] == target ? 1 : 0;

        int notPick = recursion(index - 1, target, num);
        int pick = 0;
        if(target >= num[index]) {
            pick = recursion(index - 1, target - num[index], num);
        }

        return pick + notPick;
    }
}
