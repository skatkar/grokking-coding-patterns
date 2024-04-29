package org.educative.backtracking.practice;

public class Leetcode31 {
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length == 0)
            return;

        int n = nums.length;
        //Step 1: Find the breach
        int i = n - 2;
        while(i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        //Step 2: Find the next greater number
        int j = n - 1;
        if(i >= 0){
            while(j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums,i, j);
        }

        //Step 3: Reverse the sub-array starting from i + 1
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int l, int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    private void reverse(int[] nums, int low, int high) {
        while(low <= high) {
            swap(nums, low, high);
            low++;
            high--;
        }
    }
}
