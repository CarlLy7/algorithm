import java.util.Arrays;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.04
 * @Since: 1.0
 */

public class day20250604Solution {
    // [1372] 二叉树中的最长交错路径
    int res = 0;

    public int longestZigZag(TreeNode root) {
        getPathLen(root);
        return res;
    }

    /**
     * 从root出发，返回最长的交错路径长度
     * 
     * @param root
     */
    private int[] getPathLen(TreeNode root) {
        if (root == null) {
            return new int[] {-1, -1};
        }
        int[] leftPathLen = getPathLen(root.left);
        int[] rightPathLen = getPathLen(root.right);
        int left = leftPathLen[1] + 1;
        int right = rightPathLen[0] + 1;
        res = Math.max(res, Math.max(left, right));
        return new int[] {left, right};
    }

    // [1373] 二叉搜索子树的最大键值和
    // 1.左右子树是不是BST
    // 2.左子树的最大值，右子树的最小值
    // 3.左右子树的节点值之后
    int maxRes = 0;

    public int maxSumBST(TreeNode root) {
        findMinMaxSum(root);
        return maxRes;
    }

    /**
     * 返回的是int[4]数组 int[0]:以root为根的二叉树是不是BST int[1]:以root为根的二叉树的的最小值 int[2]:以root为根的二叉树的最大值 int[3]:以root为根的二叉树的和
     * 
     * @param root
     */
    private int[] findMinMaxSum(TreeNode root) {
        if (root == null) {
            return new int[] {1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        int[] leftMinMaxSum = findMinMaxSum(root.left);
        int[] rightMinMaxSum = findMinMaxSum(root.right);
        int[] res = new int[4];
        // 当前root为根的二叉树符合BST
        if (leftMinMaxSum[0] == 1 && rightMinMaxSum[0] == 1 && leftMinMaxSum[2] < root.val
            && root.val < rightMinMaxSum[1]) {
            int min = Math.min(root.val, leftMinMaxSum[1]);
            int max = Math.max(root.val, rightMinMaxSum[2]);
            int sum = leftMinMaxSum[3] + rightMinMaxSum[3] + root.val;
            maxRes = Math.max(maxRes, sum);
            res[0] = 1;
            res[1] = min;
            res[2] = max;
            res[3] = sum;
        } else {
            res[0] = 0;
        }
        return res;
    }

    // [2140] 解决智力问题
    long[] memo;

    public long mostPoints(int[][] questions) {
        int n = questions.length;
        memo = new long[n];
        Arrays.fill(memo, -1);
        return dp(questions, 0);
    }

    /**
     * 从第satrt个问题开始做，可以获得的最大的分数
     * 
     * @param questions
     * @param start
     * @return
     */
    private long dp(int[][] questions, int start) {
        int n = questions.length;
        if (start >= n) {
            return 0;
        }
        if (memo[start] != -1) {
            return memo[start];
        }
        // 可以选择做这个题还是不做这个题
        memo[start] =
            // 如果做的话，需要跳过questions[start][1]的索引再+1
            Math.max(dp(questions, start + 1), questions[start][0] + dp(questions, start + questions[start][1] + 1));
        return memo[start];
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
