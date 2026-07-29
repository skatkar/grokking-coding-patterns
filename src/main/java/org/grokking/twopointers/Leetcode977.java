package org.grokking.twopointers;

public class Leetcode977 {
    public int[] sortedSquares(int[] arr) {
        int[] squares = new int[arr.length];
        int length = arr.length;
        int left = 0, right = length - 1;
        int highIndex = length - 1;
        while(left <= right){
            if(arr[left] * arr[left] > arr[right] * arr[right]){
                squares[highIndex--] = arr[left] * arr[left];
                left++;
            }else {
                squares[highIndex--] = arr[right] * arr[right];
                right--;
            }
        }
        return squares;
    }
}
