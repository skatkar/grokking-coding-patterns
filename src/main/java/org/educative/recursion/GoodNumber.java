package org.educative.recursion;

public class GoodNumber {
    public static boolean isGoodNumber(String arg) {
        if(arg == null || arg.length() == 0)
            return false;
        return helper(arg, 0);
    }

    private static boolean helper(String digits, int index) {
        if (index == digits.length())
            return true;

        char digit = digits.charAt(index);

        if (index % 2 == 0 && !isEven(digit - '0') ||
                index % 2 != 0 && !checkPrime(digit - '0', 2))
            return false;
        return helper(digits, index + 1);
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    private static boolean checkPrime(int number, int divisor) {
        if (divisor > Math.sqrt(number)) {
            return true;
        }
        if(number % divisor == 0)
            return false;
        return checkPrime(number, divisor + 1);
    }

    public static void main(String[] args) {
        boolean goodNumber = GoodNumber.isGoodNumber("23478");
        System.out.println("goodNumber = " + goodNumber);
    }
}
