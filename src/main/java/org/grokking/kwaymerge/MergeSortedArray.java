package org.grokking.kwaymerge;

import java.util.Arrays;

public class MergeSortedArray {
    public static void main(String[] args) {
        MergeSortedArray question = new MergeSortedArray();
        int[] result = question.mergeSorted(new int[]{0}, 0, new int[]{1}, 1);
        Arrays.stream(result)
                .forEach(i -> System.out.println("i = " + i));
    }

    // Using 3 pointers - p, p1, p2
    public int[] mergeSorted(int[] nums1, int m, int[] nums2, int n) {

        int p = nums1.length - 1;
        int p2 = n - 1;
        int p1 = m - 1;

        while(p1 >= 0 && p2 >= 0) {
            if(nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            }else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }

        while(p1 >= 0) {
            nums1[p] = nums1[p1];
            p1--;
            p--;
        }

        while(p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }

        return nums1;
    }
}
