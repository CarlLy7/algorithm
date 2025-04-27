import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.27
 * @Since: 1.0
 */

public class day20250427Solution {
    // 169 多数元素
    public int majorityElement(int[] nums) {
        // 类比于电性
        int count = 0;
        // 目标众数
        int target = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                target = nums[i];
                count = 1;
            } else if (nums[i] == target) {
                count++;
            } else {
                count--;
            }
        }
        return target;
    }

    // 198 打家劫舍
    int[] memo;

    public int rob(int[] nums) {
        int n = nums.length;
        // 到达nums[0..i]的时候，最大金额
        memo = new int[n];
        Arrays.fill(memo, -1);
        return dp(nums, 0);
    }

    // 从nums[start]开始能偷到的最大金额
    private int dp(int[] nums, int start) {
        // base case
        if (start >= nums.length) {
            return 0;
        }
        if (memo[start] != -1) {
            return memo[start];
        }
        int res = Math.max(dp(nums, start + 1), // 不偷
            dp(nums, start + 2) + nums[start] // 偷
        );
        memo[start] = res;
        return res;
    }

    // 199 二叉树的右视图
    List<Integer> res = new ArrayList<>();
    //深度
    int depth = 0;

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return res;
        }
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        depth++;
        //每个深度应该有一个节点，所以节点数应该和深度保持一致
        if (res.size() < depth) {
            res.add(root.val);
        }
        //先递归遍历右子树
        traverse(root.right);
        traverse(root.left);
        depth--;
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
