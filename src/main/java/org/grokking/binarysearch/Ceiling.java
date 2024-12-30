package org.grokking.binarysearch;

public class Ceiling {
    /**
     * Question : <a href="https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639f1fd54e4f288d4a83ab44">...</a>
     * @param arr
     * @param key
     * @return
     */
    public static int searchCeilingOfANumber(int[] arr, int key) {
        if(arr == null || arr.length == 0) return 0;
        if(key > arr[arr.length - 1]) return -1;

        int low = 0, high = arr.length - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;

            if(arr[mid] == key) {
                return mid;
            }else if(key < arr[mid]){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int search = Ceiling.searchCeilingOfANumber(new int[]{4, 6, 10}, 9);
        System.out.println("search = " + search);
    }
}
