package day20240715;

import java.util.Arrays;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-07-15 10:09
 * @version: 1.0
 */
public class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int end = 0, farest = 0;
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            farest = Math.max(farest, i + nums[i]);
            if (i == end) {
                res++;
                end = farest;
            }
        }
        return res;
    }

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        // base case
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int maxProduct(int[] nums) {
        // dp1[i]：以nums[i]结尾的最小的数
        int[] dp1 = new int[nums.length];
        //dp2[i]: 以nunms[i]结尾的最大的数
        int[] dp2 = new int[nums.length];
        dp1[0] = nums[0];
        dp2[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp1[i] = Math.min(dp1[i - 1] * nums[i], Math.min(dp2[i - 1] * nums[i], nums[i]));
            dp2[i] = Math.max(dp1[i - 1] * nums[i], Math.max(dp2[i - 1] * nums[i], nums[i]));
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, dp2[i]);
        }
        return res;
    }
}
