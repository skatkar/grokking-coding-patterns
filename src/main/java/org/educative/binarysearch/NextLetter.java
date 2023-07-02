package org.educative.binarysearch;

public class NextLetter {
    public static char searchNextLetter(char[] letters, char key) {
        if(letters == null || letters.length == 0) return '0';

        int low = 0, high = letters.length - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(key < letters[mid]) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return letters[low % letters.length];
    }
}
