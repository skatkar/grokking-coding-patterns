package org.educative.topkelements;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LeetCode973 {
    private int findDistFromOrigin(int x, int y) {
        return (int) (Math.pow(x,2) + Math.pow(y,2));
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> findDistFromOrigin(a[0], a[1])));
        List<int[]> result = new ArrayList<>();

        for(int[] point : points) {
            minHeap.add(point);
        }

        int i=0;
        while(i < k){
            result.add(minHeap.poll());
            i++;
        }

        return result.toArray(int[][]::new);

    }

}
