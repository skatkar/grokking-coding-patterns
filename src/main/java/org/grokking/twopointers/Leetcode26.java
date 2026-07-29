package org.grokking.twopointers;

public class Leetcode26 {
    public int removeDuplicates(int[] arr) {
        if(arr == null || arr.length == 0) return 0;
        int first = 0, second = 1;
        int length = arr.length;
        while(second < length) {
            if(arr[first] != arr[second]){
                arr[++first] = arr[second];
            }
            second++;
        }
        return first + 1;
    }
}
