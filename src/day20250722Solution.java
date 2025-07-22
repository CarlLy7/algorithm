import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.22
 * @Since: 1.0
 */

public class day20250722Solution {
    // [LCR 163] 找到第 k 位数字
    public int findKthNumber(int k) {
        if (k == 0) {
            return 0;
        }
        return findNthDigit_400(k);
    }

    int findNthDigit_400(int n) {
        // 位数（一位数，两位数...）
        int digit = 1;
        // 1,10,100, 1000 这样的后缀
        long base = 1;

        while (n > 9 * base * digit) {
            n -= 9 * base * digit;
            base *= 10;
            digit++;
        }

        // 此时假设 base = 1000，那么说明 n 是 100~999 中的某个三位数的某一位
        // 哪个三位数呢？这样算：
        long val = base + (n - 1) / digit;
        // 是这个三位数的第几位呢？这样算：
        int index = (n - 1) % digit;

        // 怎么把 val 的第 index 这一位数字抠出来呢？这样算：
        return ("" + val).charAt(index) - '0';
    }

    // [LCR 153] 二叉树中和为目标值的路径
    public List<List<Integer>> pathTarget(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (root.left == null && root.right == null && root.val == target) {
            LinkedList<Integer> track = new LinkedList<>();
            track.addLast(root.val);
            res.add(track);
            return res;
        }
        // 分解问题
        List<List<Integer>> leftSub = pathTarget(root.left, target - root.val);
        List<List<Integer>> rightSub = pathTarget(root.right, target - root.val);
        for (List<Integer> left : leftSub) {
            left.add(0, root.val);
            res.add(left);
        }
        for (List<Integer> right : rightSub) {
            right.add(0, root.val);
            res.add(right);
        }
        return res;
    }

    // [LCR 175] 计算二叉树的深度
    public int calculateDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 分解问题
        int leftDepth = calculateDepth(root.left);
        int rightDepth = calculateDepth(root.right);

        return 1 + Math.max(leftDepth, rightDepth);
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
