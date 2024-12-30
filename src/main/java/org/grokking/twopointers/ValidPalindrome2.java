package org.grokking.twopointers;

public class ValidPalindrome2 {
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while(left < right) {
            if(s.charAt(left) != s.charAt(right)) {
                return cheackPalidrome(s, left, right - 1) || cheackPalidrome(s, left + 1, right);
            }
            left++;
            right--;
        }

        return true;
    }

    private boolean cheackPalidrome(String s, int left, int right) {
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
