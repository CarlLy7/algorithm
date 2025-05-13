import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.05.13
 * @Since: 1.0
 */

public class day20250513Solution {
    // 543 二叉树的直径
    int res = 0;

    private int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return res;
    }

    /**
     * 计算当前节点的左右子树的最大深度
     * 
     * @param root
     */
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        // 后序位置，更新最大直径
        res = Math.max(res, leftDepth + rightDepth);
        return 1 + Math.max(leftDepth, rightDepth);
    }

    // 560 和为 K 的子数组
    private int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        // 前缀和：前缀和出现的次数
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(preSum[0], 1);
        int res = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
            // 如果前面的数有和为k的
            int need = preSum[i] - k;
            res += countMap.getOrDefault(need, 0);
            if (!countMap.containsKey(preSum[i])) {
                countMap.put(preSum[i], 1);
            } else {
                countMap.put(preSum[i], countMap.get(preSum[i]) + 1);
            }
        }
        return res;
    }

    // 739 每日温度
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            // 存放下一个更大元素索引，所以需要保证从大到小
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
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
