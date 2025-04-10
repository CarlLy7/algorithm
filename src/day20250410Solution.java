import java.util.Arrays;
import java.util.LinkedList;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.10
 * @Since: 1.0
 */

public class day20250410Solution {

    // 56 合并区间
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            int[] last = res.getLast();
            if (cur[0] <= last[1]) {
                last[1] = Math.max(last[1], cur[1]);
            } else {
                res.add(intervals[i]);
            }
        }
        return res.toArray(new int[0][0]);
    }

    // 62 不同路径
    // int[][] memo;
    //
    // public int uniquePaths(int m, int n) {
    // memo = new int[m][n];
    // return dp(m - 1, n - 1);
    // }
    //
    // // dp(x,y): 从(0,0)到(x,y)总共有多少条不同的路径
    // private int dp(int x, int y) {
    // if (x == 0 && y == 0) {
    // return 1;
    // }
    // if (x < 0 || y < 0) {
    // return 0;
    // }
    // if (memo[x][y] > 0) {
    // return memo[x][y];
    // }
    // memo[x][y] = dp(x-1,y)+dp(x,y-1);
    // return memo[x][y];
    // }

    // 64 最小路径和
    int[][] memo;

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        memo = new int[m][n];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dp(m - 1, n - 1, grid);
    }

    private int dp(int x, int y, int[][] grid) {
        if (x == 0 && y == 0) {
            return grid[x][y];
        }
        if (x < 0 || y < 0) {
            return Integer.MAX_VALUE;
        }
        if (memo[x][y] != -1) {
            return memo[x][y];
        }
        memo[x][y] = Math.min(dp(x - 1, y, grid), dp(x, y - 1, grid)) + grid[x][y];
        return memo[x][y];
    }

}
