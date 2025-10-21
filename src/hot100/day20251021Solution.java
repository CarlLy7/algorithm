package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.21
 * @Since: 1.0
 */

public class day20251021Solution {
    // [235] 二叉搜索树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 保证p的值小于q的值
        if (p.val > q.val) {
            return lowestCommonAncestor(root, q, p);
        }
        if (p.val <= root.val && root.val <= q.val) {
            return root;
        }
        if (root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < p.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return null;
    }

    // [236] 二叉树的最近公共祖先
    // public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // if (root == null) {
    // return null;
    // }
    // if (root == p || root == q) {
    // return root;
    // }
    // TreeNode leftRoot = lowestCommonAncestor(root.left, p, q);
    // TreeNode rightRoot = lowestCommonAncestor(root.right, p, q);
    // if (leftRoot != null && rightRoot != null) {
    // return root;
    // }
    // return leftRoot != null ? leftRoot : rightRoot;
    // }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
