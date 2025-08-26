package hot100;

import netscape.security.UserTarget;

import java.util.*;

import static testDemo.testDemo7.dfs;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.26
 * @Since: 1.0
 */

public class day20250826Solution {
    // [198] 打家劫舍
    int[] memo;

    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(0, nums);
    }

    /**
     * 从start开始可以rob的最多金额
     * 
     * @param start
     * @param nums
     * @return
     */
    private int dp(int start, int[] nums) {
        if (start >= nums.length) {
            return 0;
        }
        if (memo[start] != -1) {
            return memo[start];
        }
        int res = Math.max(dp(start + 1, nums), nums[start] + dp(start + 2, nums));
        memo[start] = res;
        return res;
    }

    // [199] 二叉树的右视图
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return res;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (i == 0) {
                    res.add(curNode.val);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
            }
        }
        return res;
    }

    // [200] 岛屿数量
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
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
