package date202602;

import java.util.Arrays;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.11
 * @Since: 1.0
 */

public class day20260211Solution {
    // [509] 斐波那契数
    public int fib(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dp(memo, n);
    }

    private int dp(int[] memo, int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        memo[n] = dp(memo, n - 1) + dp(memo, n - 2);
        return memo[n];
    }

    // [322] 零钱兑换
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
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    // [518] 零钱兑换 II
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // dp[i][j]:对于前i个硬币，当前容量为j的时候，有dp[i][j]种方案可以装满背包
        int[][] dp = new int[n + 1][amount + 1];
        // base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        // 可以选择的硬币
        for (int i = 1; i <= n; i++) {
            // 当前容量
            for (int j = 1; j <= amount; j++) {
                // 可以放下
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];

                }
                // 放不下了
                dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][amount];
    }
}
