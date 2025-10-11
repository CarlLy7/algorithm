package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.11
 * @Since: 1.0
 */

public class day20251011Solution {
    // [486] 预测赢家
    private class Pair {
        // 先手的得分
        int first;
        // 后手的得分
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        // dp[i][j]:从【i...j】范围，dp[i][j].first先手最大得分，dp[i][j].second后手最大得分
        Pair[][] dp = new Pair[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = new Pair(0, 0);
            }
        }
        // base case:只有一个元素的时候，肯定是先手得分，后手不得分
        for (int i = 0; i < n; i++) {
            dp[i][i].first = nums[i];
            dp[i][i].second = 0;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 先手选择左边的得分
                int left = nums[i] + dp[i + 1][j].second;
                // 先手选择右边的得分
                int right = nums[j] + dp[i][j - 1].second;
                if (left >= right) {
                    dp[i][j].first = left;
                    dp[i][j].second = dp[i + 1][j].first;
                } else {
                    dp[i][j].first = right;
                    dp[i][j].second = dp[i][j - 1].first;
                }
            }
        }
        Pair res = dp[0][n - 1];
        return res.first - res.second >= 0;
    }

    // [877] 石子游戏
    public boolean stoneGame(int[] piles) {
        return true;
    }
}
