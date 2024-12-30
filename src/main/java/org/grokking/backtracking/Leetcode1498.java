package org.grokking.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode1498 {
    public static void main(String[] args) {
        Leetcode1498 q = new Leetcode1498();
        int numSubseq = q.numSubseq(new int[]{2,3,3,4,6,7}, 12);
        System.out.println("numSubseq = " + numSubseq);
    }

    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        return (int) (generateSubset(0, nums, new ArrayList<>(),target) % (Math.pow(10,9) + 7));

    }

    private long generateSubset(int index, int[] nums, List<Integer> subset, int target) {
        if(index >= nums.length - 1 ){
            if(!subset.isEmpty() && subset.get(0) + subset.get(subset.size() - 1) <= target)
                return 1;
            return 0;
        }

        subset.add(nums[index]);
        long left = generateSubset(index + 1, nums, subset, target);

        subset.remove(subset.size() - 1);
        long right = generateSubset(index + 1, nums, subset, target);

        return left + right;
    }
}
