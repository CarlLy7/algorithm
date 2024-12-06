import java.util.HashSet;

/**
 * @author: luanyingqi
 * @date: 2024/12/6
 */

public class day20241206Solution {

    //694
    public int numDistinctIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, 666);
                }
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, int i, int j, StringBuilder sb, int dir) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        sb.append(dir).append(",");
        dfs(grid, i - 1, j, sb, 1);
        dfs(grid, i + 1, j, sb, 2);
        dfs(grid, i, j - 1, sb, 3);
        dfs(grid, i, j + 1, sb, 4);
        sb.append(-dir).append(",");
    }

    //1905
//    public int countSubIslands(int[][] grid1, int[][] grid2) {
//        int m = grid1.length;
//        int n = grid1[0].length;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
//                    dfs(grid2, i, j);
//                }
//            }
//        }
//        int res = 0;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid2[i][j] == 1) {
//                    res++;
//                    dfs(grid2, i, j);
//                }
//            }
//        }
//        return res;
//    }
//
//    private void dfs(int[][] grid2, int i, int j) {
//        int[][] temp = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
//        int m = grid2.length;
//        int n = grid2[0].length;
//        if (i < 0 || j < 0 || i >= m || j >= n) {
//            return;
//        }
//        if (grid2[i][j] == 0) {
//            return;
//        }
//        grid2[i][j] = 0;
//        for (int[] ints : temp) {
//            dfs(grid2, i + ints[0], j + ints[1]);
//        }
//    }

    //695
//    public int maxAreaOfIsland(int[][] grid) {
//        int m = grid.length;
//        int n = grid[0].length;
//        int res = 0;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == 1) {
//                    res = Math.max(res, dfs(grid, i, j));
//                }
//            }
//        }
//        return res;
//    }
//
//    private int dfs(int[][] grid, int i, int j) {
//        int m = grid.length;
//        int n = grid[0].length;
//        if (i < 0 || j < 0 || i >= m || j >= n) {
//            return 0;
//        }
//        if (grid[i][j] == 0) {
//            return 0;
//        }
//        grid[i][j] = 0;
//        return 1 + dfs(grid, i - 1, j) + dfs(grid, i + 1, j) + dfs(grid, i, j - 1) + dfs(grid, i, j + 1);
//    }

    //1254
//    public int closedIsland(int[][] grid) {
//        int m = grid.length;
//        int n = grid[0].length;
//        for (int i = 0; i < m; i++) {
//            dfs(grid, i, 0);
//            dfs(grid, i, n - 1);
//        }
//        for (int j = 0; j < n; j++) {
//            dfs(grid, 0, j);
//            dfs(grid, m - 1, j);
//        }
//        int res = 0;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == 0) {
//                    res++;
//                    dfs(grid, i, j);
//                }
//            }
//        }
//        return res;
//    }
//
//    private void dfs(int[][] grid, int i, int j) {
//        int m = grid.length;
//        int n = grid[0].length;
//        if (i < 0 || j < 0 || i >= m || j >= n) {
//            return;
//        }
//        if (grid[i][j] == 1) {
//            return;
//        }
//        grid[i][j] = 1;
//        dfs(grid, i - 1, j);
//        dfs(grid, i + 1, j);
//        dfs(grid, i, j - 1);
//        dfs(grid, i, j + 1);
//    }

    //200
//    public int numIslands(int[][] grid) {
//        int m = grid.length;
//        int n = grid[0].length;
//        int res = 0;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == '1') {
//                    res++;
//                    // 使用dfs算法将这个岛屿淹没
//                    dfs(grid, i, j);
//                }
//            }
//        }
//        return res;
//    }
//
//    private void dfs(int[][] grid, int i, int j) {
//        int m = grid.length;
//        int n = grid[0].length;
//        if (i < 0 || j < 0 || i >= m || j >= n) {
//            return;
//        }
//        if (grid[i][j] == '0') {
//            return;
//        }
//        grid[i][j] = '0';
//        dfs(grid, i - 1, j);
//        dfs(grid, i + 1, j);
//        dfs(grid, i, j - 1);
//        dfs(grid, i, j + 1);
//    }


}
