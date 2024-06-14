package day20240614;

import java.util.Arrays;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-14 10:51
 * @version: 1.0
 */
public class Solution {
//    int res=0;
//    public int findTargetSumWays(int[] nums, int target) {
//        if(nums.length==0){
//            return res;
//        }
//        backTrack(nums,0,target);
//        return res;
//    }
//
//    private void backTrack(int[] nums, int start, int remainder) {
//        int n=nums.length;
//        // base case
//        if (start==n){
//            if (remainder==0){
//                res++;
//            }
//            return;
//        }
//
//            //做选择
//            remainder-=nums[start];
//            backTrack(nums,start+1,remainder);
//            remainder+=nums[start];
//
//            remainder+=nums[start];
//            backTrack(nums,start+1,remainder);
//            remainder-=nums[start];
//
//    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if (sum < Math.abs(target) || (sum + target) % 2 != 0) {
            return 0;
        }
        return subsets(nums, (sum + target) / 2);
    }

    private int subsets(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];
        // base case
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                // 放或者不放
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][target];
    }

    public long maxScore(int[] nums, int x) {
        // res只保存最大值
        long res = nums[0];
        if (nums.length == 1) {
            return res;
        }
        //dp[0]存放从前面的偶数过来的最大值，dp[1]存放从前面的奇数过来的最大值
        long[] dp = new long[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        dp[nums[0] % 2] = nums[0];
        // 因为res只保存最大值，所以不选肯定就是之前的最大值，所以下面只需要讨论选择的时候进行更新res即可
        for (int i = 1; i < nums.length; i++) {
            int zeroOrOne = nums[i] % 2;
            // 从前面的偶数过来 or 从前面的奇数过来
            long cur = Math.max(dp[zeroOrOne] + nums[i], dp[1 - zeroOrOne] - x + nums[i]);
            res = Math.max(res, cur);
            dp[zeroOrOne] = Math.max(dp[zeroOrOne], cur);
        }
        return res;
    }
}
