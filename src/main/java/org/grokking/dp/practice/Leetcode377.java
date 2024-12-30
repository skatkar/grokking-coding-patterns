package org.grokking.dp.practice;

public class Leetcode377 {
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        return recursion(n - 1, nums, target);
    }

    private int recursion(int index, int[] nums, int target) {
        // Base condition
        if(index == 0){
            if(target % nums[0] == 0)
                return 1;
            else
                return 0;
        }

        // not pick condition
        int notPick = recursion(index - 1, nums, target);

        // pick condition
        int pick = 0;
        if(nums[index] <= target)
            pick = recursion(index, nums, target - nums[index]);

        return pick + notPick;
    }
}
