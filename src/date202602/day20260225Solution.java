package date202602;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.25
 * @Since: 1.0
 */

public class day20260225Solution {
    // [121] 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            // base case
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 因为只有一次交易机会，所以dp[i-1][0]一定是没有收益的，所以这里直接是-price[i]
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }

    // [122] 买卖股票的最佳时机 II
    public int maxProfitII(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 因为不限制买卖次数，所以dp[i-1][0]可能已经有收益了
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    // [309] 买卖股票的最佳时机含冷冻期
    public int maxProfitIII(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }

            // base case i=2
            if (i - 2 == -1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                // 因为有冷冻期，所以dp[i-1][0]一定是不能卖的,所以这里就是-prices[i]
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
            }
        }
        return dp[n - 1][0];
    }
}
