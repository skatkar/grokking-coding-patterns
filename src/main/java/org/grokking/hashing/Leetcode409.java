package org.grokking.hashing;

import java.util.HashSet;
import java.util.Set;

public class Leetcode409 {
    // Sample input : abccccdd
    public int longestPalindrome(String s) {
        if(s == null || s.length() == 0) return 0;

        Set<Character> unSeenChars = new HashSet<>();
        int count = 0;

        for(char ch : s.toCharArray()) {
            if(unSeenChars.contains(ch)) {
                count += 2; // because the current character is already seen somewhere and we found a match.
                            // This gives a string of length 2, so adding 2 to the count
                unSeenChars.remove(ch);
            }else{
                unSeenChars.add(ch); // we don't find any match, e.g. ccc => 2 c's are already matched but the 3rd one is still unmatched
            }
        }

        // if the set contains few characters, then pick any of them to already matched even length string
        return unSeenChars.isEmpty() ? count : count + 1;
    }
}
