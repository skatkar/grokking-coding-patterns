package org.grokking.dp.oned;

public class Leetcode45 {
    public int jump(int[] nums) {
        int[] memo = new int[nums.length];
        return this.countMinJumpsRecursive(memo, nums, 0);
    }

    private int countMinJumpsRecursive(int[] memo, int[] jumps, int currentIndex) {
        // if we have reached the last index, we don't need any more jumps
        if( currentIndex == jumps.length - 1)
            return 0;

        // If an element is 0, then we cannot move through that element
        if (jumps[currentIndex] == 0)
            return Integer.MAX_VALUE;

        // if we have already solved this problem, return the result
        if(memo[currentIndex] != 0)
            return memo[currentIndex];

        int totalJumps = Integer.MAX_VALUE;
        int start = currentIndex + 1;
        int end = currentIndex + jumps[currentIndex];
        while(start < jumps.length && start <= end) {
            // jump one step and recurse for the remaining array
            int minJumps = countMinJumpsRecursive(memo, jumps, start++);
            if(minJumps != Integer.MAX_VALUE)
                // Because at the last index we took jump 0. But from the previous index we took 1 jump to reach the last index. So adding 1.
                // As we know, every index within the range of current index can be reached in one jump.
                // Therefore, we can say that we can reach every index (within the range of current index) in:
                //
                //    'jumps to reach current index' + 1
                totalJumps = Math.min(totalJumps, minJumps + 1);
        }
        memo[currentIndex] = totalJumps;
        return memo[currentIndex];
    }
}
