import java.util.HashMap;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.24
 * @Since: 1.0
 */

public class day20250624Solution {
    // [979] 在二叉树中分配硬币
    int res = 0;

    public int distributeCoins(TreeNode root) {
        getRest(root);
        return res;
    }

    /**
     * 给定一颗二叉树，返回这个二叉树多余的硬币数，如果是负数代表没有剩余只有需要
     * 
     * @param root
     */
    private int getRest(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getRest(root.left);
        int right = getRest(root.right);
        // 因为root节点只需要保留一个硬币，所以后面需要加上root.val-1
        res += Math.abs(left) + Math.abs(right) + root.val - 1;
        return left + right + root.val - 1;
    }

    // [1080] 根到叶路径上的不足节点
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (root == null) {
            return root;
        }
        if (root.left == null && root.right == null) {
            if (root.val < limit) {
                return null;
            }
            return root;
        }

        TreeNode leftTree = sufficientSubset(root.left, limit - root.val);
        TreeNode rightTree = sufficientSubset(root.right, limit - root.val);
        if (leftTree == null && rightTree == null) {
            return null;
        }
        root.left = leftTree;
        root.right = rightTree;
        return root;
    }

    // [2049] 统计最高分的节点数目

    // 使用邻接表来表示一个二叉树
    int[][] tree;
    // k:最高分 v:个数
    HashMap<Long, Integer> map = new HashMap<>();

    public int countHighestScoreNodes(int[] parents) {
        this.tree=buildTree(parents);

        countNode(0);

        long maxScore = 0;
        for (Long score : map.keySet()) {
            maxScore = Math.max(score, maxScore);
        }
        return map.get(maxScore);
    }

    /**
     * 计算二叉树的节点个数
     * 
     * @param root
     * @return
     */
    private int countNode(int root) {
        if (root == -1) {
            return 0;
        }
        int leftCount = countNode(tree[root][0]);
        int rightCount = countNode(tree[root][1]);

        int n = tree.length;
        int otherCount = n - leftCount - rightCount - 1;
        // 得分= 左子树的节点个数 * 右子树的节点个数 * 除此二叉树外的其他节点个数
        long score = (long)Math.max(leftCount, 1) * Math.max(rightCount, 1) * Math.max(otherCount, 1);
        map.put(score, map.getOrDefault(score, 0) + 1);

        return leftCount + rightCount + 1;
    }

    /**
     * 使用邻接矩阵来创建一个二叉树
     * 
     * @param parents
     */
    private int[][] buildTree(int[] parents) {
        int n = parents.length;
        // tree[i][0]:左子树 tree[i][1]右子树
        int[][] tree = new int[n][2];

        for (int[] ints : tree) {
            ints[0] = -1;
            ints[1] = -1;
        }

        for (int i = 1; i < n; i++) {
            int parent_i = parents[i];
            if (tree[parent_i][0] == -1) {
                tree[parent_i][0] = i;
            } else {
                tree[parent_i][1] = i;
            }
        }
        return tree;
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
