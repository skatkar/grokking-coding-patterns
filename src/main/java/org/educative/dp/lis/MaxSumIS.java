package org.educative.dp.lis;

public class MaxSumIS {
    public static void main(String[] args) {
        MaxSumIS q = new MaxSumIS();
        int answer = q.findMSIS(new int[]{-4,10,3,7,15});
        System.out.println("answer = " + answer);
    }

    /**
     * Reference - <a href="https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/maximum-sum-increasing-subsequence">...</a>
     * @param nums
     * @return max sum
     */
    public int findMSIS(int[] nums) {
        return recursion(0, -1, 0, nums);
    }

    // The goal is not just to find a max sum but find an increasing subsequence having max sum
    // This is similar to Leetcode 300 but with slight modification
    private int recursion(int index, int prevIndex, int sum, int[] nums) {
        // Base case
        if(index == nums.length)
            return sum;

        // Not pick case
        int notPick = recursion(index + 1, prevIndex, sum, nums);

        // Pick case
        int pick = sum;
        if(prevIndex == -1 || nums[index] > nums[prevIndex]) {
            pick = recursion(index + 1, index, sum + nums[index], nums);
        }

        return Math.max(pick, notPick);
    }
}
