package dp;

/**
 * @description: 最佳买卖股票时机含冷冻期 https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * @author: lyq
 * @createDate: 16/4/2023
 * @version: 1.0
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            if (i - 2 == -1) {
                dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
                dp[i][1]=Math.max(dp[i-1][1],-prices[i]);
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //因为卖了和再次买之间至少隔一天，所有不可能是前一天卖了，今天买，只能是前两天卖了，今天买
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);

        }
        return dp[n - 1][0];
    }
}
