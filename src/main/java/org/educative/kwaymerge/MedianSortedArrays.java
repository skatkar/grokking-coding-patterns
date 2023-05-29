package org.educative.kwaymerge;

public class MedianSortedArrays {
    public static float findMedian(int[] nums1, int[] nums2) {

        int totalLength = nums1.length + nums2.length;
        int mid = totalLength / 2;
        int[] mergedArray = new int[totalLength];
        int k = 0, count = 0;
        int i = 0, j = 0;

        while(count < totalLength) {
            //1. If within the range of both arrays
            if(i < nums1.length && j < nums2.length) {
                if(nums1[i] <= nums2[j]){
                    mergedArray[k++] = nums1[i++];
                }else {
                    mergedArray[k++] = nums2[j++];
                 }
            }
            //2. Iterated over all nums2, so iterating just nums1
            else if(i < nums1.length) {
                mergedArray[k++] = nums1[i++];
            }
            //3. Iterated over all nums1, so iterating just nums2
            else {
                mergedArray[k++] = nums2[j++];
            }
            count++;
        }

        return totalLength % 2 == 0 ? (float) (mergedArray[mid] + mergedArray[mid - 1]) / 2 : (float) mergedArray[mid];
    }

    public static void main(String[] args) {
        float median = MedianSortedArrays.findMedian(new int[]{1,3,5,7,9}, new int[]{2,4,6,8,10});
        System.out.println("median = " + median);
    }
}
