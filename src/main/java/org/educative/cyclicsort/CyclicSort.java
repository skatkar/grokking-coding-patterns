package org.educative.cyclicsort;

/**
 * Problem : <a href="https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6393a98bd8a93f4bff961b4d">...</a>
 */
public class CyclicSort {
    // TC : O(n)
    // SC : O(1)
    public static void sort(int[] nums) {
        int i = 0;
        while (i < nums.length) {
          int j = nums[i] - 1;
          if (nums[i] != nums[j])
            swap(nums, i, j);
          else
            i++;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        /*int[] arr = new int[] { 3, 1, 5, 4, 2 };
        CyclicSort.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[] { 2, 6, 4, 3, 1, 5 };
        CyclicSort.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[] { 1, 5, 6, 4, 3, 2, 7 };
        CyclicSort.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();*/

        int[] arr = new int[] {4,3,2,7,8,2,3,1};
        CyclicSort.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }
}
