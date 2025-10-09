package hot100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.09
 * @Since: 1.0
 */

public class day20251009Solution {

    // [198] 打家劫舍
    int[] memo;

    public int rob(int[] nums) {
        int n = nums.length;
        memo = new int[n];
        Arrays.fill(memo, -1);
        return dp(nums, 0);
    }

    /**
     * 对于nums个房子，从start开始抢，最多能抢多少钱
     * 
     * @param nums
     * @param start
     * @return
     */
    private int dp(int[] nums, int start) {
        if (start >= nums.length) {
            return 0;
        }
        if (memo[start] != -1) {
            return memo[start];
        }
        memo[start] = Math.max(dp(nums, start + 1), nums[start] + dp(nums, start + 2));
        return memo[start];
    }

    // [213] 打家劫舍 II
    public int robII(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] memo1 = new int[n];
        int[] memo2 = new int[n];
        Arrays.fill(memo1, -1);
        Arrays.fill(memo2, -1);
        return Math.max(dp(nums, 0, n - 2, memo1), dp(nums, 1, n - 1, memo2));
    }

    private int dp(int[] nums, int start, int end, int[] memo) {
        int n = nums.length;
        if (start > end) {
            return 0;
        }
        if (memo[start] != -1) {
            return memo[start];
        }
        int res = 0;
        for (int i = start; i <= end; i++) {
            res = Math.max(dp(nums, start + 1, end, memo), dp(nums, start + 2, end, memo) + nums[start]);
        }
        memo[start] = res;
        return res;
    }

    // [337] 打家劫舍 III
    public int robIII(TreeNode root) {
        Map<TreeNode, Integer> memo = new HashMap<>();
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        // 抢
        int do_it = root.val + (root.left == null ? 0 : robIII(root.left.left) + robIII(root.left.right))
            + (root.right == null ? 0 : robIII(root.right.left) + robIII(root.right.right));
        // 不抢
        int do_not = robIII(root.left) + robIII(root.right);
        int res = Math.max(do_it, do_not);
        memo.put(root, res);
        return res;
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
