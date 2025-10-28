package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.28
 * @Since: 1.0
 */

public class day20251028Solution {
    // [230] 二叉搜索树中第 K 小的元素
    // int res = 0;
    // int rank = 0;

    // public int kthSmallest(TreeNode root, int k) {
    // traverse(root, k);
    // return res;
    // }

    // private void traverse(TreeNode root, int k) {
    // if (root == null) {
    // return;
    // }
    // traverse(root.left, k);
    // rank++;
    // if (rank == k) {
    // res = root.val;
    // return;
    // }
    // traverse(root.right, k);
    // }

    // [538] 把二叉搜索树转换为累加树
    // int sum = 0;
    //
    // public TreeNode convertBST(TreeNode root) {
    // if (root == null) {
    // return root;
    // }
    // traverse(root);
    // return root;
    // }
    //
    // private void traverse(TreeNode root) {
    // if (root == null) {
    // return;
    // }
    // // 因为要累加右子树，所以先去把右子树遍历一遍
    // traverse(root.right);
    // sum += root.val;
    // root.val = sum;
    // traverse(root.left);
    // }

    // [98] 验证二叉搜索树
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /**
     * 通过增加参数列表，来传递给子树最大值和最小值的约束
     * @param root
     * @param min
     * @param max
     * @return
     */
    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min.val)
            return false;
        if (max != null && root.val >= max.val)
            return false;
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
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
