package org.educative.binarysearch;

public class Leetcode852 {
    public int peakIndexInMountainArray(int[] arr) {
        if(arr == null || arr.length == 0) return 0;

        int low = 0, high = arr.length - 1;

        while(low <= high) {
            int mid = low + (high - low)/2;

            if((mid == 0 || arr[mid - 1] < arr[mid]) && (mid == arr.length - 1 || arr[mid] < arr[mid + 1])) { // verifying mid index number
                return mid;
            }else if(mid != arr.length - 1 && arr[mid] < arr[mid + 1]) { // need to adjust low-pointer
                low = mid + 1;
            }else{ // need to adjust high-pointer
                high = mid - 1;
            }
        }

        return -1;
    }
}
