import java.util.HashSet;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.21
 * @Since: 1.0
 */

public class day20250421Solution {
    // 121 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        // base case
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(-prices[i], dp[i - 1][1]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }

    // 124 二叉树中的最大路径和
    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        oneSideMax(root);
        return ans;
    }

    /**
     * 计算root节点为根节点的单边最大路径和
     * 
     * @param root
     */
    private int oneSideMax(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左子树最大单边路径和
        int leftSideMax = Math.max(0, oneSideMax(root.left));
        // 右子树最大单边路径和
        int rightSideMax = Math.max(0, oneSideMax(root.right));
        ans = Math.max(ans, leftSideMax + rightSideMax + root.val);
        return Math.max(leftSideMax, rightSideMax) + root.val;
    }

    // 128 最长连续序列
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;
        for (int num : set) {
            // 不是第一个数
            if (set.contains(num - 1)) {
                continue;
            }
            int curNum = num;
            int curLen = 1;
            while (set.contains(curNum + 1)) {
                curNum += 1;
                curLen += 1;
            }
            res = Math.max(res, curLen);
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
