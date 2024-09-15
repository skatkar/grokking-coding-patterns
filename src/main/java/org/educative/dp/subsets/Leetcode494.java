package org.educative.dp.subsets;

import java.util.Arrays;

public class Leetcode494 {
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

    public static void main(String[] args) {
        Leetcode494 q = new Leetcode494();
        int targetSumWays = q.findTargetSumWays(new int[]{1,1,1,1,1}, 3);
        System.out.println(targetSumWays);
    }

    // Here is the explanation - https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/solution-target-sum
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int totalSum = Arrays.stream(nums).sum();

        if(totalSum < target || (target + totalSum) % 2 != 0) return 0;

        int k = (target + totalSum) / 2;
        int[][] memo = new int[n][k + 1];
        for(int[] sub: memo) {
            Arrays.fill(sub, -1);
        }
        return memoization(n - 1, k, memo, nums);
    }
}
