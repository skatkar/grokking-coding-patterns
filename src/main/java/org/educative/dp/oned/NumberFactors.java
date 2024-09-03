package org.educative.dp.oned;

/**
 * Reference - <a href="https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/number-factors">...</a>
 */
public class NumberFactors {
    public static void main(String[] args) {
        NumberFactors q = new NumberFactors();
        int ways = q.countWays(4);
        System.out.println(ways);
    }

    public int countWays(int n) {
        // This has to be captured before calling the dynamic function else it will fail at line 16 below.
        if(n == 0 || n == 1) return 1;
        return dynamic(n);
    }

    private int dynamic(int sum){
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i=2; i <= sum; i++) {
            dp[i] = dp[i - 1];
            if(i - 3 >= 0) dp[i] += dp[i - 3];
            if(i - 4 >= 0) dp[i] += dp[i - 4];
        }

        return dp[sum];
    }

    private int recursion(int sum) {
        if(sum == 0)
            return 1;
        if(sum== 1)
            return 1;

        int answer = recursion(sum - 1);
        if(sum - 3 >= 0)
            answer += recursion(sum - 3);
        if(sum - 4 >= 0)
            answer += recursion(sum - 4);
        return answer;
    }
}
