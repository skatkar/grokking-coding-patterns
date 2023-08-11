package org.educative.recursion;

public class PrimeNumber {
    public static void main(String[] args) {
        PrimeNumber q = new PrimeNumber();
        boolean prime = q.isPrime(12);
        System.out.println("prime = " + prime);
    }

    public boolean isPrime(int number) {
        if(number == 1) return false;

        return checkPrime(number, 2);
    }

    private boolean checkPrime(int number, int divisor) {
        if (divisor > Math.sqrt(number)) {
            return true;
        }
        // This condition will also work
//        if(divisor == number / 2 + 1)
//            return true;
        if(number % divisor == 0)
            return false;
        return checkPrime(number, divisor + 1);
    }
}
