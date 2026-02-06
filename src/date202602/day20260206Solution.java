package date202602;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.06
 * @Since: 1.0
 */

public class day20260206Solution {
    // [1254] 统计封闭岛屿的数目
    // public int closedIsland(int[][] grid) {
    // int m=grid.length;
    // int n=grid[0].length;
    // for (int j = 0; j < n; j++) {
    // //把上面的岛屿淹没
    // dfs(grid,0,j);
    // //把下面的岛屿淹没
    // dfs(grid,m-1,j);
    // }
    // for (int i = 0; i < m; i++) {
    // //把左边淹没
    // dfs(grid,i,0);
    // //把右边淹没
    // dfs(grid,i,n-1);
    // }
    // int ans=0;
    // for (int i = 0; i < m; i++) {
    // for (int j = 0; j < n; j++) {
    // if (grid[i][j]==0){
    // ans++;
    // dfs(grid,i,j);
    // }
    // }
    // }
    // return ans;
    // }
    //
    // private void dfs(int[][] grid, int i, int j) {
    // int m = grid.length;
    // int n = grid[0].length;
    // if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 1) {
    // return;
    // }
    // grid[i][j] = 1;
    // dfs(grid, i - 1, j);
    // dfs(grid, i + 1, j);
    // dfs(grid, i, j - 1);
    // dfs(grid, i, j + 1);
    // }

    // [1020] 飞地的数量
    // public int numEnclaves(int[][] grid) {
    // int m=grid.length;
    // int n=grid[0].length;
    // for (int j = 0; j < n; j++) {
    // //把上面的岛屿淹没
    // dfs(grid,0,j);
    // //把下面的岛屿淹没
    // dfs(grid,m-1,j);
    // }
    // for (int i = 0; i < m; i++) {
    // //把左边淹没
    // dfs(grid,i,0);
    // //把右边淹没
    // dfs(grid,i,n-1);
    // }
    // int ans=0;
    // for (int i = 0; i < m; i++) {
    // for (int j = 0; j < n; j++) {
    // if (grid[i][j]==0){
    // ans++;
    // dfs(grid,i,j);
    // }
    // }
    // }
    // return ans;
    // }

    // [695] 岛屿的最大面积
    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(grid, i, j);
                    ans = Math.max(ans, size);
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, int i, int j) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return ans;
        }
        grid[i][j] = 0;
        ans++;
        ans += dfs(grid, i - 1, j);
        ans += dfs(grid, i + 1, j);
        ans += dfs(grid, i, j - 1);
        ans += dfs(grid, i, j + 1);
        return ans;
    }

    // [51] N 皇后
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        List<String> boards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(".");
            }
            boards.add(sb.toString());
        }
        backTrack(boards, 0);
        return res;
    }

    private void backTrack(List<String> boards, int row) {
        // 结束条件
        if (row == boards.size()) {
            res.add(new ArrayList<>(boards));
            return;
        }
        int n = boards.get(row).length();
        // 选择列表是该行的每一列
        for (int col = 0; col < n; col++) {
            if (!valid(boards, row, col)) {
                continue;
            }
            char[] chars = boards.get(row).toCharArray();
            chars[col] = 'Q';
            boards.set(row, new String(chars));
            backTrack(boards, row + 1);
            chars[col] = '.';
            boards.set(row, new String(chars));
        }
    }

    private boolean valid(List<String> boards, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (boards.get(i).charAt(col) == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < boards.get(row).length(); i--, j++) {
            if (boards.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (boards.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }
}
