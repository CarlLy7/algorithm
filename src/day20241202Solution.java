import java.util.Arrays;

/**
 * @author: luanyingqi
 * @date: 2024/12/2
 */

public class day20241202Solution {
    //1143
    public int longestCommonSubsequence(String s1, String s2) {
        int[][] memo;
        int m = s1.length();
        int n = s2.length();
        memo = new int[m][n];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dp(s1, 0, s2, 0, memo);
    }

    private int dp(String s1, int i, String s2, int j, int[][] memo) {
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = 1 + dp(s1, i + 1, s2, j + 1, memo);
        } else {
            memo[i][j] = Math.max(dp(s1, i + 1, s2, j, memo), dp(s1, i, s2, j + 1, memo));
        }
        return memo[i][j];
    }

    //583
    public int minDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int commonLen = longestCommonSubsequence(s1, s2);
        return m - commonLen + n - commonLen;
    }

    //712
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] memo = new int[m][n];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dpTwo(s1, 0, s2, 0, memo);
    }

    private int dpTwo(String s1, int i, String s2, int j, int[][] memo) {
        int res = 0;
        if (i == s1.length()) {
            for (; j < s2.length(); j++) {
                res += s2.charAt(j);
            }
            return res;
        }
        if (j == s2.length()) {
            for (; i < s1.length(); i++) {
                res += s1.charAt(i);
            }
            return res;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp(s1, i + 1, s2, j + 1, memo);
        } else {
            memo[i][j] = Math.min(s1.charAt(i) + dpTwo(s1, i + 1, s2, j, memo), s2.charAt(j) + dpTwo(s1, i, s2, j + 1, memo));
        }
        return memo[i][j];
    }

    //516
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }

    //1312
//    public int minInsertions(String s) {
//        int n = s.length();
//        int[][] dp = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            dp[i][i] = 0;
//        }
//        for (int i = n - 1; i >= 0; i--) {
//            for (int j = i + 1; j < n; j++) {
//                if (s.charAt(i) == s.charAt(j)) {
//                    dp[i][j] = dp[i + 1][j - 1];
//                } else {
//                    dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
//                }
//            }
//        }
//        return dp[0][n - 1];
//    }
    public int minInsertions(String s) {
        return s.length() - longestPalindromeSubseq(s);
    }

}
