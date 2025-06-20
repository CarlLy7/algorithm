/**
 * @description:
 * @author: carl
 * @date: 2025.06.20
 * @Since: 1.0
 */

public class day20250620Solution {
    // [563] 二叉树的坡度
    int res = 0;

    public int findTilt(TreeNode root) {
        sum(root);
        return res;
    }

    /**
     * 以root为根的树，所有节点的和
     * 
     * @param root
     */
    private int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = sum(root.left);
        int rightSum = sum(root.right);
        // 后序位置，更新坡度
        res += Math.abs(leftSum - rightSum);
        return leftSum + rightSum + root.val;
    }

    // [1325] 删除给定值的叶子节点
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        // 利用分解问题的思路
        if (root == null) {
            return null;
        }
        // 处理左子树
        root.left = removeLeafNodes(root.left, target);
        // 处理右子树
        root.right = removeLeafNodes(root.right, target);
        // 后序位置处理
        // 只有当自己的左右孩子都是空，并且自己也是target的时候，才会返回空，否则返回自己
        if (root.val == target && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }

    // [865] 具有所有最深节点的最小子树
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Result res = maxDepth(root);
        return res.node;
    }

    /**
     * 给定一颗二叉树的根节点，返回二叉树的最大深度以及最深叶子结点的最近公共祖先
     * 
     * @param root
     * @return
     */
    private Result maxDepth(TreeNode root) {
        if (root == null) {
            return new Result(0, root);
        }
        Result leftResult = maxDepth(root.left);
        Result rightResult = maxDepth(root.right);
        // 后置位置

        // 如果左右子树的最大深度一样，当前节点肯定是最近公共祖先
        if (leftResult.depth == rightResult.depth) {
            return new Result(leftResult.depth + 1, root);
        }
        // 如果左右子树的深度不一样，最深叶子节点的最近公共祖先肯定在深的一方
        Result result = leftResult.depth > rightResult.depth ? leftResult : rightResult;
        // 记得加上根节点这一个深度
        result.depth++;
        return result;
    }

    private class Result {
        // 最大深度
        private int depth;

        // 最深叶子结点的最近公共祖先
        private TreeNode node;

        public Result(int depth, TreeNode node) {
            this.depth = depth;
            this.node = node;
        }
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
