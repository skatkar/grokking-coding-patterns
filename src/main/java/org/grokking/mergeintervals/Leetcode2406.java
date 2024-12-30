package org.grokking.mergeintervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Leetcode2406 {
    public static void main(String[] args) {
        Leetcode2406 q = new Leetcode2406();
        int groups = q.minGroups(new int[][]{
                {1,3},
                {5,6},
                {8,10},
                {11, 13}
        });
        System.out.println("groups = " + groups);
    }

    public int minGroups(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;

        // Ascending right value
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // Sorting based on the left value
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        for(int[] interval : intervals){
            // No overlap. So heap peek and the current interval belong to the same group
            if(!minHeap.isEmpty() && minHeap.peek()[1] < interval[0]){
                minHeap.poll();
            }
            minHeap.add(interval);
        }

        return minHeap.size();
    }
}
