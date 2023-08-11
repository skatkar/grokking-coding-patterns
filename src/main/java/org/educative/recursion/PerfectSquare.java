package org.educative.recursion;

public class PerfectSquare {
    public static boolean isPerfectSquare(int num) {
        if(num == 1) return true;
        return checkSquare(num, 2);
    }

    private static boolean checkSquare(int num, int divisor) {
        if(divisor == num / 2 + 1)
            return false;

        if(divisor * divisor == num)
            return true;
        return checkSquare(num, divisor + 1);
    }

    public static void main(String[] args) {
        boolean perfectSquare = PerfectSquare.isPerfectSquare(16);
        System.out.println("perfectSquare = " + perfectSquare);
    }
}
