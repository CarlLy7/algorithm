import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:
 * @author: carl
 * @date: 2025.05.28
 * @Since: 1.0
 */

public class day20250528Solution {
    // [357] 统计各位数字都不同的数字个数
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int res = 9;
        for (int i = 1; i < n; i++) {
            res *= (10 - i);
        }
        res = res + countNumbersWithUniqueDigits(n - 1);
        return res;
    }

    // [474] 一和零
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        // dp[i][m][n]:对于字符串数组中的前i个元素，当前剩余m个0和n个1的时候可以放下的最多的子集大小
        int[][][] dp = new int[len + 1][m + 1][n + 1];

        for (int i = 1; i <= strs.length; i++) {
            String str = strs[i - 1];
            // 子串中0的数量
            int zeroCount = 0;
            // 子串中1的数量
            int oneCount = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zeroCount++;
                } else {
                    oneCount++;
                }
            }
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    // 当前剩余0和1的数量可以放下
                    if (j >= zeroCount && k >= oneCount) {
                        // 可以选择放或者不放的最大值
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeroCount][k - oneCount] + 1);
                    } else {
                        // 放不下
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        return dp[len][m][n];
    }

    // [542] 01 矩阵
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] ans = new int[m][n];
        for (int[] an : ans) {
            Arrays.fill(an, -1);
        }
        // 存放的是下标位置
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                    ans[i][j] = 0;
                }
            }
        }
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            for (int[] dir : dirs) {
                int nextX = dir[0] + x;
                int nextY = dir[1] + y;
                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && ans[nextX][nextY] == -1) {
                    queue.offer(new int[] {nextX, nextY});
                    ans[nextX][nextY] = ans[x][y] + 1;
                }
            }
        }
        return ans;
    }
}
