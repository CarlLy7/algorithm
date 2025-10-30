package hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.30
 * @Since: 1.0
 */

public class day20251030Solution {
    // [96] 不同的二叉搜索树
    int[][] memo;

    public int numTrees(int n) {
        memo = new int[n + 1][n + 1];
        return count(1, n);
    }

    /**
     * 在【lo,hi】这个范围内可以构造的BST的数量
     * 
     * @param lo
     * @param hi
     * @return
     */
    private int count(int lo, int hi) {
        if (lo >= hi) {
            return 1;
        }
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }
        int res = 0;
        for (int mid = lo; mid <= hi; mid++) {
            int leftCount = count(lo, mid - 1);
            int rightCount = count(mid + 1, hi);
            res += leftCount * rightCount;
        }
        memo[lo][hi] = res;
        return res;
    }

    // [95] 不同的二叉搜索树 II
    public List<TreeNode> generateTrees(int n) {
        return build(1, n);
    }

    private List<TreeNode> build(int lo, int hi) {
        List<TreeNode> res = new ArrayList<>();
        if (lo > hi) {
            res.add(null);
            return res;
        }
        // 遍历每一个节点，都可能作为root节点
        for (int i = lo; i <= hi; i++) {
            // 递归构造出左子树的所有BST
            List<TreeNode> leftList = build(lo, i - 1);
            // 递归构造出右子树的所有BST
            List<TreeNode> rightList = build(i + 1, hi);
            for (TreeNode leftNode : leftList) {
                for (TreeNode rightNode : rightList) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    res.add(root);
                }
            }
        }
        return res;
    }

    // [1373] 二叉搜索子树的最大键值和
    int maxRes = 0;

    public int maxSumBST(TreeNode root) {
        findBSTMinMaxSum(root);
        return maxRes;
    }

    /**
     * 
     * @param root
     * @return res[0]:是否是BST res[1]:以root为根的二叉树的最小值 res[2]：以root为根的二叉树的最大值 res[3]:以root为根的二叉树的节点和
     */
    private int[] findBSTMinMaxSum(TreeNode root) {
        if (root == null) {
            return new int[] {1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        // 递归处理左右子树
        int[] leftSub = findBSTMinMaxSum(root.left);
        int[] rightSub = findBSTMinMaxSum(root.right);
        int[] res = new int[4];
        if (leftSub[0] == 1 && rightSub[0] == 1 && root.val > leftSub[2] && root.val < rightSub[1]) {
            res[0] = 1;
            // 这里为什么需要比较呢，你可以想一下如果当前节点是一个叶子结点的时候怎么处理
            res[1] = Math.min(root.val, leftSub[1]);
            res[2] = Math.max(root.val, rightSub[2]);
            res[3] = leftSub[3] + root.val + rightSub[3];
            maxRes = Math.max(maxRes, res[3]);
        } else {
            res[0] = 0;
        }
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
