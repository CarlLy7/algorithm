package hot100;

import java.util.HashMap;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.09
 * @Since: 1.0
 */

public class day20250909Solution {
    // [72] 编辑距离
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // dp[i][j]:将word1的前i个字符变成word2前j个字符的最小编辑距离
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
                }
            }
        }
        return dp[m][n];
    }

    // [101] 对称二叉树
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 遍历的方法
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        if (left.val != right.val) {
            return false;
        }
        return check(left.left, right.right) && check(left.right, right.left);
    }

    // [105] 从前序与中序遍历序列构造二叉树

    // 记录中序遍历中，每个节点对应的索引位置
    HashMap<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int m = preorder.length;
        int n = inorder.length;
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return build(preorder, 0, m - 1, inorder, 0, n - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inorderStart, int inorderEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        int index = inorderMap.get(rootVal);
        int leftSize = index - inorderStart;
        TreeNode root = new TreeNode(rootVal);
        TreeNode left = build(preorder, preStart + 1, preStart + leftSize, inorder, inorderStart, index - 1);
        TreeNode right = build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inorderEnd);
        root.left = left;
        root.right = right;
        return root;
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
