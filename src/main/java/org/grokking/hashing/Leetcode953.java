package org.grokking.hashing;

import java.util.HashMap;
import java.util.Map;

public class Leetcode953 {
    // This map will track the character and its index in that dictionary
    // e.g. h -> 0, j -> 1, if we are given the order as "hj"
    Map<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        if(words == null || words.length == 0) return false;
        map = new HashMap<>();

        for(int i=0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }

        for(int i=0; i < words.length - 1; i++) {
            String firstWord = words[i];
            String secondWord = words[i + 1];

            if(isNotSorted(firstWord, secondWord))
                return false;
        }

        return true;
    }

    private boolean isNotSorted(String firstWord, String secondWord) {
        int m = firstWord.length();
        int n = secondWord.length();

        // compare the characters of the two words one by one
        for(int i=0, j=0; i < m && j < n; i++, j++) {
            char firstChar = firstWord.charAt(i);
            char secondChar = secondWord.charAt(j);

            // firstChar is greater than the second one as per the dictionary
            if(firstChar != secondChar)
                return map.get(firstChar) > map.get(secondChar);
        }

        // this is as per the third test case; apple > app (in English, so same concept applies here)
        return m > n;
    }
}
