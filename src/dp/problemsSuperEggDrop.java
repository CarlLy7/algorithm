package dp;

import java.util.Arrays;

/**
 * @description: 鸡蛋掉落 https://leetcode.cn/problems/super-egg-drop/
 * @author: lyq
 * @createDate: 18/4/2023
 * @version: 1.0
 */
public class problemsSuperEggDrop {
    //下面写一下使用二分搜索优化的自顶向下的动态规划算法。
    //使用动态规划必须先明确状态和选择 状态：可以尝试的鸡蛋次数，楼层数  选择：从哪个楼层扔鸡蛋
    //因为是一个自顶向下的动态规划问题，这个问题中有重叠子问题，所以使用备忘录来消除重叠子问题
//    int[][] memo;
//
//    public int superEggDrop(int k, int n) {
//        //整体右移一位，从下标1开始更符合生活实际
//        memo = new int[k + 1][n + 1];
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -1);
//        }
//        return dp(k, n);
//    }
//
//    //声明一下dp函数的定义：当有k个鸡蛋，n层楼的时候确定恰好不碎的楼层的最少尝试次数为dp(k,n)
//    private int dp(int k, int n) {
//        //base case
//        //1.当我们只有一个鸡蛋的时候，只能进行线性扫描才能确定最坏情况下的恰好不碎的楼层
//        if (k == 1) {
//            return n;
//        }
//        //2.当楼层为0或者鸡蛋为0的时候，尝试的次数肯定为0
//        if (k == 0 || n == 0) {
//            return 0;
//        }
//        if(memo[k][n]!=-1){
//            return memo[k][n];
//        }
//        //状态转移方程：如果此时碎了，那么楼层在当前楼层的下面，楼层数为i-1 如果没有随楼层在当前楼层上面，楼层数为n-i
//        // 因为是最坏情况下所以碎没碎取决于尝试的次数，选择尝试次数多的那个
//        int res = Integer.MAX_VALUE;
//        int low = 1, high = n;
//        while (low <= high) {
//            //这个mid其实就是使用二分搜索快速知道的中间的尝试楼层，也就是当前仍的那个楼层
//            int mid = (low + high) / 2;
//            //鸡蛋碎的情况
//            int broke = dp(k - 1, mid - 1);
//            //鸡蛋没有碎的情况
//            int not_broke = dp(k, n - mid);
//            //因为是最坏情况下，所以我们选择碎或者没碎中尝试次数最多，也就是最大的那个
//            if (broke > not_broke) {
//                //碎的情况下尝试的次数多于不碎
//                //下次我们应该去楼下尝试
//                high = mid - 1;
//                //因为最后结果是尝试次数最少，所以取最小值
//                res = Math.min(res, broke + 1);
//            } else {
//                //不碎的情况下尝试的次数多于碎
//                //下次我们应该去楼上尝试
//                low = mid + 1;
//                res = Math.min(res, not_broke + 1);
//            }
//        }
//        memo[k][n] = res;
//        return res;
//    }

    //=====================================
    //下面换一个dp定义的思路

    public int superEggDrop(int K, int n) {
        //dp数组的定义:dp[k][m]表示有k个鸡蛋，最少尝试m次可以确定的最高的楼层是dp[k][m]
        //这里为什么大小设置为[n+1]呢。因为确定楼层N层需要的最少的次数最多也就是n次
        int[][] dp = new int[K + 1][n + 1];
        //base case
        //1.当鸡蛋为0的时候，确定的高度为0
        for (int m = 0; m < n + 1; m++) {
            dp[0][m] = 0;
        }
        //2.当尝试次数m为0的时候可以确定的楼层也为0
        for (int i = 0; i < K - 1; i++) {
            dp[i][0] = 0;
        }
        //当我们可以确定的楼层高度达到了n层，那么就结束了
        int m = 0;
        while (dp[K][m] < n) {
            m++;//尝试的次数+1
            for (int k = 1; k <= K; k++) {
                //当前可以确定的最高高度=楼下的高度+楼上的高度+1
                //dp[k][m-1]代表没碎，所以确定楼下的高度
                //dp[k-1][m-1]代表碎了，确定楼上的高度
                //加1是当前的楼层
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
            }
        }
        return m;
    }
}
