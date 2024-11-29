import java.util.Arrays;

/**
 * @author: luanyingqi
 * @date: 2024/11/29
 */

public class day20241129Solution {
    //509
    public int fib(int n) {
        int[] memo = new int[n + 1];
        return dp(memo, n);
    }

    private int dp(int[] memo, int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = dp(memo, n - 1) + dp(memo, n - 2);
        return memo[n];
    }

    //322
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        // base case
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    //53
//    public int maxSubArray(int[] nums) {
//        int n = nums.length;
//        int[] preSum = new int[n + 1];
//        preSum[0] = 0;
//        for (int i = 1; i <= n; i++) {
//            preSum[i] = preSum[i - 1] + nums[i - 1];
//        }
//        int res = Integer.MIN_VALUE;
//        int minVal = Integer.MAX_VALUE;
//        for (int i = 0; i < n; i++) {
//            minVal = Math.min(minVal, preSum[i]);
//            res=Math.max(res,preSum[i+1]-minVal);
//        }
//        return res;
//    }

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        // base case
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
