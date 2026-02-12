package date202602;

import java.util.Arrays;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.12
 * @Since: 1.0
 */

public class day20260212Solution {
    // [300] 最长递增子序列
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i]:以nums[i]结尾的子数组长度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        // base case
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }
        int ans = 0;
        for (int i : dp) {
            ans = Math.max(i, ans);
        }
        return ans;
    }

    // [53] 最大子数组和
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        // base case
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // [416] 分割等和子集
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        int n = nums.length;
        // dp[i][j]:对于前i个物品，当前容量为j的时候是否能够恰好装满
        boolean[][] dp = new boolean[n + 1][sum + 1];
        // base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        // 前i个物品
        for (int i = 1; i <= n; i++) {
            // 当前容量
            for (int j = 1; j <= sum; j++) {
                // 放的下
                if (j - nums[i - 1] >= 0) {
                    //为什么放的话状态要看dp[i-1][j-nums[i-1]]呢，因为每个物品只能选一次，所以就是[i-1]了
                    dp[i][j] = dp[i - 1][j] || dp[i-1][j - nums[i - 1]];
                } else {
                    // 放不下
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];
    }
}
