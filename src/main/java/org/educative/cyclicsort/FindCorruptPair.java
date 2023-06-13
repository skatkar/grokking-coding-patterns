package org.educative.cyclicsort;

public class FindCorruptPair {
    public static int[] findCorruptPair(int[] nums) {

        int i=0;
        while(i < nums.length) {
            if(nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }else
                i++;
        }

        for(i=0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                return new int[]{i+1, nums[i]}; // nums[nums[i]]
            }
        }

        return new int[]{0, 0};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] corruptPair = FindCorruptPair.findCorruptPair(new int[]{3, 1, 2, 3, 6, 4});
        System.out.println("corruptPair = " + corruptPair);
    }
}
