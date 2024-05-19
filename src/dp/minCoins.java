package dp;

import java.util.Arrays;

/**
 * @description: 零钱兑换 https://leetcode.cn/problems/coin-change/
 * @author: lyq
 * @createDate: 24/3/2023
 * @version: 1.0
 */
public class minCoins {
    //    int[] memo;
//    public int coinChange(int[] coins, int amount) {
//        //好像是摸到了一点门道：那就是我们这个是最值问题提，所以我们就可以考虑使用动态规划问题来进行解答
//        //我们要使用和斐波那契数列一样的解法，我们可以去找他的子问题，比如coins = [1, 2, 5], amount = 11
//        //我们要凑一个11块钱，我们可以去找子问题的答案，比如我们如果知道凑11-1=10块的硬币数的话我们最后加一就可以了，类似的我们也可以求出凑11-2=9块钱的
//        //硬币个数，11-5=6块钱的硬币个数，这就和青蛙跳台阶问题是一样的了
//        //而且这个问题要注意使用备忘录来去重
//        memo=new int[amount+1];
//        Arrays.fill(memo,-999);
//        int res=dp(coins,amount);
//        return res;
//    }
//
//    private int dp(int[] coins, int amount) {
//        if(amount==0)
//            return 0;
//        if(amount<0)
//            return -1;
//        if(memo[amount]!=-999){
//            return memo[amount];
//        }
//        int maxCoins=Integer.MAX_VALUE;
//        //下面就是去找子问题的答案，其实就是递归的升级
//        for (int coin : coins) {
//            int coinNum = dp(coins, amount - coin);
//            if(coinNum==-1)
//                continue;
//            maxCoins=Math.min(maxCoins,coinNum+1);
//        }
//        memo[amount]=maxCoins==Integer.MAX_VALUE?-1:maxCoins;
//        return memo[amount];
//    }

    //为了消除重叠子问题，所以我们使用备忘录
//    int[] memo;
//    public int coinChange(int[] coins, int amount) {
//        memo=new int[amount+1];
//        Arrays.fill(memo,-666);
//        return dp(coins,amount);
//    }
//
//    private int dp(int[] coins, int amount) {
//        if(amount<0){
//            return -1;
//        }
//        if(amount==0){
//            return 0;
//        }
//        if(memo[amount]!=-666){
//            return memo[amount];
//        }
//        int min=Integer.MAX_VALUE;
//        for (int coin : coins) {
//            int dp = dp(coins, amount - coin);
//            if(dp==-1){
//                continue;
//            }
//            min=Math.min(min,dp+1);
//        }
//        //更新memo
//        memo[amount]=min==Integer.MAX_VALUE?-1:min;
//        return memo[amount];
//    }
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        //base case
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
