package org.educative.subarray;

import java.util.HashMap;
import java.util.Map;

public class Leetcode974 {
    public int subarraysDivByK(int[] nums, int k) {
        if(nums == null || nums.length == 0) return 0;

        int total = 0, rSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);

        for (int num : nums) {
            rSum += num;

            int mod = rSum % k;

            // We're looking for a "valid mod" index.
            // Ex: 4 % 5 = 4, 9 % 5 = 4. So, how many 5s or multiples of 5s you add to the number will give you the same mod.
            // Similarly, in the negative numbers band, you would add 5 to get the first positive mod value. Also, in Java, -4 % 5 = -4 and not 4
            
            // For a given k, valid mods are: 0,1,2,... k - 1
            if(mod < 0) mod += k;

            if (map.containsKey(mod)) {
                total += map.get(mod);
            }

            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }

        return total;
    }
}
