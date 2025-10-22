package hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.22
 * @Since: 1.0
 */

public class day20251022Solution {
    // [104] 二叉树的最大深度
    // public int maxDepth(TreeNode root) {
    // if (root == null) {
    // return 0;
    // }
    // int leftDepth = maxDepth(root.left);
    // int rightDepth = maxDepth(root.right);
    // return Math.max(leftDepth, rightDepth) + 1;
    // }

    // [543] 二叉树的直径
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        // 解题思路：二叉树的最大直径其实就是左右子树的最大深度之和
        maxDepth(root);
        return max;
    }

    /**
     * 给你一个二叉树的根节点，求最大深度
     * 
     * @param root
     */
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        max = Math.max(max, leftDepth + rightDepth);
        return 1 + Math.max(leftDepth, rightDepth);
    }

    // [144] 二叉树的前序遍历
    // public List<Integer> preorderTraversal(TreeNode root) {
    // List<Integer> res = new ArrayList<>();
    // if (root == null) {
    // return res;
    // }
    // res.add(root.val);
    // List<Integer> left = preorderTraversal(root.left);
    // List<Integer> right = preorderTraversal(root.right);
    // res.addAll(left);
    // res.addAll(right);
    // return res;
    // }
    List<Integer> res = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        traverse(root);
        return res;
    }

    /**
     * 回溯算法来进行前序遍历
     * 
     * @param root
     */
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        traverse(root.left);
        traverse(root.right);
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
