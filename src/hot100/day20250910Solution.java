package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.10
 * @Since: 1.0
 */

public class day20250910Solution {
    // [108] 将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    /**
     * 在nums的【start,end】范围内构建BST，返回根节点
     * 
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }

    // [114] 二叉树展开为链表
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        // 将右子树变成一个链表
        flatten(root.left);
        // 将左子树变成一个链表
        flatten(root.right);
        // 后序位置
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    // [124] 二叉树中的最大路径和
    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 单边最大路径和
        oneSideMax(root);
        return res;
    }

    /**
     * 求单边最大路径和，并在后序位置更新一下最大路径和
     * 
     * @param root
     */
    private int oneSideMax(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSub = Math.max(0, oneSideMax(root.left));
        int rightSub = Math.max(0, oneSideMax(root.right));
        // 后序位置更新答案
        int pathMax = root.val + leftSub + rightSub;
        res = Math.max(res, pathMax);

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
