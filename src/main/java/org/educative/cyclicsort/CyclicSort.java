package org.educative.cyclicsort;

public class CyclicSort {
    public static void sort(int[] nums) {
        int i =0;
        while(i < nums.length) {
            if(nums[i] < nums.length && nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1);
            }else
                i++;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
