package dp;

/**
 * @description: 0-1背包问题
 * @author: lyq
 * @createDate: 10/4/2023
 * @version: 1.0
 */
public class ZeroOnePackage {
    public int getMaxValue(int N, int W, int[] weights, int[] values) {
        //定义一个二维的dp数组，二维dp【i】【w】数组表示：对于前i个物品，当前背包的容量为w时的最大价值
        int[][] dp = new int[N + 1][W + 1];
        //base case:当没有物品，或者当前背包没有空间了的话，最大价值就是0了
        for (int w = 0; w <= W; w++) {
            dp[0][w] = 0;
        }
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 0;
        }
        //状态转移方程
        //对于一个物品只有两种选择，第一种就是放进去，第二种就是不放进去
        for (int i = 1; i <=N ; i++) {
            for (int w = 1; w <=W ; w++) {
                //如果此时背包中的容量无法放下这个物品了，那么只能将这个物品不放进被背包中了
                if(w-weights[i-1]<0){
                    dp[i][w]=dp[i-1][w];
                }else{
                    //如果可以放进去的话，可会面临两种选择，要么放，要么不放
                    dp[i][w]=Math.max(dp[i-1][w],//不放
                            //放的话要将总容量减少，然后在加上这个物品的价值，因为我们右移了以为，所以当前的数据要减一
                                    dp[i-1][w-weights[i-1]]+values[i-1]);
                }
            }
        }
        return dp[N][W];
    }
}
