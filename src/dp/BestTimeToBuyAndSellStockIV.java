package dp;

/**
 * @description: 买卖股票的最佳时机 IV https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/
 * @author: lyq
 * @createDate: 17/4/2023
 * @version: 1.0
 */
public class BestTimeToBuyAndSellStockIV {
    public int maxProfit(int k, int[] prices) {
        int n= prices.length;
        //为什么要用k+1呢，k等于0我们不用，整体后移一位
        int[][][] dp=new int[n][k+1][2];
        //对于k这个维度的base case
        for (int i = 0; i < n; i++) {
            dp[i][0][0]=0;
            dp[i][0][1]=Integer.MIN_VALUE;
        }
        for (int i = 0; i < n; i++) {
            for (int j = k; j >=1 ; j--) {
                //对于天数这个维度的base case
                if(i-1==-1){
                    dp[i][j][0]=0;
                    dp[i][j][1]=-prices[i];
                    continue;
                }else{
                    dp[i][j][0]=Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
                    dp[i][j][1]=Math.max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i]);
                }
            }
        }
        return dp[n-1][k][0];
    }
}
