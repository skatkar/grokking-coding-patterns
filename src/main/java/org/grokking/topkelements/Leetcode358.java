package org.grokking.topkelements;

import java.util.*;

public class Leetcode358 {
    public static void main(String[] args) {
        Leetcode358 question = new Leetcode358();
        String answer = question.rearrangeString("aabbcc", 3);
        System.out.println("answer = " + answer);
    }

    public String rearrangeString(String str, int k) {
        if(str == null || str.length() == 0) return "";

        StringBuilder result = new StringBuilder();

        Map<Character, Integer> charMap = new HashMap<>();
        for(char ch : str.toCharArray())
            charMap.put(ch, charMap.getOrDefault(ch, 0) + 1);

        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());
        maxHeap.addAll(charMap.entrySet());

        while (!maxHeap.isEmpty()){
            Map.Entry<Character, Integer> current = maxHeap.poll();
            result.append(current.getKey());

            current.setValue(current.getValue() - 1);

            // Hold that character somewhere for k characters
            queue.add(current);

            if(queue.size() == k){
                Map.Entry<Character, Integer> prevEntry = queue.poll();
                if(prevEntry.getValue() > 0) {
                    maxHeap.add(prevEntry);
                }
            }
        }

        return result.length() == str.length() ? result.toString() : "";
    }
}
