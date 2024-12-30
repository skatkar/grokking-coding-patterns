package org.grokking.recursion;

public class BinarySearch {
    public static boolean binarySearch(int[] nums, int target) {
        if(nums == null || nums.length == 0) return false;

        return searchNumber(nums, target, 0, nums.length - 1);
    }

    private static boolean searchNumber(int[] nums, int target, int low, int high) {
        if(low > high)
            return false;

        int mid = low + (high - low) / 2;

        if(nums[mid] == target)
            return true;
        else if(target < nums[mid])
            return searchNumber(nums, target, low, mid - 1);
        else
            return searchNumber(nums, target, mid + 1, high);
    }

    public static void main(String[] args) {
        System.out.println(BinarySearch.binarySearch(new int[]{1, 2, 3, 4, 5}, 4));
        System.out.println(BinarySearch.binarySearch(new int[]{2, 4, 6, 8, 10}, 5));
        System.out.println(BinarySearch.binarySearch(new int[]{3, 6, 9, 12, 15}, 15));
    }
}
