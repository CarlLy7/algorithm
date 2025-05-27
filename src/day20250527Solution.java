/**
 * @description:
 * @author: carl
 * @date: 2025.05.27
 * @Since: 1.0
 */

public class day20250527Solution {
    // [221] 最大正方形
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // dp[i][j]: 以matrix[i][j]为右下角的正方形的边长
        int[][] dp = new int[m][n];
        // base case
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] - '0';
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j] - '0';
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
            }
        }
        int maxLen = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen * maxLen;
    }

    // [329] 矩阵中的最长递增路径
    int[][] memo;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        memo = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dp(matrix, i, j));
            }
        }
        return res;
    }

    /**
     * 以matrix[i][j]为起点，递增路径最大长度
     * 
     * @param matrix
     * @param i
     * @param j
     * @return
     */
    private int dp(int[][] matrix, int i, int j) {
        // 已经算过了
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int res = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n) {
                continue;
            }
            if (matrix[x][y] > matrix[i][j]) {
                res =Math.max(res,dp(matrix,x,y)+1);
            }
        }
        memo[i][j] = res;
        return res;
    }

    // [343] 整数拆分
    int[] memo2;
    public int integerBreak(int n) {
        memo2=new int[n+1];
        return dp(n);
    }

    /**
     * 拆分n的最大乘积
     * @param n
     * @return
     */
    private int dp(int n) {
        int res=Integer.MIN_VALUE;
        if (n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }
        if (memo2[n]>0){
            return memo2[n];
        }
        for (int i = 1; i <=n ; i++) {
            res=Math.max(res,i*Math.max(dp(n-i),n-i));
        }
        memo2[n]=res;
        return res;
    }
}
