package day20240613;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-13 10:39
 * @version: 1.0
 */
public class Solution {
    int knapsack(int W, int[] wt, int[] val) {
        int n=wt.length;
        int[][] dp=new int[n+1][W+1];
        // base case
        for (int i = 1; i <=n ; i++) {
            for (int j = 1; j <=W ; j++) {
                if (j-wt[i-1]<0){
                    return dp[i-1][j];
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],val[i-1]+dp[i-1][j-wt[i-1]]);
                }
            }
        }
        return dp[n][W];
    }

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum%2!=0){
            return false;
        }
        sum=sum/2;
        int n=nums.length;
        boolean[][] dp=new boolean[n+1][sum+1];
        // base case
        for (int i = 0; i < n+1; i++) {
            dp[i][0]=true;
        }
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <=sum ; j++) {
                // 如果背包容量不足了，就只能不放进去了
                if (j-nums[i-1]<0){
                    dp[i][j]=dp[i-1][j];
                }else{
                    // 不放或者放进去，其中放进去取决于前一个是不是恰好装满，如果恰好装满那么肯定也能装满，反之不能
                    dp[i][j]=dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[n][sum];
    }

    public int change(int amount, int[] coins) {
        int n=coins.length;
        int[][] dp=new int[n+1][amount+1];
        // base case:金额为0的时候，什么都不做就是唯一的一种选择
        for (int i = 0; i <=n ; i++) {
            dp[i][0]=1;
        }
        for (int i = 1; i <=n ; i++) {
            for (int j = 1; j <=amount ; j++) {
                // 不选择coins[i-1]这种面值,那么结果就是之前的结果
                // 选择coins[i-1]这种面值,dp[i][j-coins[i-1]] 也不难理解，如果你决定使用这个面值的硬币，那么就应该关注如何凑出金额 j - coins[i-1]
                //比如说，你想用面值为 2 的硬币凑出金额 5，那么如果你知道了凑出金额 3 的方法，再加上一枚面额为 2 的硬币，不就可以凑出 5 了嘛
                if (j-coins[i-1]>=0){
                    dp[i][j]=dp[i-1][j]+dp[i][j-coins[i-1]];
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[n][amount];
    }

    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int n=nums.size();
        int[][] dp=new int[n+1][target+1];
        // base case
        Arrays.fill(dp[0],Integer.MIN_VALUE);
        dp[0][0]=0;
        for (int i = 1; i <=n ; i++) {
            for (int j = 1; j <=target ; j++) {
                // 不选择，选择
                if (j-nums.get(i-1)>=0){
                    dp[i][j]=Math.max(dp[i-1][j],1+dp[i-1][j-nums.get(i-1)]);
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[n][target]<0?-1:dp[n][target];
    }
}
