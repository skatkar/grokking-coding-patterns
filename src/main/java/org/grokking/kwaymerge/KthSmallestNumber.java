package org.grokking.kwaymerge;

import java.util.*;

public class KthSmallestNumber {
    public static void main(String[] args) {
        KthSmallestNumber question = new KthSmallestNumber();
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.asList(1,2,3));
        lists.add(Arrays.asList(4,5));
        lists.add(Arrays.asList(6,7,8,15));
        lists.add(Arrays.asList(10, 11, 12, 13));
        lists.add(Arrays.asList(5,10));

        int result = question.kSmallestNumber(lists, 50);
        System.out.println("result = " + result);
    }

    public int kSmallestNumber(List<List<Integer>> lists, int k) {

        // Array structure:
        // 0th - value
        // 1st - row number
        // 2nd - column number
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // First populate all the rows of the first column
        for(int r=0; r < Math.min(lists.size(), k); r++) {
            minHeap.add(new int[]{lists.get(r).get(0), r, 0});
        }

        while(k > 1) {

            int[] minElement = minHeap.poll();
            int listIndex = minElement[1];
            int elementIndex = minElement[2];

            // proceed to the next column of whatever came out of the minHeap.
            elementIndex++;

            if(elementIndex < lists.get(listIndex).size()) {
                minHeap.add(new int[]{lists.get(listIndex).get(elementIndex), listIndex, elementIndex});
            }

            k--;
        }


        return minHeap.poll()[0];
    }
}
