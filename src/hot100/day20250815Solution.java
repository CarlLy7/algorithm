package hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.15
 * @Since: 1.0
 */

public class day20250815Solution {
    // [101] 对称二叉树
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root.left, root.right);
    }

    /**
     * 检查两棵子树是否镜像对称
     * 
     * @param left
     * @param right
     * @return
     */
    private boolean check(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        if (left.val != right.val) {
            return false;
        }
        return check(left.left, right.right) && check(left.right, right.left);
    }

    // [102] 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> track = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                track.addLast(curNode.val);
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            res.add(new ArrayList<>(track));
        }
        return res;
    }

    // [104] 二叉树的最大深度
    int res = 0;
    int depth=0;

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 分解子问题的思路
        // int leftDepth = maxDepth(root.left);
        // int rightDepth = maxDepth(root.right);
        // return Math.max(leftDepth, rightDepth) + 1;
        // 遍历问题的思路
        traverse(root);
        return res;
    }

    /**
     * 遍历的思路求最大深度
     * 
     * @param root
     */
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        //前序位置
        depth++;
        // base case:到达叶子结点之后更新答案
        if (root.left == null && root.right == null) {
            res = Math.max(res, depth);
        }
        //左子树
        traverse(root.left);
        //右子树
        traverse(root.right);
        //后序位置
        depth--;
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
