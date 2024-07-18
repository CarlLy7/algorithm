package day20240718;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-07-18 15:31
 * @version: 1.0
 */
public class Solution {
//    public int rob(int[] nums) {
//        int n = nums.length;
//        int[] dp = new int[n + 2];
//        for (int i = n - 1; i >= 0; i--) {
//            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
//        }
//        return dp[0];
//    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
    }

    private int robRange(int[] nums, int start, int end) {
        int dp_i = 0, dp_i_1 = 0, dp_i_2 = 0;
        for (int i = end; i >= start; i--) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int rob(TreeNode root) {
        // res[0]：不抢的最大金额 res[1]抢的最大金额
        int[] res = dp(root);
        return Math.max(res[0], res[1]);
    }

    private int[] dp(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int res = 0;
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        // 如果抢root的话，下一层是不可以抢的
        int do_it = root.val + left[0] + right[0];
        // 不抢的话，下一层可抢可不抢，选最大值
        int nodo = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{nodo, do_it};
    }

//    public int maxProfit(int[] prices) {
//        int n = prices.length;
//        int[][] dp = new int[n][2];
//        //base case
//        dp[0][0] = 0;
//        dp[0][1] = -prices[0];
//        for (int i = 1; i < n; i++) {
//            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
//            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
//        }
//        return dp[n - 1][0];
//    }

//    public int maxProfit(int[] prices) {
//        int n = prices.length;
//        int[][] dp = new int[n][2];
//        dp[0][0] = 0;
//        dp[0][1] = -prices[0];
//        for (int i = 1; i < n; i++) {
//            if (i - 2 < 0) {
//                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
//                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
//                continue;
//            }
//            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
//            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
//        }
//        return dp[n - 1][0];
//    }

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0] - fee;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }
        return dp[n - 1][0];
    }

//    public int maxProfit(int[] prices) {
//        int n = prices.length;
//        int[][][] dp = new int[n][2][2];
//        for (int i = 0; i < n; i++) {
//            for (int j = 2; j >= 1; j--) {
//                if (i - 1 == -1) {
//                    // 处理 base case
//                    dp[i][j][0] = 0;
//                    dp[i][j][1] = -prices[i];
//                    continue;
//                }
//                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
//                dp[i][j][1] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][0] - prices[i]);
//            }
//        }
//        return dp[n - 1][2][0];
//    }

    public int maxProfit(int k, int[] prices) {
        if (prices.length <= 0) {
            return 0;
        }
        int n = prices.length;
        int[][][] dp = new int[n][k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = k; j >= 1; j--) {
                if (i - 1 == -1) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //只能进行一次交易，所以dp[i-1][0]一定是前一天没有持有股票，然后再买利润一定是赔钱得
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }
}
