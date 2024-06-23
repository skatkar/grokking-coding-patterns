package org.educative.dp.practice;

import java.util.Arrays;

public class Leetcode983 {

    public int mincostTickets(int[] days, int[] costs) {
        int[] memo = new int[days.length];
        Arrays.fill(memo, -1);
        return memoization(days, costs, memo, 0);
    }

    private int memoization(int[] days, int[] costs, int[] memo, int dayIndex) {

        // Base condition
        if(dayIndex >= days.length) return 0;

        if(memo[dayIndex] != -1) return memo[dayIndex];

        // 1-day pass
        int costOneDayTicket = costs[0] + memoization(days, costs, memo, dayIndex + 1);

        // 7-day pass
        // From current day, move 7 days ahead and pick that day as it will cover the last 7 days of the travel
        int i;
        for(i=dayIndex; i < days.length; i++) {
            if(days[i] >= days[dayIndex] + 7)
                break;
        }
        int costSevenDaysTicket = costs[1] + memoization(days, costs, memo, i);

        // 30-day pass
        // From current day, move 30 days ahead and pick that day as it will cover the last 30 days of travel.
        for(i=dayIndex; i < days.length; i++) {
            if(days[i] >= days[dayIndex] + 30)
                break;
        }
        int costThirtyDaysTicket = costs[2] + memoization(days, costs, memo, i);

        memo[dayIndex] = Math.min(costOneDayTicket,
                Math.min(costSevenDaysTicket, costThirtyDaysTicket));
        return memo[dayIndex];
    }
}
