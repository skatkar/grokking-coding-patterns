package org.grokking.orderedset;

import java.util.*;

public class Leetcode2363 {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Map<Integer, Integer> map = new TreeMap<>();

        for(int[] item : items1) {
            map.put(item[0], map.getOrDefault(item[0], 0) + item[1]);
        }

        for(int[] item : items2) {
            map.put(item[0], map.getOrDefault(item[0], 0) + item[1]);
        }

        List<List<Integer>> result = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entries : map.entrySet()) {
            result.add(Arrays.asList(entries.getKey(), entries.getValue()));
        }

        return result;
    }
}
