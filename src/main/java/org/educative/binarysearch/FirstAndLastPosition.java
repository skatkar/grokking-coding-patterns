package org.educative.binarysearch;

import java.util.Arrays;

public class FirstAndLastPosition {
    public static void main(String[] args) {
        FirstAndLastPosition question = new FirstAndLastPosition();
        int[] range = question.searchRange(new int[]{5,7,7,8,8,10}, 8);
        Arrays.stream(range)
                .forEach(System.out::println);
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};

        int low = 0, high = nums.length - 1;

        // First search whether such number exist in the array or not
        int mid = binarySearch(nums, low, high, target, true);

        if (mid != -1) {
            // TODO
            //return new int[]{binarySearch(nums, low, mid, target), binarySearch(nums, mid + 1, high, target)};
        }

        return new int[]{-1, -1};
    }

    private int binarySearch(int[] nums, int low, int high, int target, boolean isFirst) {

        while(low <= high) {
            int mid = low + (high - low) / 2;

            if(target == nums[mid]){
                if(isFirst && (mid == 0 || nums[mid - 1] < nums[mid])) {
                    return mid;
                }else if(!isFirst && (mid == nums.length - 1 || nums[mid] < nums[mid + 1])) {
                    return mid;
                }
                return -1;
            }else if(target < nums[mid]){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return -1;
    }
}
