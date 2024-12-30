package org.grokking.dp.rectangle;

import java.util.Stack;

public class Leetcode85 {
    // This is a DP problem as previously calculated height determines the current height.
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length;
        int[] heights = new int[matrix[0].length];
        int maxArea = 0;
        for(int i=0; i < rows; i++) {
            for(int j=0; j < columns; j++) {
                // Creating a heights array on each row and calculating the max area in the current "histogram".
                if(matrix[i][j] == '1')
                    heights[j]++;
                else // overwrite the previous height value
                    heights[j] = 0;
            }

            int area = largestRectangleArea(heights);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    // Leetcode 84
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) return 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = 0;
        int i=0;

        while(i < heights.length) {
            if(stack.peek() == -1 || heights[i] >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            }else {
                max = Math.max(max, heights[stack.pop()] * (i - stack.peek() - 1));
            }
        }

        while(stack.size() > 1){
            max = Math.max(max, heights[stack.pop()] * (i - stack.peek() - 1));
        }

        return max;
    }
}
