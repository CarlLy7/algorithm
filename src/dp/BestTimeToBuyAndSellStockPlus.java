package dp;

/**
 * @description: 自制的股票买卖问题
 * 输入股票价格数组 prices，你最多进行 max_k 次交易，每次交易需要额外消耗 fee 的手续费，
 * 而且每次交易之后需要经过 cooldown 天的冷冻期才能进行下一次交易，请你计算并返回可以获得的最大利润。
 * @author: lyq
 * @createDate: 17/4/2023
 * @version: 1.0
 */
public class BestTimeToBuyAndSellStockPlus {
    int maxProfit_all_in_one(int max_k, int[] prices, int cooldown, int fee){
        int n= prices.length;
        int[][][] dp=new int[n][max_k+1][2];
        //交易次数的base case
        for (int i = 0; i < n; i++) {
            dp[i][0][0]=0;
            dp[i][0][1]=Integer.MIN_VALUE;
        }
        for (int i = 0; i <n; i++) {
            for (int k = max_k; k >=1 ; k--) {
                //针对天数的base case
                if(i-1==-1){
                    dp[i][k][0]=0;
                    dp[i][k][1]=-prices[i]-fee;
                    continue;
                }
                if(i-cooldown-1==-1){
                    dp[i][k][0]=Math.max(dp[i-1][k][0],dp[i-1][k][1]+prices[i]);
                    dp[i][k][1]=Math.max(dp[i-1][k][1],-prices[i]-fee);
                    continue;
                }
                dp[i][k][0]=Math.max(dp[i-1][k][0],dp[i-1][k][1]+prices[i]);
                dp[i][k][1]=Math.max(dp[i-1][k][1],dp[i-cooldown-1][k-1][0]-prices[i]-fee);
            }
        }
        return dp[n-1][max_k][0];
    }
}
