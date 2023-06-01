package org.educative.topkelements;

import java.util.*;

public class FrequentElements{
    public static List<Integer> topKFrequent(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());
        Map<Integer, Integer> map = new HashMap<>();

        for(int number : arr) {
            map.put(number, map.getOrDefault(number, 0) + 1);
        }

        maxHeap.addAll(map.entrySet());

        for(int i =0; i < k; i++){
            result.add(maxHeap.poll().getKey());
        }

        return result;
    }

    public static List<Integer> topKFrequent2(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a,b) -> a.getValue() - b.getValue());
        Map<Integer, Integer> map = new HashMap<>();

        for(int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        minHeap.addAll(map.entrySet());

        while(minHeap.size() > k)
            minHeap.poll();


        while(!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }

        return result;
    }
}
