package org.educative.topkelements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class ClosestPoints {

    private static int findDistanceFromZero(int x, int y) {
        return (int) (Math.pow(x,2) + Math.pow(y, 2));
    }

    public static List<Point> kClosest(Point[] points, int k) {

        PriorityQueue<Point> minHeap = new PriorityQueue<>((a, b) -> findDistanceFromZero(a.x, a.y) - findDistanceFromZero(b.x, b.y));
        List<Point> result = new ArrayList<>();

        minHeap.addAll(Arrays.asList(points));

        int i=0;
        while(i < k) {
            result.add(minHeap.poll());
            i++;
        }

        return result;
    }

    public static List<Point> kClosest2(Point[] points, int k) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>((a,b) -> findDistanceFromZero(b.x, b.y) - findDistanceFromZero(a.x, a.y));
        List<Point> result = new ArrayList<>();

        int i=0;
        while(i < k) {
            maxHeap.add(points[i]);
            i++;
        }

        while(i < points.length) {
            Point current = points[i];
            Point top = maxHeap.peek();
            if(findDistanceFromZero(current.x, current.y) < findDistanceFromZero(top.x, top.y)) {
                maxHeap.poll();
                maxHeap.add(current);
            }
        }

        while(!maxHeap.isEmpty()) {
            result.add(maxHeap.poll());
        }

        return result;
    }


}
