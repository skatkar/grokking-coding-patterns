package org.educative.recursion;

public class DecToBin {
    public static String decimalToBinary(int decimal) {
        if(decimal == 0)
            return "";

        return decimalToBinary(decimal / 2) + decimal % 2;
    }

    public static void main(String[] args) {
        String s = DecToBin.decimalToBinary(14);
        System.out.println("s = " + s);
    }
}
