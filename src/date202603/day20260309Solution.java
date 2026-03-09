package date202603;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2026.03.09
 * @Since: 1.0
 */

public class day20260309Solution {
    // [104] 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return 1 + Math.max(leftMaxDepth, rightMaxDepth);
    }

    // [199] 二叉树的右视图
    LinkedList<Integer> res = new LinkedList<>();
    int depth = 0;

    public List<Integer> rightSideView(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        depth++;
        // 每一层应该填入一个元素，所以元素个数和层数应该是相等的，如果小说明需要填进去
        if (res.size() < depth) {
            res.addLast(root.val);
        }
        // 因为是右视图，所以需要先遍历右子树
        traverse(root.right);
        traverse(root.left);
        depth--;
    }

    // [235] 二叉搜索树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        // 如果两个节点有一个是根节点，那么最近公共祖先就是根节点
        if (root == p || root == q) {
            return root;
        }
        // 去当前节点的左子树中找看有没有两个节点的公共祖先
        TreeNode leftAncestor = lowestCommonAncestor(root.left, p, q);
        // 去当前节点的右子树中看看有没有两个节点的公共祖先
        TreeNode rightAncestor = lowestCommonAncestor(root.right, p, q);

        // 如果左右子树中都找到了最近公共祖先，那么说明当前节点是最近公共祖先
        if (leftAncestor != null && rightAncestor != null) {
            return root;
        }

        // 如果左子树和右子树中只有一个公共祖先，那么对应的就是
        return leftAncestor == null ? rightAncestor : leftAncestor;
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
