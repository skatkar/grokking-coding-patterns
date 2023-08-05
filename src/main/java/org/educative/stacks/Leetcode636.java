package org.educative.stacks;

import java.util.List;
import java.util.Stack;

public class Leetcode636 {

    // Sample input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
    public int[] exclusiveTime(int n, List<String> logs) {
        if(logs == null || logs.size() == 0) return new int[]{};

        int[] result = new int[n];

        // Stack will be holding function IDs
        Stack<Integer> stack = new Stack<>();

        // prevTime represents when did the last task paused / completed its execution
        int prevTime = 0;

        for (String log : logs) {
            String[] splitted = log.split(":");

            int currTime = Integer.parseInt(splitted[2]);

            if ("start".equals(splitted[1])) {
                if (!stack.isEmpty()) {
                    result[stack.peek()] += currTime - prevTime;// because previous task was executing before this task came up
                }

                stack.push(Integer.parseInt(splitted[0]));
                prevTime = currTime;
            } else {
                // Make sure to +1 to as end takes the whole unit of time.
                // if end time is 5 then it'll take 1 unit to actually finish and the new task may start at 6
                // simple words, end task "started" at 5 and will finish before 6
                // OR if it started at 2 and suppose to end at 5, it was executing at 2,3,4, and 5. So (5 - 2) + 1
                // as per the question : "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function call with function ID 1 ended at the end of timestamp 2
                result[stack.pop()] += currTime - prevTime + 1;
                prevTime = currTime + 1;
            }
        }


        return result;
    }
}
