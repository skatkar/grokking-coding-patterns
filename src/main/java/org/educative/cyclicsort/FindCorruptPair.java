package org.educative.cyclicsort;

public class FindCorruptPair {
    /**
     * We are given an unsorted array, nums, with n elements and each element is in the range
     * [1,n] inclusive.The array originally contained all the elements from 1 to n
     *  but due to a data error, one of the numbers is duplicated, which causes another number missing.
     *  Find and return the corrupt pair (missing, duplicated).
     *  Constraints:
     *  1 <= n <= 10^3
     *  1 <= nums[i] <= n
     * @param nums
     * @return
     */
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
                return new int[]{i+1, nums[i]};// 0 - missing 1 - duplicate
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
