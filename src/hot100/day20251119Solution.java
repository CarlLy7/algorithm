package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.19
 * @Since: 1.0
 */

public class day20251119Solution {
    // [110] 平衡二叉树
    // boolean res = true;
    //
    // public boolean isBalanced(TreeNode root) {
    // maxDepth(root);
    // return res;
    // }
    //
    // private int maxDepth(TreeNode root) {
    // if (root == null) {
    // return 0;
    // }
    // int leftMaxDepth = maxDepth(root.left);
    // int rightMaxDepth = maxDepth(root.right);
    // if (Math.abs(leftMaxDepth - rightMaxDepth) > 1) {
    // res = false;
    // }
    // return 1 + Math.max(leftMaxDepth, rightMaxDepth);
    // }

    // [563] 二叉树的坡度
    int res = 0;

    public int findTilt(TreeNode root) {
        sum(root);
        return res;
    }

    /**
     * 返回树的节点元素之和
     * 
     * @param root
     * @return
     */
    private int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = sum(root.left);
        int rightSum = sum(root.right);
        res = res += Math.abs(leftSum - rightSum);
        return leftSum + rightSum + root.val;
    }

    // [814] 二叉树剪枝

    /**
     * 二叉树的枝剪其实就是把所有值为0的叶子节点删掉，只剩下值为1的叶子结点
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        // 后序位置,判断自己是不是值为0的叶子节点
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }

    // ***************
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
