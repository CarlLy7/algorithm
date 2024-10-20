import java.util.Arrays;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-10-20 17:55
 * @version: 1.0
 */
public class day20241020Solution {
//    public int minDistance(String word1, String word2) {
//        int m = word1.length();
//        int n = word2.length();
//        int[][] dp = new int[m + 1][n + 1];
//        for (int i = 1; i <= m; i++) {
//            dp[i][0] = i;
//        }
//        for (int j = 1; j <= n; j++) {
//            dp[0][j] = j;
//        }
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
//                    dp[i][j] = dp[i - 1][j - 1];
//                } else {
//                    dp[i][j] = min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1, dp[i][j - 1] + 1);
//                }
//            }
//        }
//        return dp[m][n];
//    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }


    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // dp[i]:以nums[i]结尾的连续子数组的最大和
        int[] dp = new int[n];
        // base case
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    int[][] memo;

//    public int longestCommonSubsequence(String text1, String text2) {
//        int m = text1.length();
//        int n = text2.length();
//        memo = new int[m + 1][n + 1];
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -1);
//        }
//        return dp(text1, 0, text2, 0);
//    }

//    private int dp(String text1, int i, String text2, int j) {
//        if (i == text1.length() || j == text2.length()) {
//            return 0;
//        }
//        if (memo[i][j] != -1) {
//            return memo[i][j];
//        }
//        if (text1.charAt(i) == text2.charAt(j)) {
//            memo[i][j] = 1 + dp(text1, i + 1, text2, j + 1);
//        } else {
//            memo[i][j] = Math.max(dp(text1, i + 1, text2, j), dp(text1, i, text2, j + 1));
//        }
//        return memo[i][j];
//    }

//    public int minDistance(String word1, String word2) {
//        int m = word1.length();
//        int n = word2.length();
//        int count = longestCommonSubsequence(word1, word2);
//        return m - count + n - count;
//    }

    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        memo = new int[m][n];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dp(s1, 0, s2, 0);
    }

    private int dp(String s1, int i, String s2, int j) {
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
            memo[i][j] = dp(s1, i + 1, s2, j + 1);
        } else {
            memo[i][j] = Math.min(s1.charAt(i) + dp(s1, i + 1, s2, j), s2.charAt(j) + dp(s1, i, s2, j + 1));
        }
        return memo[i][j];
    }
}
