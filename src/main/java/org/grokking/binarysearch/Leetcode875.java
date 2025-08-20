package org.grokking.binarysearch;

import java.util.Arrays;

public class Leetcode875 {
    /**
     * This is a pattern called binary search on the answers.
     * Given the piles, there can be many possibilities that can help in finding out the speed.
     * This will range from 1 to whatever is the max value in the pile.
     * Only the speed that is smaller than h and also the smallest among the possibilities is the answer.
     * Instead of linearly going through this range, we can use the binary search approach to find out the min eating speed.
     * @param piles
     * @param h
     * @return min eating speed
     */
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = Arrays.stream(piles).max().getAsInt();

        while(low <= high) {
            int mid = low + (high - low) / 2;
            int requiredHours = getHoursRequired(piles, mid);
            if(requiredHours <= h){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int getHoursRequired(int[] piles, int currSpeed) {
        int totalTime = 0;
        for (int pile : piles) {
            totalTime += Math.ceil((double) pile / currSpeed);
        }

        return totalTime;
    }

    public static void main(String[] args) {
        Leetcode875 q = new Leetcode875();
        q.minEatingSpeed(new int[]{3,6,7,11}, 8);
    }
}
