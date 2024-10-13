package day20241013;

import java.util.Arrays;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-10-13 21:49
 * @version: 1.0
 */
public class Solution {
    /**
     * 最长递增子序列
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        // dp[i]：以nums[i]为结尾的数组的最长递增子序列的长度
        int[] dp = new int[nums.length];
        // base case
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
