package org.educative.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFreeTime {
    public static void main(String[] args) {
        EmployeeFreeTime question = new EmployeeFreeTime();
        List<List<Interval>> schedule =new ArrayList<>();
        schedule.add(Arrays.asList(new Interval(1,2), new Interval(5,6)));
        schedule.add(List.of(new Interval(1, 3)));
        schedule.add(List.of(new Interval(4, 10)));

        List<Interval> intervals = question.employeeFreeTime(schedule);
        System.out.println("intervals = " + intervals);
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        // 1. Flatten out all the schedules into a 1D list
        List<Interval> allSchedules = schedule.stream()
                .flatMap(subList -> subList.stream())
                .collect(Collectors.toList());

        List<Interval> result = new ArrayList<>();

        // 2. Sort these schedules based on the start time of each schedule
        allSchedules.sort(Comparator.comparingInt(sch -> sch.start));

        // 3. Keep track of the previous latest ending time
        int prevEnd = allSchedules.get(0).end;

        for(Interval interval : allSchedules) {

            // 4. If there is a gap between the previous latest ending time and the current start time
            // add that gap to the final result
            if(prevEnd < interval.start) {
                result.add(new Interval(prevEnd, interval.start));
            }

            // 4. Update the previous latest ending time
            prevEnd = Math.max(prevEnd, interval.end);
        }

        return result;
    }
}
