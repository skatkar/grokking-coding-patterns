package org.educative.mergeintervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Question : <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/7DE8JxQ3YrG">...</a>
 */
public class MeetingRooms2 {
    public static void main(String[] args) {
        MeetingRooms2 question = new MeetingRooms2();
        int meetingRooms = question.minMeetingRooms(Arrays.asList(new Interval(3, 17),
                new Interval(19, 20),
                new Interval(20, 22),
                new Interval(1, 18),
                new Interval(9, 19),
                new Interval(21, 22),
                new Interval(3, 4),
                new Interval(7, 22)));
        System.out.println("meetingRooms = " + meetingRooms);
    }

    public int minMeetingRooms(List<Interval> intervals) {

        intervals.sort(Comparator.comparingInt(a -> a.start));
        PriorityQueue<Interval> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.end));

        for(Interval interval : intervals) {
            if(minHeap.isEmpty() || minHeap.peek().end > interval.start) {
                minHeap.add(interval);
            }else if(minHeap.peek().end < interval.end){
                minHeap.poll();
                minHeap.add(interval);
            }
        }
        return minHeap.size();
    }
}
