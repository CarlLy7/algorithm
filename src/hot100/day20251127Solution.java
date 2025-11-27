package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.27
 * @Since: 1.0
 */

public class day20251127Solution {
    // [236] 二叉树的最近公共祖先
    // public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // if (root == null) {
    // return null;
    // }
    // if (root == p || root == q) {
    // return root;
    // }
    // // 分解问题，去左右子树中判断是否存在给定两个节点的最近公共祖先
    // TreeNode leftSub = lowestCommonAncestor(root.left, p, q);
    // TreeNode rightSub = lowestCommonAncestor(root.right, p, q);
    // if (leftSub != null && rightSub != null) {
    // return root;
    // }
    // return leftSub == null ? rightSub : leftSub;
    // }

    // [235] 二叉搜索树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        // 保证p小于q
        if (p.val > q.val) {
            return lowestCommonAncestor(root, q, p);
        }
        // 如果当前根节点位于两个节点之间
        if (p.val <= root.val && root.val <= q.val) {
            return root;
        }
        if (root.val < p.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return lowestCommonAncestor(root.left, p, q);
        }
    }

    // [222] 完全二叉树的节点个数
    public int countNodes(TreeNode root) {
        // Queue<TreeNode> queue = new LinkedList<>();
        // int res = 0;
        // if (root == null) {
        // return res;
        // }
        // queue.offer(root);
        // while (!queue.isEmpty()) {
        // int size = queue.size();
        // res += size;
        // for (int i = 0; i < size; i++) {
        // TreeNode curNode = queue.poll();
        // if (curNode.left != null) {
        // queue.offer(curNode.left);
        // }
        // if (curNode.right != null) {
        // queue.offer(curNode.right);
        // }
        // }
        // }
        // return res;

        // 利用完全二叉树的特性来提高效率
        TreeNode l = root, r = root;
        // 记录左右子树的高度
        int lh = 0, rh = 0;
        while (l != null) {
            l = l.left;
            lh++;
        }
        while (r != null) {
            r = r.right;
            rh++;
        }
        // 如果左右子树的高度一样,说明是一个满二叉树
        if (lh == rh) {
            return (int)Math.pow(2, lh) - 1;
        }
        // 否则就分解问题
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
