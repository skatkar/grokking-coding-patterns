package org.educative.dp.subsets;

public class RibbonCut {
    /**
     * This problem is exactly like Leetcode 322
     * Ref: <a href="https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/maximum-ribbon-cut">...</a>
     * @param ribbonLengths
     * @param total
     * @return
     */
    public int countRibbonPieces(int[] ribbonLengths, int total) {
        int n = ribbonLengths.length;
        return total > 0 ? recursion(ribbonLengths, total, n - 1) : total;
    }

    private int recursion(int[] ribbonLengths, int total, int index) {
        // Base condition
        if(total == 0) return 1;
        if(index == 0) return total % ribbonLengths[index] == 0 ?  total / ribbonLengths[index] : 0;

        int notPick = recursion(ribbonLengths, total, index - 1);
        int pick = Integer.MIN_VALUE;
        if(ribbonLengths[index] <= total){
            pick = 1 + recursion(ribbonLengths, total - ribbonLengths[index], index);
        }

        return Math.max(pick, notPick);
    }
}
