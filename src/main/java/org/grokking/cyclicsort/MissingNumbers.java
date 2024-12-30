package org.grokking.cyclicsort;

import java.util.ArrayList;
import java.util.List;

public class MissingNumbers {
    /**
     * Find the first k missing positive numbers
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> firstKMissingNumbers(int[] nums, int k) {

        int i=0;
        List<Integer> missingNumbers = new ArrayList<>();
        List<Integer> extraNumbers = new ArrayList<>();

        while(i < nums.length) {
            if(nums[i] > 0 && nums[i] < nums.length && nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1);
            }else
                i++;
        }


        for(i=0; i < nums.length && missingNumbers.size() < k; i++) {
            if(nums[i] != i + 1) {
                missingNumbers.add(i + 1);
                extraNumbers.add(nums[i]);
            }
        }

        for(i=1; missingNumbers.size() < k; i++) {
            int candidateNumber = i + nums.length;

            // If the array doesn't contain this number originally, add it to the missing numbers list
            if(!extraNumbers.contains(candidateNumber)) {
                missingNumbers.add(candidateNumber);
            }
        }

        return missingNumbers;
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
