package org.grokking.stacks;

import java.util.*;

/**
 * This question is similar to Leetcode # 636, but here are the differences
 * Log streams will be like these (b - begin/ e - end):
 * foo:10:b
 * bar:20:b
 * baz:30:b
 * baz:50:e
 * bar:60:e
 * foo:100:e
 *
 * Another difference is that the task execution time is end timestamp - begin timestamp. No need to add 1 just like mentioned in the leetcode question
 */

class Log {
    String function;
    int timestamp;
    char type;

    Log(String function, int timestamp, char type) {
        this.function = function;
        this.timestamp = timestamp;
        this.type = type;
    }

    static Log parse(String log) {
        String[] split = log.split(":");
        return new Log(split[0], Integer.parseInt(split[1]), split[2].charAt(0));
    }
}
public class ExclusiveTimeFunction {
    public static void main(String[] args) {
        ExclusiveTimeFunction q = new ExclusiveTimeFunction();
        List<String> list = Arrays.asList(
                "foo:10:b",
                "bar:20:b",
                "baz:30:b",
                "baz:50:e",
                "bar:60:e",
                "foo:100:e"
        );
        Map<String, Integer> result = q.exclusiveTime(list);
        result.forEach((function, elapsed) -> System.out.println(function + " : " + elapsed));
    }

    public Map<String, Integer> exclusiveTime(List<String> logs) {
        Map<String, Integer> result = new HashMap<>();
        Stack<Log> stack = new Stack<>();
        int prevTime = 0;

        for(String log : logs) {
            Log currLog = Log.parse(log);

            if(!stack.isEmpty()) {
                String function = stack.peek().function;

                // We either paused the execution of the function on top of the stack
                // OR we are ending the function on top of the stack
                int elapsed = currLog.timestamp - prevTime;
                result.put(function, result.getOrDefault(function, 0) + elapsed);
            }

            if(currLog.type == 'b') {
                stack.push(currLog);
            }else {
                stack.pop();
            }
            prevTime = currLog.timestamp;
        }

        return result;
    }
}
