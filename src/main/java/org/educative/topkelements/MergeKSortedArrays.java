package org.educative.topkelements;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


class Entry {
    int value, rowIndex, colIndex;

    public Entry(int value, int rowIndex, int colIndex) {
        this.value = value;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }
}
public class MergeKSortedArrays {
    public static void main(String[] args) {
        MergeKSortedArrays question = new MergeKSortedArrays();
        int[][] input = new int[][]{
                {1,5,13},
                {4,6,8,12},
                {2,10,11,14},
        };

        int[] mergeKArrays = question.mergeKArrays2(input);
        for(int num : mergeKArrays) {
            System.out.print(num + " ");
        }
    }

    /**
     * Question : https://leetcode.com/discuss/interview-question/617596/facebook-onsite-merge-k-sorted-arrays
     * Similar to https://leetcode.com/problems/merge-k-sorted-lists/
     * @param input
     * @return
     */
    public int[] mergeKArrays(int[][] input) {
        List<Integer> result = new ArrayList<>();
        // 0 - value, 1 - array index, 2 - index within that array
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for(int i=0; i < input.length; i++) {
            minHeap.add(new int[]{input[i][0], i, 0});
        }

        while(!minHeap.isEmpty()) {
            int[] currMin = minHeap.poll();
            int rowIndex = currMin[1];
            int colIndex = currMin[2];

            result.add(currMin[0]);

            if(colIndex < input[rowIndex].length - 1) {
                minHeap.add(new int[]{input[rowIndex][colIndex + 1], rowIndex, colIndex + 1});
            }
        }

        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public int[] mergeKArrays2(int[][] input) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Entry> minHeap = new PriorityQueue<>((a, b) -> a.value - b.value);
        for(int i=0; i < input.length; i++) {
            minHeap.add(new Entry(input[i][0], i, 0));
        }

        while(!minHeap.isEmpty()) {
            Entry currMin = minHeap.poll();
            int rowIndex = currMin.rowIndex;
            int colIndex = currMin.colIndex;

            result.add(currMin.value);

            if(colIndex < input[rowIndex].length - 1) {
                minHeap.add(new Entry(input[rowIndex][colIndex + 1], rowIndex, colIndex + 1));
            }
        }

        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
