package dp;

import java.util.Arrays;

/**
 * @description:
 * @author: lyq
 * @createDate: 27/3/2023
 * @version: 1.0
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int res=0;
        //dp[i]代表着以nums[i]结尾的数组的递增子序列的个数
        int[] dp=new int[nums.length];
        Arrays.fill(dp,1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            res=Math.max(res,dp[i]);
        }
        return res;
    }
}
