package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.13
 * @Since: 1.0
 */

public class day20251113Solution {
    // [101] 对称二叉树
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        if (left.val != right.val)
            return false;
        return check(left.left, right.right) && check(left.right, right.left);
    }

    // [951] 翻转等价二叉树
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right))
            || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }

    // [124] 二叉树中的最大路径和
    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        oneSideMax(root);
        return res;

    }

    /**
     * 计算单边最大路径和
     * 
     * @param root
     */
    private int oneSideMax(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = Math.max(0, oneSideMax(root.left));
        int rightMax = Math.max(0, oneSideMax(root.right));
        res = Math.max(res, leftMax + rightMax + root.val);
        return Math.max(leftMax, rightMax) + root.val;
    }

    // *************************************
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
