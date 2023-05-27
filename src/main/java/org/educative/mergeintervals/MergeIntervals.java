package org.educative.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Question : <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/m2GlVZBv7Y9">...</a>
 */
class Interval{
    int start;
    int end;
    boolean closed;
    public Interval(int start, int end)
    {
        this.start = start;
        this.end = end;
        this.closed = true; // by default, the interval is closed
    }
    public int getStart()
    {
        return start;
    }

    public int getEnd(){
        return end;
    }
    public void setEnd(int end)
    {
        this.end = end;
    }


    // set the flag for closed/open
    public void setClosed(boolean closed)
    {
        this.closed = closed;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
public class MergeIntervals {
    public static void main(String[] args) {
        MergeIntervals question = new MergeIntervals();
        List<Interval> result = question.mergeIntervals(Arrays.asList(new Interval(1, 5), new Interval(3, 7), new Interval(4, 6)));
        System.out.println("result = " + result);
    }

    // TC : O(n)
    // SC : O(1)
    public List<Interval> mergeIntervals(List <Interval> intervals) {
        if(intervals == null || intervals.isEmpty() || intervals.size() == 1) return intervals;
        Interval newInterval = intervals.get(0);
        List<Interval> result = new ArrayList<>();
        result.add(newInterval);

        for(Interval interval : intervals){
            if(newInterval.end >= interval.start){
                newInterval.end = Math.max(newInterval.end, interval.end);
            }else{
                newInterval = interval;
                result.add(newInterval);
            }
        }
        return result;
    }
}
