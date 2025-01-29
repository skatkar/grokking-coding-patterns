package org.grokking.twopointers;

public class Leetcode443 {
    public int compress(char[] chars) {
        int idx = 0;
        int n = chars.length;
        for(int i=0; i < n; i++) {
            char ch = chars[i];
            int count = 0;

            while(i < n && chars[i] == ch) {
                count++;
                i++;
            }

            chars[idx++] = ch;
            if (count != 1) {
                for (char c : Integer.toString(count).toCharArray()) {
                    chars[idx++] = c;
                }
            }
            i--;
        }

        return idx;
    }
}
