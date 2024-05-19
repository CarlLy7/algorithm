package dp;

/**
 * @description: 买卖股票的最佳时机 III https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
 * @author: lyq
 * @createDate: 17/4/2023
 * @version: 1.0
 */
public class BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        //天数 最大交易次数 持有状态
        int[][][] dp = new int[n][3][2];
        for (int i = 0; i < n; i++) {
            dp[i][0][0]=0;
            dp[i][0][1]=Integer.MIN_VALUE;
        }
        for (int i = 0; i < n; i++) {
            for (int k = 2; k >= 1; k--) {
                if (i - 1 == -1) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                } else {
                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                    dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
                }
            }
        }
        return dp[n - 1][2][0];
    }
}
