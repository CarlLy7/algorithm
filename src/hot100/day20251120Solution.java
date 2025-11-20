package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.20
 * @Since: 1.0
 */

public class day20251120Solution {
    // [1325] 删除给定值的叶子节点
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        // 后序位置判断自己是不是需要删除的叶子结点
        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }
        return root;
    }

    // [687] 最长同值路径
    int res = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxLen(root, root.val);
        return res;
    }

    /**
     * 求值为val的最大长度
     * 
     * @param root
     * @param val
     * @return
     */
    private int maxLen(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        // 求左子树中值为父节点值的最大长度
        int leftLen = maxLen(root.left, root.val);
        int rightLen = maxLen(root.right, root.val);
        res = Math.max(res, leftLen + rightLen);
        if (root.val != val) {
            return 0;
        }
        return Math.max(leftLen, rightLen) + 1;
    }

    // [865] 具有所有最深节点的最小子树
    private class Result {
        // 最大叶子节点的公共祖先
        private TreeNode node;
        // 最大叶子节点的深度
        private Integer depth;

        public Result(TreeNode node, Integer depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }
        Result res = maxDepth(root);
        return res.node;
    }

    /**
     * 求以root为根的二叉树的最大叶子结点深度和最大叶子结点的公共祖先
     * 
     * @param root
     * @return
     */
    private Result maxDepth(TreeNode root) {
        if (root == null) {
            return new Result(null, 0);
        }
        // 分解问题
        Result leftResult = maxDepth(root.left);
        Result rightResult = maxDepth(root.right);
        // 如果左右子树的深度一样，说明当前root为最近公共祖先
        if (leftResult.depth == rightResult.depth) {
            return new Result(root, rightResult.depth + 1);
        }
        // 否则公共祖先肯定在深的那一侧
        Result result = leftResult.depth > rightResult.depth ? leftResult : rightResult;
        return new Result(result.node, result.depth + 1);
    }

    // ************************
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
