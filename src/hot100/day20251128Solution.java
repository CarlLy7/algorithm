package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.28
 * @Since: 1.0
 */

public class day20251128Solution {
    // [99] 恢复二叉搜索树
    TreeNode first = null, second = null;
    // 前面一个节点
    TreeNode preNode = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    /**
     * BST的中序遍历
     * 
     * @param root
     */
    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        // 当前这个根节点出问题了
        if (root.val < preNode.val) {
            // 如果第一个有问题的位置是空的，那么preNode就是第一个first
            if (first == null) {
                first = preNode;
            }
            // 当前根节点肯定是有问题的
            second = root;
        }
        preNode = root;
        inorder(root.right);
    }

    // [669] 修剪二叉搜索树
    public TreeNode trimBST(TreeNode root, int low, int high) {
        // 确定思路：使用分解子问题
        if (root == null) {
            return null;
        }
        // root和root的左子树都应该裁剪掉
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            // root和root的右子树都应该被裁剪掉
            return trimBST(root.left, low, high);
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }

    // [671] 二叉树中第二小的节点
    public int findSecondMinimumValue(TreeNode root) {
        if (root.left == null && root.right == null) {
            return -1;
        }
        int left = root.left.val, right = root.right.val;
        // 如果最小的值等于左子树最小的值，尝试去左子树搜索第二小的值
        if (root.val == root.left.val) {
            left = findSecondMinimumValue(root.left);
        }
        // 如果最小的值是右子树最小的值，尝试去右子树搜索第二小的值
        if (root.val == root.right.val) {
            right = findSecondMinimumValue(root.right);
        }
        if (left == -1) {
            return right;
        }
        if (right == -1) {
            return left;
        }
        // 如果最小值刚好等于左右子树的值，那么left和right可能会同时找到第二小的值，则取最小的
        return Math.min(left, right);
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
