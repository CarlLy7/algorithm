package day20241014;

import java.util.Arrays;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-10-14 21:03
 * @version: 1.0
 */
public class Solution {
    int[][] memo;

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        memo = new int[n][n];
        for (int[] ints : memo) {
            Arrays.fill(ints, 66666);
        }
        // base case
        for (int col = 0; col < n; col++) {
            memo[0][col] = matrix[0][col];
        }
        int ans = Integer.MAX_VALUE;
        for (int col = 0; col < n; col++) {
            ans = Math.min(ans, dp(matrix, n - 1, col));
        }
        return ans;
    }

    // dp(matrix,1,2):从第一行到达(1,2)这个位置得最小下降路径
    private int dp(int[][] matrix, int row, int col) {
        int n = matrix.length;
        if (row < 0 || col < 0 || row >= n || col >= n) {
            return 66666;
        }
        if (memo[row][col] != 66666) {
            return memo[row][col];
        }
        memo[row][col] = matrix[row][col] + min(dp(matrix, row - 1, col - 1),
                dp(matrix, row - 1, col),
                dp(matrix, row - 1, col + 1));
        return memo[row][col];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
