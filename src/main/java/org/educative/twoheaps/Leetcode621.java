package org.educative.twoheaps;

import java.util.*;

class Leetcode621 {
    public int leastInterval(char[] tasks, int k) {
        int intervalCount = 0;
        Map<Character, Integer> taskFrequencyMap = new HashMap<>();
        for (char chr : tasks)
            taskFrequencyMap.put(chr, taskFrequencyMap.getOrDefault(chr, 0) + 1);

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
                new PriorityQueue<>(
                        (e1, e2) -> e2.getValue() - e1.getValue());

        // add all tasks to the max heap
        maxHeap.addAll(taskFrequencyMap.entrySet());

        while (!maxHeap.isEmpty()) {
            List<Map.Entry<Character, Integer>> waitList = new ArrayList<>();
            // if k = 2, meaning there should be a gap of 2 between two tasks of same id
            // For example, tasks : A A A B C
            // It can be like this A B C A idle idle A
            // before the second A, 3 tasks are already executed which is k + 1
            int n = k + 1; // try to execute as many as 'k+1' tasks from the max-heap
            for (; n > 0 && !maxHeap.isEmpty(); n--) {
                intervalCount++;
                Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
                if (currentEntry.getValue() > 1) {
                    currentEntry.setValue(currentEntry.getValue() - 1);
                    waitList.add(currentEntry);
                }
            }

            if(!waitList.isEmpty()){
                maxHeap.addAll(waitList); // put all the waiting list back on the heap

                // The queue is not empty. That means there are unfinished tasks left to do.
                // Before starting the next iteration, we need to have a few idle tasks based on the current value of n
                intervalCount += n;
            }
        }

        return intervalCount;

    }
}
