package org.grokking.twopointers;

public class Leetcode75 {
    public void sortColors(int[] nums) {
        int length = nums.length;
        int left = 0, right = length - 1;
        int mid = 0;
        while(mid <= right){
            if(nums[mid] == 2){ // 2 is on the left side, move it to the right side
                swap(nums, mid, right);
                right--;
            }else if(nums[mid] == 0){ // mid is pointing to 0, move it to the left side
                swap(nums, mid, left);
                left++;
                mid++;
            }else { // mid is already pointing to 1
                mid++;
            }
        }
    }

    private void swap(int[] nums, int mid, int high){
        int temp = nums[mid];
        nums[mid] = nums[high];
        nums[high] = temp;
    }
}
