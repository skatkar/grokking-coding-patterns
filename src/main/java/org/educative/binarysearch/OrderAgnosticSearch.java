package org.educative.binarysearch;

public class OrderAgnosticSearch {
    public static int search(int[] arr, int key) {
        if(arr == null || arr.length == 0) return 0;

        int left = 0, right = arr.length - 1;

        boolean sortedIncr = arr[left] < arr[right];

        while(left <= right){
            int mid = left + (right - left)/2;
            if(arr[mid] == key){
                return mid;
            }

            if(sortedIncr){
                if(arr[mid] < key){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }else{
                if(arr[mid] < key){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
        }
        return -1; // element not found
    }

    public static void main(String[] args) {
        int search = OrderAgnosticSearch.search(new int[]{10, 6, 4}, 5);
        System.out.println("search = " + search);
    }
}
