package date202602;

import java.util.Arrays;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.13
 * @Since: 1.0
 */

public class day20260213Solution {
    // [931] 下降路径最小和
    // int[][] memo;
    //
    // public int minFallingPathSum(int[][] matrix) {
    // int n = matrix.length;
    // memo = new int[n][n];
    // for (int[] ints : memo) {
    // Arrays.fill(ints, 66666);
    // }
    // int res = Integer.MAX_VALUE;
    // for (int i = 0; i < n; i++) {
    // res = Math.min(res, dp(matrix, n - 1, i));
    // }
    // return res;
    // }
    //
    // private int dp(int[][] matrix, int i, int j) {
    // int n = matrix.length;
    // if (i < 0 || j < 0 || i >= n || j >= n) {
    // return 99999;
    // }
    // if (i == 0) {
    // memo[i][j] = matrix[i][j];
    // return memo[i][j];
    // }
    // if (memo[i][j] != 66666) {
    // return memo[i][j];
    // }
    // // 对于当前这个位置来说，元素一定只能从左上、上、右上三个位置过来，所以对于三个位置过来求最小值
    // memo[i][j] =
    // matrix[i][j] + Math.min(dp(matrix, i - 1, j - 1), Math.min(dp(matrix, i - 1, j), dp(matrix, i - 1, j + 1)));
    // return memo[i][j];
    // }

    // [115] 不同的子序列
    int[][] memo;

    public int numDistinct(String s, String t) {
        memo = new int[s.length()][s.length()];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dp(s, 0, t, 0);
    }

    // s[i...]子序列 中 t[j...] 出现的个数
    private int dp(String s, int i, String t, int j) {
        // base case：如果t匹配完了
        if (j == t.length()) {
            return 1;
        }
        // base case:如果s剩下的长度不够匹配t剩下的长度了
        if (s.length() - i < t.length() - j) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = 0;
        // 如果当前这个元素相等，那么有两种移动策略：①s、t都往后走一个 ②s往后走t不动
        if (s.charAt(i) == t.charAt(j)) {
            res += dp(s, i + 1, t, j + 1) + dp(s, i + 1, t, j);
        } else {
            // 如果两个元素不相等，之后s往后走
            res += dp(s, i + 1, t, j);
        }
        memo[i][j] = res;
        return memo[i][j];
    }

    // [139] 单词拆分
    // int[] memo;
    //
    // public boolean wordBreak(String s, List<String> wordDict) {
    // int n = s.length();
    // memo = new int[n];
    // Arrays.fill(memo, -1);
    // // dp(s,0,wordDict):s[0...]能够被字典中的单词拼出
    // return dp(s, 0, wordDict);
    // }
    //
    // private boolean dp(String s, int start, List<String> wordDict) {
    // if (start == s.length()) {
    // return true;
    // }
    // if (memo[start] != -1) {
    // return memo[start] == 0 ? false : true;
    // }
    // for (String dict : wordDict) {
    // // 超出长度了
    // if (dict.length() + start > s.length()) {
    // continue;
    // }
    // String str = s.substring(start, start + dict.length());
    // if (!wordDict.contains(str)) {
    // continue;
    // }
    // if (dp(s, start + dict.length(), wordDict)) {
    // memo[start] = 1;
    // return true;
    // }
    // }
    // memo[start] = 0;
    // return false;
    // }
}
