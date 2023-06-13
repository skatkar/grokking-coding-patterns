package org.educative.cyclicsort;

import java.util.ArrayList;
import java.util.List;

public class MissingNumbers {
    public static List<Integer> firstKMissingNumbers(int[] nums, int k) {

        int i=0;
        List<Integer> result = new ArrayList<>();

        while(i < nums.length) {
            if(nums[i] > 0 && nums[i] < nums.length && nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1);
            }else
                i++;
        }


        for(i=0; i < nums.length; i++) {
            if(nums[i] != i + 1 && result.size() <= k){
                result.add(i+1);
            }
        }

        return result;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        List<Integer> integers = MissingNumbers.firstKMissingNumbers(new int[]{2, 5, 4}, 3);
        integers.stream()
                .forEach(System.out::println);
    }
}
