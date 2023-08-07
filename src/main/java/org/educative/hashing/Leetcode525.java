package org.educative.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leetcode525 {
    public int findMaxLength(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        int max = 0;
        int rSum = 0;
        for(int i=0; i < nums.length; i++) {
            // If the current number is 1, increment rSum
            // else, decrement it
            if(nums[i] == 1) {
                rSum += 1;
            }else{
                rSum -= 1;
            }


            if(map.containsKey(rSum)){
                max = Math.max(max, i - map.get(rSum));
            }else{
                map.put(rSum, i);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        Leetcode525 question = new Leetcode525();
        int maxLength = question.findMaxLength(new int[]{0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1});
        System.out.println("maxLength = " + maxLength);
        int[] maxLengthPositions = question.findMaxLengthPositions(new int[]{0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1});
        Arrays.stream(maxLengthPositions)
                .forEach(System.out::println);
    }

    public int[] findMaxLengthPositions(int[] nums) {
        if(nums == null || nums.length == 0) return new int[]{};

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        int max = 0;
        int rSum = 0, start = 0, end = 0;
        for(int i=0; i < nums.length; i++) {
            // If the current number is 1, increment rSum
            // else, decrement it
            if(nums[i] == 1) {
                rSum += 1;
            }else{
                rSum -= 1;
            }


            if(map.containsKey(rSum)){
                if(max < i - map.get(rSum)){
                    max = i - map.get(rSum);
                    start = map.get(rSum) + 1;
                    end = i;
                }
            }else{
                map.put(rSum, i);
            }
        }

        return new int[]{start,end};
    }
}
