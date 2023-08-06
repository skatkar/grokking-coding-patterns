package org.educative.hashing;

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
}
