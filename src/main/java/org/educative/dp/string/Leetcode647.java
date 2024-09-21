package org.educative.dp.string;

public class Leetcode647 {
    public static void main(String[] args) {
        Leetcode647 q = new Leetcode647();
        int answer = q.findCPS("cddpd");
        System.out.println(answer);
    }

    public int findCPS(String st) {
        int length = st.length();

        // This DP array will help in identifying whether the intermediate string was a palindrome or not.
        // For example, "ddpd"
        // while we are at the first and last 'd', we can't directly say it is a palindrome just because the two characters are matching.
        // We need to know whether the string formed by the remaining chars is a palindrome or not.
        // DP table will help us in identifying that.
        boolean[][] dp = new boolean[length][length];
        int count = 0;

        // Each 1-length string is a palindrome
        for(int i=0; i < length; i++) {
            dp[i][i] = true;
            count++;
        }

        for(int start=length - 1; start >= 0; start--) {
            for(int end = start + 1; end < length; end++) {
                if(st.charAt(start) == st.charAt(end)){
                    if(end - start == 1 || dp[start + 1][end - 1]){
                        dp[start][end] = true;
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
