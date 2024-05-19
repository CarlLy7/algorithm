package dp;

import java.util.Arrays;

/**
 * @description: 打家劫舍I https://leetcode.cn/problems/house-robber/
 * @author: lyq
 * @createDate: 16/4/2023
 * @version: 1.0
 */
public class HourseRobberI {
    //使用备忘录来消除重叠子问题
//    int[] memo;
//    public int rob(int[] nums) {
//        //动态规划要明确两点，第一就是状态，第二就是选择
//        //在这个问题中，状态就是你此时到了哪个房子面前，选择就是抢或者不抢
//        int n=nums.length;
//        memo=new int[n];
//        Arrays.fill(memo,-1);
//        return dp(nums,0);
//    }
//
//    //声明一下dp函数的定义：从start索引的房子开始抢劫一直到最后可以抢到的最大的金额为dp(nums,start)
//    private int dp(int[] nums, int start) {
//        //base case:当到达最后一个房子的时候，说明已经抢完了，返回0
//        if(start>=nums.length){
//            return 0;
//        }
//        if(memo[start]!=-1){
//            return memo[start];
//        }
//        int res=Math.max(dp(nums,start+1),//不抢
//                             nums[start]+dp(nums,start+2)//抢
//                );
//        memo[start]=res;
//        return res;
//    }
    //==================================
    //下面来写对应的自底向上的动态规划算法
//    public int rob(int[] nums) {
//        int n=nums.length;
//        int[] dp=new int[n+2];
//        for (int i = n-1; i >=0 ; i--) {
//            dp[i]=Math.max(dp[i+1],nums[i]+dp[i+2]);
//        }
//        return dp[0];
//    }
    //===========================
    //下面对自底向上的动态规划算法进行一个空间的压缩
    public int rob(int[] nums) {
        //发现dp[i]的状态之和dp[i+1]、dp[i+2]有关，所以可以对空间在进行一个压缩
        int n=nums.length;
        int dp_i=0;
        int dp_i_1=0;
        int dp_i_2=0;
        for (int i = n-1; i >=0 ; i--) {
            dp_i=Math.max(dp_i_1,nums[i]+dp_i_2);
            dp_i_2=dp_i_1;
            dp_i_1=dp_i;
        }
        return dp_i;
    }
}
