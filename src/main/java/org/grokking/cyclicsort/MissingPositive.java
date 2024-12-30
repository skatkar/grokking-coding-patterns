package org.grokking.cyclicsort;

/**
 * Problem : <a href="https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63948c59c549a12fb2181118">...</a>
 * Leetcode : https://leetcode.com/problems/first-missing-positive/
 */
public class MissingPositive {

    // TC : O(n)
    // SC : O(1)
    public static int findNumber(int[] nums) {
        int i=0;
        while(i < nums.length) {
            if(nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }else
                i++;
        }

        for (i = 0; i < nums.length; i++)
            if (nums[i] != i + 1)
                return i + 1;

        return nums.length + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(
                MissingPositive.findNumber(new int[] { -3, 1, 5, 4, 2 }));
        System.out.println(
                MissingPositive.findNumber(new int[] { 3, -2, 0, 1, 2 }));
        System.out.println(
                MissingPositive.findNumber(new int[] { 3, 2, 5, 1 }));
        System.out.println(
                MissingPositive.findNumber(new int[]{7,8,9,11,12}));
    }
}
