import java.util.Arrays;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.23
 * @Since: 1.0
 */

public class day20250723Solution {
    // [LCR 176] 判断是否为平衡二叉树
    boolean isBalance = true;

    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return isBalance;
    }

    /**
     * 求树的最大深度
     * 
     * @param root
     * @return
     */
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 分解问题
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            isBalance = false;
        }
        return 1 + Math.max(leftDepth, rightDepth);
    }

    // [2611] 老鼠和奶酪
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        // diff[i][0]:reward1[i]-reward2[i]
        // diff[ii][1]: reward1选择的索引下标
        int[][] diff = new int[n][2];
        for (int i = 0; i < n; i++) {
            diff[i][0] = reward1[i] - reward2[i];
            diff[i][1] = i;
        }
        // 按照差值从大到小排序
        Arrays.sort(diff, (a, b) -> {
            return b[0] - a[0];
        });
        int res = 0;
        // 前k个优先选择差值最大的
        for (int i = 0; i < k; i++) {
            res += reward1[diff[i][1]];
        }
        for (int i = k; i < n; i++) {
            res += reward2[diff[i][1]];
        }
        return res;
    }

    // [面试题 04.05] 合法二叉搜索树

    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    /**
     * 判断是否是合法二叉搜索树
     * 
     * @param root
     * @param min
     * @param max
     * @return
     */
    private boolean isValid(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if ((min != null && root.val <= min.val) || (max != null && max.val <= root.val)) {
            return false;
        }
        return isValid(root.left, min, root) && isValid(root.right, root, max);
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
