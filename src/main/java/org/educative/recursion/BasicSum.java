package org.educative.recursion;

public class BasicSum {
    public static int calculateSum(int N) {
        if(N == 1)
            return 1;
        return N + calculateSum(N - 1);
    }

    public static void main(String[] args) {
        int answer = BasicSum.calculateSum(100);
        System.out.println("answer = " + answer);
    }
}
