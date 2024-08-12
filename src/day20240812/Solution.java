package day20240812;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-12 10:55
 * @version: 1.0
 */
public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

//    int res = 0;
//
//    public int sumOfLeftLeaves(TreeNode root) {
//        traverse(root);
//        return res;
//    }
//
//    private void traverse(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        if (root.left != null && root.left.left == null && root.left.right == null) {
//            res += root.left.val;
//        }
//        traverse(root.left);
//        traverse(root.right);
//    }

//    int targetVal, targetDepth;
//    int curDepth;
//
//    public TreeNode addOneRow(TreeNode root, int val, int depth) {
//        this.targetVal = val;
//        this.targetDepth = depth;
//        if (this.targetDepth == 1) {
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

    List<Integer> res = new ArrayList<>();
    int depth = 0;

    public List<Integer> rightSideView(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        //前序位置
        depth++;
        if (res.size() < depth) {
            res.add(root.val);
        }
        traverse(root.right);
        traverse(root.left);
        //后序位置
        depth--;
    }
}
