package org.educative.cyclicsort;

/**
 * Problem : <a href="https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6393aee5ba7c985340679287">...</a>
 * Leetcode : https://leetcode.com/problems/find-the-duplicate-number/
 */
public class FindDuplicateNumber {
    // TC : o(n)
    // SC : O(1)
    public static int findNumber(int[] nums) {
        if(nums == null || nums.length == 0) return -1;

        int i=0;
        while(i < nums.length) {
            if(nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }else
                i++;
        }

        for(int j=0; j < nums.length; j++) {
            if(nums[j] != j + 1) {
               return nums[j];
            }
        }
        return -1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(FindDuplicateNumber.findNumber(new int[] { 1, 4, 4, 3, 2 }));
        System.out.println(FindDuplicateNumber.findNumber(new int[] { 2, 1, 3, 3, 5, 4 }));
        System.out.println(FindDuplicateNumber.findNumber(new int[] { 2, 4, 1, 4, 4 }));
    }
}
