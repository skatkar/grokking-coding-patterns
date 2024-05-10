package org.educative.dp.oned;

public class Leetcode152 {
    // Reference - https://www.youtube.com/watch?v=lXVy6YWFcRM
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int curMax = nums[0], curMin = nums[0], result = nums[0];

        for(int i=1; i < n; i++) {
            // As we are going to change the value of curMax, have to take the back-up first
            int temp = curMax;

            // There are three possibilities - either curMax * current or curMin * current or current number itself is maximum than the previously assumed max value
            curMax = Math.max(Math.max(curMax * nums[i], curMin * nums[i]), nums[i]);

            // Similar logic applies here as well
            curMin = Math.min(Math.min(temp * nums[i], curMin * nums[i]), nums[i]);

            // This will help a lot if one of the numbers is 0.
            // If we don't have this step, then the product will become 0 and previously calculated product will be gone
            result = Math.max(result, curMax);
        }
        return result;
    }
}
