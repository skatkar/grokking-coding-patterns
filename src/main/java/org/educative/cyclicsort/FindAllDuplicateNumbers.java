package org.educative.cyclicsort;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all duplicate numbers <a href="https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6393b0a334689e585e94a29a">...</a>
 * Leetcode : https://leetcode.com/problems/find-all-duplicates-in-an-array/
 */
public class FindAllDuplicateNumbers {
    public static List<Integer> findNumbers(int[] nums) {
        List<Integer> duplicateNumbers = new ArrayList<>();

        int i=0;
        while(i < nums.length) {
            if(nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }else
                i++;
        }

        for(int j=0; j < nums.length; j++) {
            if(nums[j] != j + 1) {
                duplicateNumbers.add(nums[j]);
            }
        }
        duplicateNumbers.sort(Integer::compare);
        return duplicateNumbers;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        List<Integer> duplicates = FindAllDuplicateNumbers.findNumbers(new int[] { 3, 4, 4, 5, 5 });
        System.out.println("Duplicates are: " + duplicates);

        duplicates = FindAllDuplicateNumbers.findNumbers(new int[] { 5, 4, 7, 2, 3, 5, 3 });
        System.out.println("Duplicates are: " + duplicates);
    }
}
