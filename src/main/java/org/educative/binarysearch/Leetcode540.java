package org.educative.binarysearch;

public class Leetcode540 {
    public int singleNonDuplicate(int[] nums) {

        int n = nums.length;
        if(n == 1) return nums[0];
        // Boundary conditions to avoid theif else conditions to check array edge cases
        if(nums[0] != nums[1]) return nums[0];
        if(nums[n - 2] != nums[n - 1]) return nums[n - 1];

        int low = 1, high = n - 2;
        while(low <= high) {
            int mid = low + (high - low)/2;

            // mid is pointing to the single element
            if(nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) return nums[mid];

            // The left half before the single element has the elements in this fashion- (even, odd), (even, odd)..
            // In this case, discard the left half and move to the right half
            if((mid % 2 == 1 && nums[mid] == nums[mid - 1]) || (mid % 2 == 0 && nums[mid] == nums[mid + 1])){
                low = mid + 1;
            }else // The right half after the single element has the elements in this fashion- (odd, even), (odd,even)...
                high = mid - 1;
        }
        return -1;
    }
}
