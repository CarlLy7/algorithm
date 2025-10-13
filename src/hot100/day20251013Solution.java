package hot100;

import java.util.Arrays;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.13
 * @Since: 1.0
 */

public class day20251013Solution {
    // [474] 一和零
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 1; i <= strs.length; i++) {
            String curStr = strs[i - 1];
            int zeroCount = 0;
            int oneCount = 0;
            for (char c : curStr.toCharArray()) {
                if (c == '0') {
                    zeroCount++;
                } else {
                    oneCount++;
                }
            }
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    // 能够放下
                    if (j >= zeroCount && k >= oneCount) {
                        dp[i][j][k] = Math.max(
                            // 不放
                            dp[i - 1][j][k],
                            // 放
                            dp[i - 1][j - zeroCount][k - oneCount] + 1);
                    } else {
                        // 放不下
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    // [1262] 可被三整除的最大和
    public int maxSumDivThree(int[] nums) {
        /**
         * 定义： dp[i][0]: nums[0...i]中与3取余，余数为0的最大元素和
         */
        int[][] dp = new int[nums.length + 1][3];
        // base case
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            int cur = nums[i - 1];
            if (cur % 3 == 0) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][0] + cur);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][1] + cur);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][2] + cur);
            } else if (cur % 3 == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] + cur);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + cur);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + cur);
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + cur);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] + cur);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + cur);
            }
        }
        return dp[nums.length][0];
    }

    // [3180] 执行操作可获得的最大总奖励 I
    public int maxTotalReward(int[] rewardValues) {
        int n = rewardValues.length;
        Arrays.sort(rewardValues);
        int maxVal = rewardValues[n - 1];
        boolean[][] dp = new boolean[n + 1][maxVal * 2];
        // base case
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            int cur = rewardValues[i - 1];
            for (int x = 0; x < maxVal * 2; x++) {
                // 可以放得下
                if (x >= cur && cur > x - cur) {
                    // 放
                    boolean add = dp[i - 1][x - cur];
                    // 不放
                    boolean notAdd = dp[i - 1][x];
                    dp[i][x] = add || notAdd;
                } else {
                    // 放不下
                    dp[i][x] = dp[i - 1][x];
                }
            }
        }
        for (int i = maxVal * 2 - 1; i >= 0; i--) {
            if (dp[n][i]) {
                return i;
            }
        }
        return 0;
    }
}
