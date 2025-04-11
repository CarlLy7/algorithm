/**
 * @description:
 * @author: carl
 * @date: 2025.04.11
 * @Since: 1.0
 */

public class day20250411Solution {

    // 70 爬楼梯
    int[] memo;

    public int climbStairs(int n) {
        memo = new int[n + 1];
        return dp(n);
    }

    private int dp(int n) {
        if (n <= 2) {
            return n;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = dp(n - 1) + dp(n - 2);
        return memo[n];
    }

    // 72 编辑距离
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // dp[i][j] 表示将 word1 的前 i 个字符转换为 word2 的前 j 个字符所需的最小操作数
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    // 73 矩阵置零
    boolean[][] used;

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (used[i][j]) {
                    continue;
                }
                if (matrix[i][j] == 0) {
                    makeZero(matrix, i, j);
                }
            }
        }
    }

    private void makeZero(int[][] matrix, int i, int j) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int col = 0; col < n; col++) {
            if (matrix[i][col] == 0) {
                continue;
            }
            matrix[i][col] = 0;
            used[i][col] = true;
        }
        for (int row = 0; row < m; row++) {
            if (matrix[row][j] == 0) {
                continue;
            }
            matrix[row][j] = 0;
            used[row][j] = true;
        }
    }
}
