package dp;

import java.util.Arrays;

/**
 * @description: 礼物的最大价值 https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/?show=1
 * @author: lyq
 * @createDate: 13/4/2023
 * @version: 1.0
 */
public class TheMaximumValueOfGifts {
    int[][] memo;

    public int maxValue(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        memo = new int[row][col];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dp(grid, row - 1, col - 1);
    }

    private int dp(int[][] grid, int i, int j) {
        //base case
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        if (i < 0 || j < 0) {
            return Integer.MIN_VALUE;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        memo[i][j] = Math.max(dp(grid, i - 1, j), dp(grid, i, j - 1)) + grid[i][j];
        return memo[i][j];
    }
}
