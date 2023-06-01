package org.educative.topkelements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Leetcode 767 - <a href="https://leetcode.com/problems/reorganize-string/">...</a>
 */
public class ReorganizeString {
    public String reorganizeString(String inputString) {
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for(char ch : inputString.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        maxHeap.addAll(map.entrySet());

        Map.Entry<Character, Integer> previousEntry = null;
        while(!maxHeap.isEmpty()){
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();

            if(previousEntry != null && previousEntry.getValue() > 0) {
                maxHeap.add(previousEntry);
            }

            sb.append(currentEntry.getKey());
            currentEntry.setValue(currentEntry.getValue() - 1);
            previousEntry = currentEntry;
        }

        return sb.length() == inputString.length() ? sb.toString() : "";
    }
}
