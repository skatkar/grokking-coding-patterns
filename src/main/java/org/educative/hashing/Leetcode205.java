package org.educative.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode205 {
    public boolean isIsomorphic(String s, String t) {
        if(s == null || s.length() == 0 || s.length() != t.length()) return false;

        Map<Character, Character> charMap = new HashMap<>();
        Set<Character> mappedChars = new HashSet<>();

        for(int i=0; i < s.length(); i++){
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            if(!charMap.containsKey(sChar)) {
                if(mappedChars.contains(tChar))
                    return false;
                charMap.put(sChar, tChar);
                mappedChars.add(tChar);
            }else{
                if(charMap.get(sChar) != tChar)
                    return false;
            }
        }

        return true;
    }
}
