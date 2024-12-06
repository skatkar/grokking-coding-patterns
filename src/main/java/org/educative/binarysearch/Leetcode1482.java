package org.educative.binarysearch;

import java.util.Arrays;

public class Leetcode1482 {
    /**
     *
     * @param bloomDay
     * @param m - bouquets
     * @param k - number of adjacent flowers needed to form a bouquet
     * @return min number of days
     */
    public int minDays(int[] bloomDay, int m, int k) {
        int low = Arrays.stream(bloomDay).min().getAsInt();
        int high = Arrays.stream(bloomDay).max().getAsInt();

        // m * k gives the total days required. We are picking the flower each day if the flower blooms on that day.
        // Assuming we are considering all the bloom days.
        // If the required days are lower than the available days then -1
        long ans = (long) m * k; // The last test case fails if we remove long.
        if(ans > bloomDay.length) return -1;

        while(low <= high) {
            int mid = low + (high - low) / 2;
            // If it is possible, try to go further lower to find out min days
            if(possibleBouquets(bloomDay, mid, m, k)){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }

        return low;
    }

    // Find out whether creating m bouquet by k flowers is possible or not
    private boolean possibleBouquets(int[] bloomDay, int day, int m, int k) {
        int count = 0, noOfBouquets = 0;
        for (int j : bloomDay) {
            if (j <= day) {
                count++;
            } else {
                noOfBouquets += count / k;
                count = 0;
            }
        }
        // The last group of k flowers once we iterate over the entire array
        noOfBouquets += count / k;
        return noOfBouquets >= m;
    }


}
