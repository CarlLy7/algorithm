package date202602;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.26
 * @Since: 1.0
 */

public class day20250226Solution {
    // 买卖股票的最佳时机含手续费
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i] - fee;
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }
        return dp[n - 1][0];
    }

    // [123] 买卖股票的最佳时机 III
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 前多少天可以交易次数为多少下是否持有的最大收益
        int[][][] dp = new int[n][3][2];
        // 遍历天数
        for (int i = 0; i < n; i++) {
            // 遍历交易次数
            for (int k = 2; k >= 1; k--) {
                if (i - 1 == -1) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][2][0];
    }

    // [188] 买卖股票的最佳时机 IV
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        // 前多少天可以交易次数为多少下是否持有的最大收益
        int[][][] dp = new int[n][k + 1][2];
        // 遍历天数
        for (int i = 0; i < n; i++) {
            // 遍历交易次数
            for (int j = k; j >= 1; j--) {
                if (i - 1 == -1) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }

    // [887] 鸡蛋掉落
    public int superEggDrop(int k, int n) {
        // dp[k][m]=n:k个鸡蛋最坏情况尝试m次可以确定的楼层为n
        int[][] dp = new int[k + 1][n + 1];
        int m = 0;
        // 遍历鸡蛋数
        while (dp[k][m] < n) {
            m++;
            for (int K = 1; K <= k; K++) {
                dp[K][m] = dp[K][m - 1] // 上一次鸡蛋没碎
                    + dp[K - 1][m - 1] + 1; // 上一次鸡蛋碎了，但是可以确定的楼层+1
            }

        }
        return m;
    }
}
