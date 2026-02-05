package date202602;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.05
 * @Since: 1.0
 */

public class day20260205Solution {
    // [216] 组合总和 III
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    int trackSum = 0;
    int n;
    int k;
    boolean[] used = new boolean[10];

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.n = n;
        this.k = k;
        backTrack(1);
        return ans;
    }

    private void backTrack(int start) {
        if (trackSum == n && track.size() == k) {
            ans.add(new ArrayList<>(track));
            return;
        }
        if (trackSum > n || track.size() > k) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (used[i]) {
                continue;
            }
            track.addLast(i);
            trackSum += i;
            used[i] = true;
            backTrack(i + 1);
            trackSum -= i;
            track.removeLast();
            used[i] = false;
        }
    }

    // [1905] 统计子岛屿
    // public int countSubIslands(int[][] grid1, int[][] grid2) {
    // int m = grid1.length;
    // int n = grid1[0].length;
    // for (int i = 0; i < m; i++) {
    // for (int j = 0; j < n; j++) {
    // // 2中是陆地，1中是水，肯定不是子岛屿了
    // if (grid1[i][j] == 0 && grid2[i][j] == 1) {
    // // 淹没
    // dfs(grid2, i, j);
    // }
    // }
    // }
    // int ans = 0;
    // for (int i = 0; i < m; i++) {
    // for (int j = 0; j < n; j++) {
    // if (grid2[i][j] == 1) {
    // ans++;
    // dfs(grid2, i, j);
    // }
    // }
    // }
    // return ans;
    // }
    //
    // // dfs算法淹没岛屿
    // private void dfs(int[][] grid, int i, int j) {
    // int m = grid.length;
    // int n = grid[0].length;
    // if (i < 0 || i >= m || j < 0 || j >= n) {
    // return;
    // }
    // if (grid[i][j] == 0) {
    // return;
    // }
    // grid[i][j] = 0;
    // dfs(grid, i - 1, j);
    // dfs(grid, i + 1, j);
    // dfs(grid, i, j - 1);
    // dfs(grid, i, j + 1);
    // }

    // [200] 岛屿数量
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    dfs(grid, i, j);
                }
            }
        }
        return ans;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
