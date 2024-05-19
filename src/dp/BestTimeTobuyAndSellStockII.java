package dp;

/**
 * @description: 股票买卖II https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 * @author: lyq
 * @createDate: 16/4/2023
 * @version: 1.0
 */
public class BestTimeTobuyAndSellStockII {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int[][] dp=new int[n][2];
        for (int i = 0; i < n; i++) {
            //base case:第一天
            if(i-1==-1){
                dp[i][0]=0;
                dp[i][1]=-prices[i];
            }else{
                dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
                dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
            }
        }
        return dp[n-1][0];
    }
}
