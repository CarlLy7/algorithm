package dfs;

import java.util.HashSet;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-02-19 21:07
 * @version: 1.0
 */
public class Solution {
    // 200. 岛屿数量
//    public int numIslands(char[][] grid) {
//        int res = 0;
//        int m = grid.length;
//        int n = grid[0].length;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == '1') {
//                    res++;
//                    dfs(grid, i, j);
//                }
//            }
//        }
//        return res;
//    }

//    private void dfs(char[][] grid, int i, int j) {
//        int m = grid.length;
//        int n = grid[0].length;
//        if (i < 0 || j < 0 || i >= m || j >= n){
//            return;
//        }
//        if (grid[i][j]=='0'){
//            return;
//        }
//        grid[i][j]='0';
//        dfs(grid,i-1,j);
//        dfs(grid,i+1,j);
//        dfs(grid,i,j-1);
//        dfs(grid,i,j+1);
//    }
    // 1254. 统计封闭岛屿的数目
//    public int closedIsland(int[][] grid) {
//        int m= grid.length,n=grid[0].length;
//        int res=0;
//        //将四周淹掉
//        for (int j = 0; j <n ; j++) {
//            dfs(grid,0,j);
//            dfs(grid,m-1,j);
//        }
//        for (int i = 0; i < m; i++) {
//            dfs(grid,i,0);
//            dfs(grid,i,n-1);
//        }
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j]==0){
//                    res++;
//                    dfs(grid,i,j);
//                }
//            }
//        }
//        return res;
//    }

//    private void dfs(int[][] grid, int i, int j) {
//        int m= grid.length,n=grid[0].length;
//        if (i<0 || j<0 || i>=m || j>=n){
//            return;
//        }
//        if (grid[i][j]==1){
//            return;
//        }
//        grid[i][j]=1;
//        dfs(grid,i+1,j);
//        dfs(grid,i-1,j);
//        dfs(grid,i,j+1);
//        dfs(grid,i,j-1);
//    }

    // 695. 岛屿的最大面积
//    public int maxAreaOfIsland(int[][] grid) {
//        int res=0;
//        int m= grid.length,n=grid[0].length;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j]==1){
//                    res=Math.max(res,dfs(grid,i,j));
//                }
//            }
//        }
//        return res;
//    }

//    private int dfs(int[][] grid, int i, int j) {
//        int m = grid.length, n = grid[0].length;
//        if (i < 0 || j < 0 || i >= m || j >= n) {
//            return 0;
//        }
//        if (grid[i][j] == 0) {
//            return 0;
//        }
//        grid[i][j] = 0;
//        return dfs(grid, i + 1, j)+
//        dfs(grid, i - 1, j)+
//        dfs(grid, i, j + 1)+
//        dfs(grid, i, j - 1)+1;
//    }

    // 1905. 统计子岛屿
//    public int countSubIslands(int[][] grid1, int[][] grid2) {
//        int m=grid1.length,n=grid1[0].length;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid1[i][j]==0 && grid2[i][j]==1){
//                    dfs(grid2,i,j);
//                }
//            }
//        }
//        int res=0;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid2[i][j]==1){
//                    res++;
//                    dfs(grid2,i,j);
//                }
//            }
//        }
//        return res;
//    }
//
//    private void dfs(int[][] grid, int i, int j) {
//        int m=grid.length,n=grid[0].length;
//        if (i<0 || j<0 || i>=m || j>=n){
//            return;
//        }
//        if (grid[i][j]==0){
//            return;
//        }
//        grid[i][j]=0;
//        dfs(grid,i+1,j);
//        dfs(grid,i-1,j);
//        dfs(grid,i,j+1);
//        dfs(grid,i,j-1);
//    }
//    public int numDistinctIslands(int[][] grid){
//        int m=grid.length,n=grid[0].length;
//        HashSet<String> res=new HashSet<>();
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j]==1){
//                    StringBuilder sb = new StringBuilder();
//                    dfs(grid,i,j,sb,666);
//                    res.add(sb.toString());
//                }
//            }
//        }
//        return res.size();
//    }
//
//    private void dfs(int[][] grid, int i, int j, StringBuilder sb, int dir) {
//        int m=grid.length,n=grid[0].length;
//        if (i<0 || j<0 || i>=m || j>=n || grid[i][j]==0){
//            return;
//        }
//        sb.append(dir).append(",");
//        dfs(grid,i-1,j,sb,1);
//        dfs(grid,i+1,j,sb,2);
//        dfs(grid,i,j-1,sb,3);
//        dfs(grid,i,j+1,sb,4);
//        sb.append(-dir).append(",");
//    }

    // LCR 105. 岛屿的最大面积
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        return dfs(grid, i - 1, j) + dfs(grid, i + 1, j) + dfs(grid, i, j - 1) + dfs(grid, i, j + 1) + 1;
    }
}
