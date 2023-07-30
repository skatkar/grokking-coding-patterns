package org.educative.monotonic;

import java.util.*;

public class Leetcode503 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> map = generateMap(nums2);
        for (int j : nums1) {
            result.add(map.getOrDefault(j, -1));
        }

        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private Map<Integer, Integer> generateMap(int[] nums) {
        // Maintain monotonically decreasing stack
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        for(int num : nums) {
            while(!stack.isEmpty() && num > stack.peek()) {
                int number = stack.pop();
                map.put(number, num);
            }
            stack.push(num);
        }

        return map;
     }
}
