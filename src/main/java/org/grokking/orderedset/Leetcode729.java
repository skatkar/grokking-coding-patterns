package org.grokking.orderedset;

import java.util.TreeSet;

public class Leetcode729 {
    public static void main(String[] args) {
        MyCalendar obj = new MyCalendar();
        boolean param_1 = obj.book(8,13);
        boolean param_2 = obj.book(13,17);
        boolean param_3 = obj.book(17,20);

        System.out.println("param_1 = " + param_1);
        System.out.println("param_2 = " + param_2);
        System.out.println("param_3 = " + param_3);
    }

    static class MyCalendar {
        TreeSet<int[]> set;

        public MyCalendar() {
            set = new TreeSet<>(
                    (a,b) -> a[0] - b[0]
            );
        }

        public boolean book(int startTime, int endTime) {
            int[] interval = {startTime, endTime};
            int[] floor = set.floor(interval);
            int[] ceil = set.ceiling(interval);

            if(floor != null && floor[1] > startTime) {
                return false;
            }

            if(ceil != null && ceil[0] < endTime){
                return false;
            }

            set.add(interval);
            return true;
        }
    }
}
