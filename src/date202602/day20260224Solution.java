package date202602;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.24
 * @Since: 1.0
 */

public class day20260224Solution {
    // [198] 打家劫舍
    // int[] memo;
    //
    // public int rob(int[] nums) {
    // int n = nums.length;
    // memo = new int[n];
    // Arrays.fill(memo, -1);
    // return dp(nums, 0);
    // }
    //
    // private int dp(int[] nums, int satrt) {
    // if (satrt >= nums.length) {
    // return 0;
    // }
    // if (memo[satrt] != -1) {
    // return memo[satrt];
    // }
    // int res = Math.max(
    // // 不偷
    // dp(nums, satrt + 1),
    // // 偷
    // dp(nums, satrt + 2) + nums[satrt]);
    // memo[satrt] = res;
    // return res;
    // }

    // [213] 打家劫舍 II
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] memo1 = new int[n];
        Arrays.fill(memo1, -1);
        int[] memo2 = new int[n];
        Arrays.fill(memo2, -1);
        return Math.max(
            // 偷第一个位置，不偷最后一个位置
            dp(nums, 0, n - 2, memo1),
            // 偷最后一个位置，不偷第一个位置
            dp(nums, 1, n - 1, memo2));
    }

    private int dp(int[] nums, int start, int end, int[] memo) {
        if (start > end) {
            return 0;
        }
        if (memo[start] != -1) {
            return memo[start];
        }
        int res = Math.max(dp(nums, start + 1, end, memo), dp(nums, start + 2, end, memo) + nums[start]);
        memo[start] = res;
        return res;
    }

    // [337] 打家劫舍 III
    Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        // 偷
        int do_it = root.val + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
            + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
        // 不偷
        int not_doIt = rob(root.left) + rob(root.right);
        int res = Math.max(do_it, not_doIt);
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
