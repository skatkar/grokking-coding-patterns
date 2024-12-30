package org.grokking.recursion;

public class PerfectSquare {
    public static boolean isPerfectSquare(int num) {
        if(num == 1) return true;
        //return checkSquare(num, 2);
        return isPerfectSquareNumber(num, 0, num);
    }

    // TC : O(n)
    private static boolean checkSquare(int num, int divisor) {
        if(divisor == num / 2 + 1)
            return false;

        if(divisor * divisor == num)
            return true;
        return checkSquare(num, divisor + 1);
    }

    // TC : O(log n)
    private static boolean isPerfectSquareNumber(int num, int low, int high) {
        if(low > high)
            return false;

        int mid = low + (high - low)/2;
        if((long) mid * mid == num)
            return true;
        else if((long) mid * mid > num)
            return isPerfectSquareNumber(num, low, mid - 1);
        else
            return isPerfectSquareNumber(num, mid + 1, high);
    }

    public static void main(String[] args) {
        boolean perfectSquare = PerfectSquare.isPerfectSquare(1048576);
        System.out.println("perfectSquare = " + perfectSquare);
    }
}
