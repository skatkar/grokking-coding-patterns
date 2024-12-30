package org.grokking.subarray;

import java.util.HashMap;
import java.util.Map;

public class Leetcode560 {

    public static void main(String[] args) {
        Leetcode560 question = new Leetcode560();
        int answer = question.subarraySum(new int[]{1, 2, 3}, 3);
        System.out.println("answer = " + answer);
    }

    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0) return 0;

        int total = 0, rSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);

        for (int num : nums) {
            rSum += num;

            int diff = rSum - k;

            if (map.containsKey(diff)) {
                total += map.get(diff);
            }

            map.put(rSum, map.getOrDefault(rSum, 0) + 1);
        }

        return total;
    }
}
