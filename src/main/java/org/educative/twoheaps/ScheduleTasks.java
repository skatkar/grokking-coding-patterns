package org.educative.twoheaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ScheduleTasks {
    public static void main(String[] args) {
        ScheduleTasks question = new ScheduleTasks();
        List<List<Integer>> tasksList = new ArrayList<>();
        tasksList.add(List.of(2,3));
        tasksList.add(List.of(4,7));
        tasksList.add(List.of(8,18));
        tasksList.add(List.of(19,25));
        tasksList.add(List.of(26,30));

        int tasks = question.tasks(tasksList);
        System.out.println("tasks = " + tasks);
    }

    public int tasks(List<List<Integer>> tasksList) {
        // 0 - start 1 - end
        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.get(1)));
        tasksList.sort(Comparator.comparing(a -> a.get(0)));

        for(List<Integer> task : tasksList) {
            if(minHeap.isEmpty() || task.get(0) < minHeap.peek().get(1)) {
                minHeap.add(task);
            }else if(task.get(0) >= minHeap.peek().get(1)){
                minHeap.poll();
                minHeap.add(task);
            }
        }

        return minHeap.size();
    }
}
