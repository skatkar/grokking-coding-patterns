package org.grokking.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leetcode761 {
    public static void main(String[] args) {
        Leetcode761 q = new Leetcode761();
        q.makeLargestSpecial("11011000");
    }

    public String makeLargestSpecial(String s) {
        int count = 0, start = 0;
        List<String> subs = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            count += s.charAt(i) == '1' ? 1 : -1;
            if (count == 0) {
                subs.add("1" + makeLargestSpecial(s.substring(start + 1, i)) + "0");
                start = i + 1;
            }
        }
        Collections.sort(subs, Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (String sub : subs) sb.append(sub);
        return sb.toString();
    }
}
