package hot100;

import java.util.HashMap;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.19
 * @Since: 1.0
 */

public class day20250919Solution {
    // [416] 分割等和子集
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // 可以理解为背包的容量
        sum = sum / 2;
        int n = nums.length;
        // dp[i][j]:对于前i个物品，当前背包容量为j，是否有一种方案可以装满
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                // 装不下了
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 可以选择装也可以选择不装
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    // [437] 路径总和 III
    int res = 0;
    // 记录前缀和以及出现的次数
    HashMap<Long, Integer> map = new HashMap<>();
    long target;
    long pathSum;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        map.put(0L, 1);
        this.pathSum = 0;
        this.target = targetSum;
        traverse(root);
        return res;
    }

    /**
     * 二叉树问题的【遍历思想】
     * 
     * @param root
     */
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序位置，加值
        pathSum += root.val;
        res += map.getOrDefault(pathSum - target, 0);
        map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
        traverse(root.left);
        traverse(root.right);
        // 后序位置
        map.put(pathSum, map.get(pathSum) - 1);
        pathSum -= root.val;
    }

    // [560] 和为 K 的子数组
    public int subarraySum(int[] nums, int k) {
        // 前缀和->出现的次数
        HashMap<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);
        int n = nums.length;
        // 前缀和数组要有一个索引的偏移，因为preSum[i]:代表前i个元素 preSum[0]:就代表前0个元素
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
            res += preSumMap.getOrDefault(preSum[i] - k, 0);
            preSumMap.put(preSum[i], preSumMap.getOrDefault(preSum[i], 0) + 1);
        }
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
