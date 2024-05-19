package dp;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: lyq
 * @createDate: 11/4/2023
 * @version: 1.0
 */
public class ChangeExchangeII {
//    public  int change(int amount, int[] coins) {
//        int n=coins.length;
//        //dp数组的定义为： dp[i][j]表示对于前i个物品，当前容量为j的情况下可以有dp[i][j]种方法可以恰好将背包装满
//        int[][] dp=new int[n+1][amount+1];
//        //base case： 当容量为0的时候不放物品，所以方法只有一种，当物品为0的时候肯定无法将背包装满所以方法为0
//        for (int i = 0; i <=n; i++) {
//            dp[i][0]=1;
//        }
//        for (int i = 1; i <=n ; i++) {
//            for (int j = 1; j <=amount ; j++) {
//                if(j-coins[i-1]>=0){
//                    //容量大于物品的重量，可以放物品到背包中
//                    //因为要求的是一共有多少种方法，所以这里应该是放和不放的和
//                    //而且要注意，因为物品可以重复放入，所以放进去的话，是dp[i][j-coins[i-1]] 再次去包括i的前面物品中去递归就好了
//                    dp[i][j]=dp[i-1][j]+dp[i][j-coins[i-1]];
//                }else{
//                    //如果容量已经无法放下物品的话就不放了
//                    dp[i][j]=dp[i-1][j];
//                }
//            }
//        }
//        return dp[n][amount];
//    }

    //======================================
    //压缩空间的代码
    public int change(int amount, int[] coins) {
        int[] dp=new int[amount+1];
        //base case
        dp[0]=1;
        //dp[i][j]状态只和[i]、[i-1]有关 所以从上到下从左到右遍历
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <=amount ; j++) {
                if(j-coins[i]>=0) {
                    //容量可以放进物品去，可以选择放或者不放，最后结果应该为两者的和
                    dp[j] = dp[j] + dp[j - coins[i]];
                }else{
                    //容量无法放进去物品了
                    dp[j]=dp[j];
                }
            }
        }
        return dp[amount];
    }

}
