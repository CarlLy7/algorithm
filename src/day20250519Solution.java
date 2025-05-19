import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.05.19
 * @Since: 1.0
 */

public class day20250519Solution {
    // 71 简化路径
    public String simplifyPath(String path) {
        String[] split = path.split("/");
        // 用来进行..返回上一级目录
        Stack<String> stack = new Stack<>();
        for (String s : split) {
            if (s.isEmpty() || s.equals(".")) {
                continue;
            }
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }
            stack.push(s);
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        return res.isEmpty() ? "/" : res;
    }

    // LCR188 买卖芯片的最佳时机
    public int bestTiming(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        // dp[i][0]:第i天的时候，如果不持有股票的最大利润
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            // base case 第一天
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }

    // 173 二叉搜索树迭代器

    private class BSTIterator {
        // 利用栈来模拟中序遍历
        Stack<TreeNode> stack = new Stack<>();

        /**
         * 将左子节点全部放入栈中
         * 
         * @param p
         */
        public void pushLeft(TreeNode p) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
        }

        public BSTIterator(TreeNode root) {
            pushLeft(root);
        }

        public int next() {
            TreeNode cur = stack.pop();
            pushLeft(cur.right);
            return cur.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
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
