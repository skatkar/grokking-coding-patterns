package org.educative.mergeintervals;

import java.util.*;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int k) {
        if(tasks == null || tasks.length == 0) return 0;

        int intervalCount = 0;
        Map<Character,Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());

        for(char task : tasks) {
            map.put(task, map.getOrDefault(task,0) + 1);
        }

        maxHeap.addAll(map.entrySet());

        while(!maxHeap.isEmpty()) {
            // queuing up all the tasks which are not finished yet.
            List<Map.Entry<Character, Integer>> waitList = new ArrayList<>();

            int i = k + 1;
            for(; i > 0 && !maxHeap.isEmpty(); i--) { // as long as i > 0, at this time, we don't have anything to process
                intervalCount++;
                Map.Entry<Character, Integer> current = maxHeap.poll();

                if(current.getValue() > 1) { // If the count is 1 then we don't want them to be held in a queue. In other words, it is their last occurrence and intervalCount++ is already taking care of that
                    current.setValue(current.getValue() - 1);
                    waitList.add(current);
                }
            }

            if(!waitList.isEmpty()){ // put back all the tasks waiting.
                maxHeap.addAll(waitList);
                intervalCount += i;
            }

        }

        return intervalCount;
    }
}
