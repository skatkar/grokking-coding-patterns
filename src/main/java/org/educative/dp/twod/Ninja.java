package org.educative.dp.twod;

import java.util.Arrays;

// Reference - https://www.naukri.com/code360/problems/ninja-s-training_3621003
public class Ninja {

    // DP - Bottom up approach
    public int ninjaTraining(int n, int[][] points) {
        int[][] dp = new int[points.length][4];

        //Initialize the day 0 activities
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][1]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for(int day = 1; day < n; day++) { // for each day
            for(int last =0 ; last < 4; last++) { // for each column in dp
                dp[day][last] = 0;

                int max = 0;
                // for each task in points array
                for(int task = 0; task <= 2; task++) {
                    if(task != last){
                        int point = points[day][task] + dp[day - 1][task];
                        max = Math.max(max, point);
                    }
                }
                dp[day][last] = max;
            }
        }

        return dp[n - 1][3];
    }

    // Memoization
    public int ninjaTraining1(int n, int[][] points) {
        int[][] memo = new int[points.length][4];
        for (int[] subdp : memo) {
            Arrays.fill(subdp, -1);
        }
        return memoizeHelper(n - 1, 3, memo, points);
    }

    // Recursion
    public int ninjaTraining2(int n, int[][] points) {
        return helper(n - 1, 3, points);
    }

    private int memoizeHelper(int dayIndex, int last, int[][] memo, int[][] points) {
        if(memo[dayIndex][last] != -1) return memo[dayIndex][last];

        int max = 0;
        if(dayIndex == 0) {
            int[] day = points[dayIndex];
            for(int i=0; i < day.length; i++) {
                if(i != last){
                    max = Math.max(max, points[0][i]);
                }
            }
            memo[dayIndex][last] = max;
            return memo[dayIndex][last];
        }

        int[] activities = points[dayIndex];
        for(int activity=0; activity < activities.length; activity++) {
            if(activity != last){
                int merit = activities[activity] + memoizeHelper(dayIndex - 1, activity, memo, points);
                max = Math.max(max, merit);
            }
        }
        memo[dayIndex][last] = max;
        return memo[dayIndex][last];
    }

    private int helper(int index, int last, int[][] points) {
        int max = 0;
        if(index == 0) {
            int[] day = points[index];
            for(int i=0; i < day.length; i++) {
                if(i != last){
                    max = Math.max(max, day[i]);
                }
            }
            return max;
        }

        int[] day = points[index];
        for(int i=0; i < day.length; i++) {
            if(i != last){
                int merit = day[i] + helper(index - 1, i, points);
                max = Math.max(merit, max);
            }
        }

        return max;
    }
}
