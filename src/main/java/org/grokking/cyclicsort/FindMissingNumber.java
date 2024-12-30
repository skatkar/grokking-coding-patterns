package org.grokking.cyclicsort;

/**
 * Problem : <a href="https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63dd93ca488110f74a920bfe">...</a>
 * Leetcode : https://leetcode.com/problems/missing-number/
 */
public class FindMissingNumber {
    // TC : O(n)
    // SC : O(1)
    public static int findMissingNumber(int[] nums) {

        int i=0;
        while(i < nums.length){

            // if nums[i] is beyond the length then we can't put it at the right place
            // so, skip it and keep moving it around.
            // nums[i] number should be at nums[nums[i]]
            if(nums[i] < nums.length && nums[i] != nums[nums[i]]){
                swap(nums, i, nums[i]);
            }else
                i++;
        }


        for(int j=0; j < nums.length; j++) {
            if(j != nums[j]) {
                return j;
            }
        }

        return nums.length;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(FindMissingNumber.findMissingNumber(new int[] { 4, 0, 3, 1 }));
        System.out.println(FindMissingNumber.findMissingNumber(
                new int[] { 8, 3, 5, 2, 4, 6, 0, 1 }));
    }
}
