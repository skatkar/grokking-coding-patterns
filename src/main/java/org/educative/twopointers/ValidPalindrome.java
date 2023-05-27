package org.educative.twopointers;

public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        if(s == null || s.length() == 0) return false;

        int left = 0, right = s.length() - 1;
        while(left < right) {
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
