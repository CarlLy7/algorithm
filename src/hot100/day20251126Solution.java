package hot100;

import java.util.HashMap;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.26
 * @Since: 1.0
 */

public class day20251126Solution {
    // [979] 在二叉树中分配硬币
    int res = 0;

    public int distributeCoins(TreeNode root) {
        getRest(root);
        return res;
    }

    /**
     * 给定一颗二叉树，返回多余的硬币数,如果是负数则说明缺少硬币
     * 
     * @param root
     */
    private int getRest(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftRest = getRest(root.left);
        int rightRest = getRest(root.right);
        res += Math.abs(leftRest) + Math.abs(rightRest) + (root.val - 1);
        return leftRest + rightRest + (root.val - 1);
    }

    // [1080] 根到叶路径上的不足节点
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (root == null) {
            return root;
        }
        // 当前节点是叶子结点
        if (root.left == null && root.right == null) {
            if (root.val < limit) {
                return null;
            }
            return root;
        }
        TreeNode leftSub = sufficientSubset(root.left, limit - root.val);
        TreeNode rightSub = sufficientSubset(root.right, limit - root.val);
        // 如果这个节点的左右子树都不满足被删掉了，那么当前这个节点也应该被删掉
        if (leftSub == null && rightSub == null) {
            return null;
        }
        root.left = leftSub;
        root.right = rightSub;
        return root;
    }

    // [2049] 统计最高分的节点数目
    int[][] tree;
    HashMap<Long, Integer> scoreToCount = new HashMap<>();

    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        // tree[i][0]:左子树 tree[i][1]:右子树
        tree = new int[n][2];

        buildTree(parents);

        getCount(0);

        long maxScore = 0;
        for (Long score : scoreToCount.keySet()) {
            maxScore = Math.max(maxScore, score);
        }
        return scoreToCount.get(maxScore);
    }

    /**
     * 给定一个根节点，求节点数
     * 
     * @param root
     */
    private int getCount(int root) {
        if (root == -1) {
            return 0;
        }
        int leftCount = getCount(tree[root][0]);
        int rightCount = getCount(tree[root][1]);
        int other = tree.length - leftCount - rightCount - 1;
        long score = (long)Math.max(leftCount, 1) * Math.max(rightCount, 1) * Math.max(other, 1);
        scoreToCount.put(score, scoreToCount.getOrDefault(score, 0) + 1);
        return leftCount + rightCount + 1;
    }

    /**
     * 构造树
     * 
     * @param parents
     */
    private void buildTree(int[] parents) {
        int n = parents.length;
        for (int i = 0; i < n; i++) {
            tree[i][0] = -1;
            tree[i][1] = -1;
        }
        for (int i = 1; i < n; i++) {
            int parent = parents[i];
            if (tree[parent][0] == -1) {
                tree[parent][0] = i;
            } else {
                tree[parent][1] = i;
            }
        }
    }

    // ***************
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
