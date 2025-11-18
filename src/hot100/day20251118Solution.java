package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.18
 * @Since: 1.0
 */

public class day20251118Solution {
    // [897] 递增顺序搜索树
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftSub = increasingBST(root.left);
        TreeNode rightSub = increasingBST(root.right);
        root.left = null;
        root.right = rightSub;
        if (leftSub == null) {
            return root;
        }
        TreeNode p = leftSub;
        while (p != null && p.right != null) {
            p = p.right;
        }
        p.right = root;
        return leftSub;
    }

    // [938] 二叉搜索树的范围和
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val < low) {
            // 目标在右子树
            return rangeSumBST(root.right, low, high);
        } else if (root.val > high) {
            // 目标在左子树
            return rangeSumBST(root.left, low, high);
        } else {
            // 左右子树都有
            return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        }
    }

    // [1379] 找出克隆二叉树中的相同节点
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return null;
        }
        // 在original中找到目标节点
        if (target == original) {
            return cloned;
        }
        // 去左子树找
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) {
            return left;
        }
        // 左子树找不到的话去右子树找
        return getTargetCopy(original.right, cloned.right, target);
    }

    // private void traverse(TreeNode root) {
    // if (root == null) {
    // return;
    // }
    // traverse(root.left);
    // path.add(root.val);
    // traverse(root.right);
    // }

    // *************************
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
