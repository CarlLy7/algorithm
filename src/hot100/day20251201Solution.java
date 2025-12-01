package hot100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.01
 * @Since: 1.0
 */

public class day20251201Solution {
    // [501] 二叉搜索树中的众数
    // List<Integer> mode = new ArrayList<>();
    // TreeNode pre = null;
    // int currentCount = 0;
    // int maxCount = 0;
    //
    // public int[] findMode(TreeNode root) {
    // traverse(root);
    // int[] res = new int[mode.size()];
    // for (int i = 0; i < res.length; i++) {
    // res[i] = mode.get(i);
    // }
    // return res;
    // }
    //
    // private void traverse(TreeNode root) {
    // if (root == null) {
    // return;
    // }
    // traverse(root.left);
    //
    // if (pre == null) {
    // currentCount = 1;
    // maxCount = 1;
    // mode.add(root.val);
    // } else {
    // if (root.val == pre.val) {
    // currentCount++;
    // if (currentCount == maxCount) {
    // mode.add(root.val);
    // } else if (currentCount > maxCount) {
    // // 如果当前是众数，则把原来的众数清空
    // mode.clear();
    // maxCount = currentCount;
    // mode.add(root.val);
    // }
    // } else {
    // currentCount = 1;
    // if (currentCount == maxCount) {
    // mode.add(root.val);
    // }
    // }
    // }
    // pre = root;
    // traverse(root.right);
    // }

    // [530] 二叉搜索树的最小绝对差
    // int res = Integer.MAX_VALUE;
    // TreeNode pre = null;
    //
    // public int getMinimumDifference(TreeNode root) {
    // traverse(root);
    // return res;
    // }
    //
    // private void traverse(TreeNode root) {
    // if (root == null) {
    // return;
    // }
    // traverse(root.left);
    // if (pre != null) {
    // res = Math.min(Math.abs(root.val - pre.val), res);
    // }
    // pre = root;
    // traverse(root.right);
    // }

    // [653] 两数之和 IV - 输入二叉搜索树

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> arrs = traverse(root);
        int lo = 0, hi = arrs.size() - 1;
        while (lo < hi) {
            int sum = arrs.get(lo) + arrs.get(hi);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                lo++;
            } else {
                hi--;
            }
        }
        return false;
    }

    private List<Integer> traverse(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.addAll(traverse(root.left));
        res.add(root.val);
        res.addAll(traverse(root.right));
        return res;
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
