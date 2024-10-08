package org.educative.dp.lis;

public class LongestAlternatingSub {
    /**
     * <a href="https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/longest-alternating-subsequence">...</a>
     * @param nums
     * @return
     */
    public int findLASLength(int[] nums) {
        return Math.max(
                recursion(0, -1, nums, true),
                recursion(0, -1, nums, false)
        );
    }

    private int recursion(int index, int prevIndex, int[] nums, boolean isAsc) {
        // Base condition
        if(index == nums.length)
            return 0;

        // Pick - not pick scenario

        // Not pick scenario - Don't consider the current number
        int notPick = recursion(index + 1, prevIndex, nums, isAsc);

        int pick = 0;
        if(isAsc){
            if(prevIndex == -1 || nums[prevIndex] > nums[index]){
                pick = 1 + recursion(index + 1, index, nums, !isAsc);
            }
        }else{
            if(prevIndex == -1 || nums[prevIndex] < nums[index]){
                pick = 1 + recursion(index + 1, index, nums, !isAsc);
            }
        }

        return Math.max(pick, notPick);
    }
}
