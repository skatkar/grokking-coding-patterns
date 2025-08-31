package org.grokking.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//Leetcode - 57
public class InsertInterval {
    public static void main(String[] args) {
        InsertInterval question = new InsertInterval();
        List<Interval> result = question.insertInterval(Arrays.asList(new Interval(1, 3), new Interval(6, 9)), new Interval(2, 5));
        System.out.println("result = " + result);
    }

    public List<Interval> insertInterval(List<Interval> existingIntervals, Interval newInterval) {
        List<Interval> mergedList = new ArrayList<>();
        boolean isAdded = false;

        for(Interval existingInterval : existingIntervals) {
            // Put all the existing intervals ending before the newIntervals into the result
            if(existingInterval.end < newInterval.start) {
                mergedList.add(existingInterval);
            }else if(existingInterval.start > newInterval.end) { // no overlap, the existing interval is starting after newInterval
                if(!isAdded) {
                    mergedList.add(newInterval);
                    isAdded = true;
                }
                mergedList.add(existingInterval);
            }else{ // there is an overlap, merge the intervals
                newInterval.start = Math.min(newInterval.start, existingInterval.start);
                newInterval.end = Math.max(newInterval.end, existingInterval.end);
            }
        }

        if(!isAdded) {
            mergedList.add(newInterval);
        }

        return mergedList;
    }
}
