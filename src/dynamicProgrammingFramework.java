import java.util.Arrays;

/**
 * @description: 动态规划问题框架
 * @author: lyq
 * @createDate: 17/5/2023
 * @version: 1.0
 */
public class dynamicProgrammingFramework {

    //斐波那契数
    public int fib(int n) {
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        //dp数组的定义：前n个数之和
        int[] dp=new int[n+1];
        dp[2]=1;
        for (int i = 3; i <=n ; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    //零钱兑换
//    int[] memo;
//    public int coinChange(int[] coins, int amount) {
//        memo=new int[amount+1];
//        Arrays.fill(memo,-999);
//        return dp(coins,amount);
//    }
//
//    //dp函数的定义：当凑amount数量的金额时，需要的最小硬币数量
//    private int dp(int[] coins, int amount) {
//        if(amount<0){
//            return -1;
//        }
//        if(amount==0){
//            return 0;
//        }
//        if(memo[amount]!=-999){
//            return memo[amount];
//        }
//        int res=Integer.MAX_VALUE;
//        for (int coin : coins) {
//            int subProblem = dp(coins, amount - coin);
//            if(subProblem==-1){
//                continue;
//            }
//            res=Math.min(res,subProblem+1);
//        }
//        memo[amount]=res==Integer.MAX_VALUE?-1:res;
//        return memo[amount];
//    }


    public int coinChange(int[] coins, int amount) {
        //dp数组的定义：让我们的金额为amount的时候，需要的最小的硬币数量为dp[amount]
        int[] dp=new int[amount+1];
        Arrays.fill(dp,amount+1);
        //base case
        dp[0]=0;
        for (int i = 1; i <=amount ; i++) {
            for (int coin : coins) {
                if(i-coin<0){
                    continue;
                }
                dp[i]=Math.min(dp[i],dp[i-coin]+1);
            }
        }
        return (dp[amount]==amount+1)?-1:dp[amount];
    }
}
