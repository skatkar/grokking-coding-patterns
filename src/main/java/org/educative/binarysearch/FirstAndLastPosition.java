package org.educative.binarysearch;

import java.util.Arrays;

public class FirstAndLastPosition {
    public static void main(String[] args) {
        FirstAndLastPosition question = new FirstAndLastPosition();
        int[] range = question.searchRange(new int[]{5,7,7,8,8,10}, 6);
        Arrays.stream(range)
                .forEach(System.out::println);
    }

    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return new int[]{-1,-1};
        int firstIndex = binarySearch(nums, target, true);
        if(firstIndex != -1) {
            return new int[]{firstIndex, binarySearch(nums, target, false)};
        }

        return new int[]{-1,-1};

    }

    private int binarySearch(int[] nums, int target, boolean isFirstSearch) {
        int low = 0, high = nums.length - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(target == nums[mid]) {
                if(isFirstSearch) {
                    if(mid == 0 || nums[mid - 1] < nums[mid]){
                        return mid;
                    }else{
                        high = mid - 1;
                    }
                }else{
                    if(mid == nums.length - 1 || nums[mid] < nums[mid + 1]) {
                        return mid;
                    }else {
                        low = mid + 1;
                    }
                }
            }else if(target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }
}
