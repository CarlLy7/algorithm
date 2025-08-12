package hot100;

import java.util.Arrays;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.12
 * @Since: 1.0
 */

public class day20250812Solution {
    // [70] 爬楼梯
    int[] memo;

    public int climbStairs(int n) {
        // dp[i]:需要爬n节台阶有几种方案
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dp(n);
    }

    private int dp(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        memo[n] = dp(n - 1) + dp(n - 2);
        return memo[n];
    }

    // [72] 编辑距离

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // dp[i][j]:word1[0..i-1]变到word2[0...j-1]需要的最小步数
        int[][] dp = new int[m + 1][n + 1];
        // base case
        // 当word2为空的时候，word1肯定要全部删除
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        // 当word1为空的时候，要想变成word2肯定需要插入
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(
                        // 删
                        dp[i - 1][j] + 1,
                        // 插入
                        dp[i][j - 1] + 1),
                        // 替换
                        dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[m][n];
    }

    // [74] 搜索二维矩阵
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (getVal(matrix, mid) == target) {
                return true;
            } else if (getVal(matrix, mid) > target) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

    /**
     * 通过一个索引获取二维数组中的数据
     * 
     * @param matrix
     * @param index
     * @return
     */
    private int getVal(int[][] matrix, int index) {
        int n = matrix[0].length;
        int x = index / n;
        int y = index % n;
        return matrix[x][y];
    }

}
