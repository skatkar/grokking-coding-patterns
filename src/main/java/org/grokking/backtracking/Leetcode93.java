package org.grokking.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Leetcode93 {
    List<String> result;
    public List<String> restoreIpAddresses(String s) {
        result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>());
        return result;
    }

    private void backtrack(String s, int index, List<String> segments) {
        // If we get the 4 parts
        if(segments.size() == 4){
            if(index == s.length()){
                result.add(String.join(".", segments));
            }
        }

        for(int len=1; len<= 3; len++) {
            if(index + len > s.length())break;

            String part = s.substring(index, index + len);
            if(isValid(part)){
                segments.add(part);
                backtrack(s, index + len, segments);
                segments.remove(segments.size() - 1);
            }
        }
    }

    private boolean isValid(String input) {
        if(input.length() > 1 && input.startsWith("0")) return false;

        int number = Integer.parseInt(input);
        return number >=0 && number <= 255;
    }
}
