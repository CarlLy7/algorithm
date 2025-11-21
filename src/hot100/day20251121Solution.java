package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.21
 * @Since: 1.0
 */

public class day20251121Solution {
    // [1026] 节点与其祖先之间的最大差值
    // int res = 0;
    //
    // public int maxAncestorDiff(TreeNode root) {
    // getMinMax(root);
    // return res;
    // }
    //
    // /**
    // * 给定一个二叉树的根节点，返回这个二叉树的最大值和最小值
    // *
    // * @param root
    // * @return
    // */
    // private int[] getMinMax(TreeNode root) {
    // if (root == null) {
    // return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};
    // }
    // int[] leftMinMax = getMinMax(root.left);
    // int[] rightMinMax = getMinMax(root.right);
    // int rootMin = Math.min(root.val, Math.min(leftMinMax[0], rightMinMax[0]));
    // int rootMax = Math.max(root.val, Math.max(leftMinMax[1], rightMinMax[1]));
    // res = Math.max(res, Math.max(root.val - rootMin, rootMax - root.val));
    // return new int[] {rootMin, rootMax};
    // }

    // [1372] 二叉树中的最长交错路径
    // int res = 0;
    //
    // public int longestZigZag(TreeNode root) {
    // getPathLen(root);
    // return res;
    // }
    //
    // /**
    // * 给定一个二叉树，返回一个数组，第一个元素：往左走的最长路径 第二个元素：往右走的最长路径
    // *
    // * @param root
    // */
    // private int[] getPathLen(TreeNode root) {
    // if (root == null) {
    // return new int[] {-1, -1};
    // }
    // int[] leftPathLen = getPathLen(root.left);
    // int[] rightPathLen = getPathLen(root.right);
    // // 如果从根节点选择往走出发，左子树肯定得往右走
    // int rootLeftLen = leftPathLen[1] + 1;
    // // 如果根节点选择往右出发，那么右子树往左走
    // int rootRightLen = rightPathLen[0] + 1;
    // res = Math.max(res, Math.max(rootLeftLen, rootRightLen));
    // return new int[] {rootLeftLen, rootRightLen};
    // }

    // [1339] 分裂二叉树的最大乘积
    long res = 0;
    // 二叉树和
    int treeSum = 0;

    public int maxProduct(TreeNode root) {
        treeSum = getPathSum(root);
        getPathSum(root);
        return (int)(res % 1000000007);
    }

    /**
     * 给定一个二叉树,计算和
     * 
     * @param root
     * @return
     */
    private int getPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = getPathSum(root.left);
        int rightSum = getPathSum(root.right);
        int sum = root.val + leftSum + rightSum;
        res = Math.max(res, (long) sum * (treeSum - sum));
        return sum;
    }

    // *****************
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
