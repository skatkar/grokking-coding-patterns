package org.grokking.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode49 {
    public static void main(String[] args) {
        Leetcode49 question = new Leetcode49();
        question.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
    }

    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();
        Map<Double, List<String>> map = new HashMap<>();
        for(String str : strs) {
            double product = charProducts(str);
            List<String> strings = map.getOrDefault(product, new ArrayList<>());
            strings.add(str);
            map.put(product, strings);
        }

        map.forEach((k,v) -> result.add(v));

        return result;
    }

    private double charProducts(String str) {
        double[] prime = new double[]{2,3,7,11,13,17,19,21,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89, 97};
        double result = 1;

        for(char ch : str.toCharArray()) {
            result = result * prime[ch - 'a'];
        }

        return result;
    }
}
