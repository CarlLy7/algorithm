package hot100;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.05
 * @Since: 1.0
 */

public class day20250905Solution {
    // [347] 前 K 个高频元素
    public int[] topKFrequent(int[] nums, int k) {
        // 值->频率
        HashMap<Integer, Integer> valToMap = new HashMap<>();
        for (int num : nums) {
            valToMap.put(num, valToMap.getOrDefault(num, 0) + 1);
        }
        // 按照出现的频率小顶堆排序
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((entryA, entryB) -> {
            return entryA.getValue().compareTo(entryB.getValue());
        });
        for (Map.Entry<Integer, Integer> entry : valToMap.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = pq.poll().getKey();
        }
        return res;
    }

    // [416] 分割等和子集
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        int n = nums.length;
        // dp[i][j]:对于前i个物品，当前背包容量为j的时候，能否装满
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                // 装不下
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 选择装或者不装
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    // [437] 路径总和 III
    //前缀和->出现的次数
    Map<Long, Integer> preSumMap = new HashMap<>();
    long pathSum, targetSum;
    int res = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        this.pathSum = 0;
        this.targetSum = targetSum;
        preSumMap.put(0L, 1);
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序位置
        pathSum += root.val;
        res += preSumMap.getOrDefault(pathSum - targetSum, 0);
        preSumMap.put(pathSum, preSumMap.getOrDefault(pathSum, 0) + 1);

        traverse(root.left);
        traverse(root.right);

        // 后序位置
        preSumMap.put(pathSum, preSumMap.get(pathSum) - 1);
        pathSum -= root.val;
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
