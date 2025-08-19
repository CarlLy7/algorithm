package hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.19
 * @Since: 1.0
 */

public class day20250819Solution {
    // [118] 杨辉三角
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 1) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> firstRow = new ArrayList<>();
            firstRow.add(1);
            res.add(firstRow);
            return res;
        }
        List<List<Integer>> generate = generate(numRows - 1);
        List<Integer> lastRow = generate.get(generate.size() - 1);
        List<Integer> newRow = new ArrayList<>();
        // 开头加1
        newRow.add(1);
        for (int i = 0; i < lastRow.size() - 1; i++) {
            newRow.add(lastRow.get(i) + lastRow.get(i + 1));
        }
        // 末尾加1
        newRow.add(1);
        generate.add(newRow);
        return generate;
    }

    // [121] 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 状态：当前第几天、是否持有股票
        int[][] dp = new int[n][2];
        // base case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 因为这里是只能交易一次，所以-prices[i]不是dp[i][0]-prices[i]
            // 因为如果是只能交易一次，你这个就是唯一的一次，你在之前就不会获利的
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }

    // [124] 二叉树中的最大路径和
    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        oneSideMax(root);
        return res;
    }

    /**
     * 计算从根节点root出发的二叉树【单边】最大路径和
     * 
     * @param root
     */
    private int oneSideMax(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 对于当前这个节点来说，路径和中一定会有自己，所以左子树和右子树中，如果是负数的我就不要了
        int leftSub = Math.max(0, oneSideMax(root.left));
        int rightSub = Math.max(0, oneSideMax(root.right));
        int sum = root.val + leftSub + rightSub;
        res = Math.max(res, sum);
        return Math.max(leftSub, rightSub) + root.val;
    }

    private class TreeNode {
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
