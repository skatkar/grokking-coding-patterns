package org.educative.recursion;

public class Factorial {
    public static int calculateFactorial(int number) {
        if(number == 0)
            return 1;
        return number * calculateFactorial(number - 1);
    }
}
