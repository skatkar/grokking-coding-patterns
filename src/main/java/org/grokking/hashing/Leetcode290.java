package org.grokking.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode290 {
    public boolean wordPattern(String pattern, String s) {
        if(pattern == null || pattern.length() == 0 || pattern.length() != s.split(" ").length) return false;

        Map<Character, String> charMap = new HashMap<>();
        Set<String> visitedStrings = new HashSet<>();
        String[] strings = s.split(" ");

        for(int i=0; i < pattern.length(); i++) {
            char pChar = pattern.charAt(i);
            String str = strings[i];
            if(!charMap.containsKey(pChar)) {
                if(visitedStrings.contains(str)) return false;

                charMap.put(pChar, str);
                visitedStrings.add(str);
            }else{
                if(!charMap.get(pChar).equals(str))
                    return false;
            }
        }

        return true;
    }
}
