package org.educative.cyclicsort;

public class MissingNumber {
    public static int findMissingNumber(int[] arr) {
        for(int i=0; i < arr.length; i++) {
            while(arr[i] != i && arr[i] < arr.length) {
                swap(arr, i, arr[i]);
            }
        }

        for(int i=0; i < arr.length; i++){
            if(arr[i] != i){
                return i;
            }
        }

        return arr.length;
    }

    public static int findMissingNumber2(int[] arr) {
        int i=0;

        while(i < arr.length) {
            if(arr[i] < arr.length && arr[i] != arr[arr[i]]) {
                swap(arr, i, arr[i]);
            }else
                i++;
        }

        for(i=0; i < arr.length; i++) {
            if(arr[i] != i){
                return i;
            }
        }

        return arr.length;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int missingNumber = MissingNumber.findMissingNumber(new int[]{0,1,2});
        System.out.println("missingNumber = " + missingNumber);
    }
}
