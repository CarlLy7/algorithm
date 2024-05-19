package dp;

/**
 * @description: 买卖股票的最佳时机 https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 * @author: lyq
 * @createDate: 16/4/2023
 * @version: 1.0
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        //这里省去了交易次数K这个维度，只保留了天数以及股票持有状态这两个维度
        int[][] dp=new int[n][2];
        for (int i = 0; i < n; i++) {
            if(i-1==-1){
                //base case:当天数为-1的时候，如果没有持有股票的话是0 如果持有股票了就是-price[i]
                dp[i][0]=0;//没有持有股票
                dp[i][1]=-prices[i];
                continue;
            }else{
                dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
                dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
            }
        }
        return dp[n-1][0];
    }
}
