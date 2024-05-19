package dp;

/**
 * @description: 打家劫舍II https://leetcode.cn/problems/house-robber-ii/
 * @author: lyq
 * @createDate: 16/4/2023
 * @version: 1.0
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        int n=nums.length;
        //此时是一个环所以无非就是三种情况
        /**
         * 1、开头个结尾都不抢
         * 2、抢开头，不抢结尾
         * 3、不抢开头，抢结尾
         * 但是对于2和3选择的比1多，所以不用考虑1了
         */
        if(nums.length==1){
            return nums[0];
        }
        return Math.max(dp(nums,0,n-2),dp(nums,1,n-1));
    }

    //dp函数的定义是：在这个【start,end】区间上可以抢的最大金额为dp(nums,start,end)
    private int dp(int[] nums, int start, int end) {
        int dp_i=0;
        int dp_i_1=0,dp_i_2=0;
        for (int i = end; i >=start ; i--) {
            dp_i=Math.max(dp_i_1,nums[i]+dp_i_2);
            dp_i_2=dp_i_1;
            dp_i_1=dp_i;
        }
        return dp_i;
    }
}
