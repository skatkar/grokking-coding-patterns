package org.educative.subsets.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode17 {
    public static void main(String[] args) {
        Leetcode17 q = new Leetcode17();
        List<String> answer = q.letterCombinations("234");
        System.out.println("answer = " + answer);
    }

    public List<String> letterCombinations(String digits) {
        Map<Character, String> map = buildMap();
        List<String> result = new ArrayList<>();
        if(digits.isEmpty())
            return result;

        generateCombinations(0, result, map, digits, new StringBuilder());
        return result;
    }

    // index represents the current character in digits string
    private void generateCombinations(int index, List<String> result,
                                      Map<Character, String> map, String digits, StringBuilder substr) {
        //base case
        if(index == digits.length()){
            result.add(substr.toString());
            return;
        }

        String currString = map.get(digits.charAt(index));
        for(int i=0; i < currString.length(); i++) {
            generateCombinations(index +1, result, map, digits, substr.append(currString.charAt(i)));
            substr.deleteCharAt(substr.length() - 1);
        }
    }

    private Map<Character, String> buildMap() {
        Map<Character, String> map = new HashMap<>();
        map.put('2',"abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        return map;
    }
}
