package org.grokking.subarray;

import java.util.HashMap;
import java.util.Map;

public class Leetcode523 {
    public static void main(String[] args) {
        Leetcode523 question = new Leetcode523();
        boolean answer = question.checkSubarraySum(new int[]{23,2,6,4,7}, 6);
        System.out.println("answer = " + answer);
    }

    // Reference - https://www.youtube.com/watch?v=OKcrLfR-8mE
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0) return false;
        Map<Integer, Integer> map = new HashMap<>(Map.of(0,-1)); // in case we get the sub array in the beginning of the whole array
        int rSum = 0;

        for(int i=0; i < nums.length; i++) {
            rSum += nums[i];

            int remainder = rSum % k;
            // Here is the logic for 23, 2, 4
            // for 23, the remainder is 5,
            // but by adding 2 adn 4 to 23, we again got the remainder as 5.
            // That means after the last occurrence of 5, numbers were added making the sum as divisible by k, and the remainder repeated
            // eg. 23 -> 23 + 2 + 4 -> 29

            // Simple rule, if we've a number non-divisible by k and if we add any number divisible by k to this number, we get the same remainder again
            // Case 1 - We have never seen this remainder before
            if(!map.containsKey(remainder)) {
                map.put(remainder, i);
            }
            // Case 2 - We've seen this remainder before and at least 2 positions before
            else if(i - map.get(remainder) > 1)
                return true;
        }

        return false;
    }
}
