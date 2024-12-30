package org.grokking.recursion;

public class NumberFreq {
    public static int countOccurrences(int[] arr, int key) {
        if(arr == null || arr.length == 0)
            return 0;
        return helper(arr, key, 0);
    }

    private static int helper(int[] arr, int key, int index) {
        if(index == arr.length)
            return 0;
        return arr[index] == key ? 1 + helper(arr, key, index + 1) : helper(arr,key, index + 1);
    }

    public static void main(String[] args) {
        int i = NumberFreq.countOccurrences(new int[]{1, 2, 2, 2, 3}, 2);
        System.out.println("i = " + i);
    }
}
