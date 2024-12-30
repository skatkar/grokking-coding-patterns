package org.grokking.mergeintervals;

import java.util.ArrayList;
import java.util.List;

/**
 * Question : <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/xVO1w7pW3Mr">...</a>
 */
public class Intersection {
    public List<Interval> intervalsIntersection(List <Interval> intervalLista, List <Interval> intervalListb) {
        List <Interval> intersections = new ArrayList<>(); // to store all intersecting intervals

        for(int i=0, j=0; i < intervalLista.size() && j < intervalListb.size();) {
            int lower = Math.max(intervalLista.get(i).start, intervalListb.get(j).start);
            int upper = Math.min(intervalLista.get(i).end, intervalListb.get(j).end);

            // This will ensure that i & j pointed intervals are having an overlap
            // If not, then simply ignore that interval
            if(lower <= upper) {
                intersections.add(new Interval(lower, upper));
            }

            // Now increment i & j strategically
            if(intervalLista.get(i).end < intervalListb.get(j).end) {
                i++;
            }else {
                j++;
            }
        }

        return intersections;
    }
}
