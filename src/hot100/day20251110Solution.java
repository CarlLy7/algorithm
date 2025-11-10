package hot100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.10
 * @Since: 1.0
 */

public class day20251110Solution {
    // [1261] 在受污染的二叉树中查找元素
    // HashSet<Integer> set = new HashSet<>();
    //
    // public FindElements(TreeNode root) {
    // traverse(root,0);
    // }
    //
    // private void traverse(TreeNode root, int val) {
    // if (root == null) {
    // return;
    // }
    // root.val = val;
    // set.add(val);
    // traverse(root.left, 2 * val + 1);
    // traverse(root.right, 2 * val + 2);
    // }
    //
    // public boolean find(int target) {
    // return set.contains(target);
    // }

    // [386] 字典序排数
    // List<Integer> res = new ArrayList<>();
    //
    // public List<Integer> lexicalOrder(int n) {
    // for (int i = 1; i < 10; i++) {
    // traverse(i, n);
    // }
    // return res;
    // }
    //
    // private void traverse(int i, int n) {
    // if (i > n) {
    // return;
    // }
    // res.add(i);
    // for (int child = i * 10; child < i * 10 + 10; child++) {
    // traverse(child, n);
    // }
    // }

    // [1104] 二叉树寻路
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new ArrayList<>();
        while (label >= 1) {
            res.add(label);
            label = label / 2;
            int depth = log(label);
            int[] range = getLevelRange(depth);
            label = range[1] - (label - range[0]);
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * 计算完全二叉树指定层的最大值和最小值
     * 
     * @param depth
     * @return
     */
    private int[] getLevelRange(int depth) {
        int p = (int)Math.pow(2, depth);
        return new int[] {p, 2 * p - 1};
    }

    /**
     * 根据值求节点所在的完全二叉树的层级
     * 
     * @param label
     * @return
     */
    private int log(int label) {
        return (int)(Math.log(label) / Math.log(2));
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
