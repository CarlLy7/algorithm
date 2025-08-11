package hot100;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.11
 * @Since: 1.0
 */

public class day2025811Solution {
    // [56] 合并区间
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });
        res.addLast(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            int[] last = res.getLast();
            // 区间有覆盖，可以合并
            if (cur[0] <= last[1]) {
                last[1] = Math.max(last[1], cur[1]);
            } else {
                res.add(cur);
            }
        }
        return res.toArray(new int[][] {});
    }

    // [62] 不同路径
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        return dp(m - 1, n - 1, memo);
    }

    /**
     * 从[0,0]到达[i,j]的路径
     * 
     * @param i
     * @param j
     * @param memo
     * @return
     */
    private int dp(int i, int j, int[][] memo) {
        if (i == 0 && j == 0) {
            return 1;
        }
        if (i < 0 || j < 0) {
            return 0;
        }
        if (memo[i][j] > 0) {
            return memo[i][j];
        }
        memo[i][j] = dp(i - 1, j, memo) + dp(i, j - 1, memo);
        return memo[i][j];
    }

    // [64] 最小路径和
    int[][] memo;

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        memo = new int[m][n];
        for (int[] ints : memo) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        return dp(grid, m - 1, n - 1);
    }

    /**
     * 从[0,0]到[i,j]的最小路径和
     * 
     * @param grid
     * @param i
     * @param j
     * @return
     */
    private int dp(int[][] grid, int i, int j) {
        if (i == 0 && j == 0) {
            return grid[i][j];
        }
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        if (memo[i][j] != Integer.MAX_VALUE) {
            return memo[i][j];
        }
        memo[i][j] = Math.min(dp(grid, i - 1, j), dp(grid, i, j - 1)) + grid[i][j];
        return memo[i][j];
    }
}
