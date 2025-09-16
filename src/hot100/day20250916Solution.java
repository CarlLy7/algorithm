package hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.16
 * @Since: 1.0
 */

public class day20250916Solution {
    // [124] 二叉树中的最大路径和
    // int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxOneSide(root);
        return res;
    }

    /**
     * 计算单边最大路径和
     * 
     * @param root
     * @return
     */
    private int maxOneSide(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 如果小于0其实我就不要这条边了
        int leftSub = Math.max(0, maxOneSide(root.left));
        int rightSub = Math.max(0, maxOneSide(root.right));
        int path = leftSub + rightSub + root.val;
        res = Math.max(res, path);
        return Math.max(leftSub, rightSub) + root.val;
    }

    // [543] 二叉树的直径
    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return res;
    }

    /**
     * 求root为根的二叉树的最大深度
     * 
     * @param root
     * @return
     */
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        // 后序位置
        res = Math.max(res, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // [366]寻找二叉树的叶子节点
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> findLeaves(TreeNode root) {
        dfs(root);
        return ans;
    }

    /**
     * 求root为根的二叉树的深度
     * 
     * @param root
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = dfs(root.left);
        int rightDepth = dfs(root.right);
        // 后序位置，将对应高度的节点放到对应索引的数组中，其实就是相同高度的节点放在一个数组中
        int depth = Math.max(leftDepth, rightDepth);
        if (ans.size() == depth) {
            ans.add(new ArrayList<>());
        }
        // 相同高度的节点放在一个列表中
        ans.get(depth).add(root.val);
        return depth + 1;
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
