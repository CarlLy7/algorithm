package dp;

/**
 * @description: 买卖股票的最佳时机含手续费 https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * @author: lyq
 * @createDate: 17/4/2023
 * @version: 1.0
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        int n=prices.length;
        //天数 状态
        int[][] dp=new int[n][2];
        for (int i = 0; i < n; i++) {
            if(i-1==-1){
                dp[i][0]=0;
                dp[i][1]=-prices[i]-fee;
                continue;
            }else{
                dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
                dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]-fee);
            }
        }
        return dp[n-1][0];
    }
}
