package org.grokking.twoheaps;

import java.util.HashMap;
import java.util.Map;

public class Leetcode2365 {
    public static void main(String[] args) {
        Leetcode2365 q = new Leetcode2365();
        q.taskSchedulerII(new int[]{1,2,1,2,3,1}, 3);

    }

    public long taskSchedulerII(int[] tasks, int space) {
        // A map to maintain the mapping of a task and the next day at which its next occurrence to execute
        Map<Integer,Long> taskNextOccurrence = new HashMap<>();
        long days = 0;
        for(int task : tasks) {
            // If the required days have already passed before its next expected day, execute the task on that day.
            // Else execute it on the expected day.
            days = Math.max(days, taskNextOccurrence.getOrDefault(task, days));
            taskNextOccurrence.put(task, days + space + 1);

            // We executed the task so increment the days count
            days++;
        }
        return days;
    }
}
