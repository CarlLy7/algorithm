package hot100;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.04
 * @Since: 1.0
 */

public class day20251104Solution {
    // [404] 左叶子之和
    // int res = 0;
    //
    // public int sumOfLeftLeaves(TreeNode root) {
    // traverse(root);
    // return res;
    // }
    //
    // private void traverse(TreeNode root) {
    // if (root == null) {
    // return;
    // }
    // if (root.left != null && root.left.left == null && root.left.right == null) {
    // res += root.left.val;
    // }
    // traverse(root.left);
    // traverse(root.right);
    // }

    // [623] 在二叉树中增加一行
//    int curDepth = 0;
//    int targetVal, targetDepth;
//
//    public TreeNode addOneRow(TreeNode root, int val, int depth) {
//        targetVal = val;
//        targetDepth = depth;
//        // 如果depth=1的话，特殊处理一下
//        if (depth == 1) {
//            TreeNode newRoot = new TreeNode(val);
//            newRoot.left = root;
//            return newRoot;
//        }
//        traverse(root);
//        return root;
//    }
//
//    private void traverse(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        curDepth++;
//        if (curDepth == targetDepth - 1) {
//            TreeNode newLeft = new TreeNode(targetVal);
//            TreeNode newRight = new TreeNode(targetVal);
//            newLeft.left = root.left;
//            newRight.right = root.right;
//            root.left = newLeft;
//            root.right = newRight;
//        }
//        traverse(root.left);
//        traverse(root.right);
//        curDepth--;
//    }

    // [971] 翻转二叉树以匹配先序遍历
    int[] voyage;
    int i = 0;
    boolean canF = true;
    List<Integer> res = new LinkedList<>();

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        this.voyage = voyage;
        traverse(root);
        if (!canF) {
            return Arrays.asList(-1);
        }
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null || !canF) {
            return;
        }
        /**
         * 如果当前节点的值不相等，说明不能通过翻转实现
         */
        if (root.val != voyage[i]) {
            canF = false;
            return;
        }
        i++;
        // 如果左子树根节点值不相同，尝试翻转
        if (root.left != null && root.left.val != voyage[i]) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            res.add(root.val);
        }
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
