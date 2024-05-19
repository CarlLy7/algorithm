package dp;

import java.util.Arrays;

/**
 * @description: 最小路径和 https://leetcode.cn/problems/minimum-path-sum/
 * @author: lyq
 * @createDate: 13/4/2023
 * @version: 1.0
 */
public class MinimumPathSum {
    //因为有重叠子问题，所以用备忘录进行优化
//    int[][] memo;
//    public int minPathSum(int[][] grid) {
//        int row=grid.length;
//        int col=grid[0].length;
//        memo=new int[row][col];
//        for (int[] ints : memo) {
//            Arrays.fill(ints,-1);
//        }
//        //dp（grid,i,j）函数的定义为达到grid数组中的(i,j)位置的时候的最小路径和
//        return dp(grid,row-1,col-1);
//    }
//
//    private int dp(int[][] grid, int i, int j) {
//        //base case :左上角开始，所以左上角的初始值就是grid(0,0)
//        if(i==0 && j==0){
//            return grid[0][0];
//        }
//        //如果下标越界了，设置一个很大的值
//        if(i<0||j<0){
//            //这里为什么只判断小于0越界呢，因为只能右移一位或者下移一位，所以当前位置只能有上面那个位置或者左边那个位置过来
//            return Integer.MAX_VALUE;
//        }
//        if(memo[i][j]!=-1){
//            return memo[i][j];
//        }
//        memo[i][j]= Math.min(dp(grid,i-1,j),dp(grid,i,j-1))+grid[i][j];
//        return memo[i][j];
//    }

    //===================================
    //下面来写一个自底向上的迭代的动态规划
//    public int minPathSum(int[][] grid) {
//        int row= grid.length;
//        int col=grid[0].length;
//        //dp数组的定义：到达（i,j）位置的最小路径和
//        int[][] dp=new int[row][col];
//        //base case 当到达（0,0）位置的时候就是grid[0][0]
//        dp[0][0]=grid[0][0];
//        //当行 为第一行的时候，每个位置的值都是左边那个位置的最小路径+自己位置的路径大小
//        for (int j = 1; j < col; j++) {
//            dp[0][j]=dp[0][j-1]+grid[0][j];
//        }
//        //当列 为第一列的时候，每个位置的值都是上面那个位置的最小路径+自己位置的路径大小
//        for (int i = 1; i <row ; i++) {
//            dp[i][0]=dp[i-1][0]+grid[i][0];
//        }
//        for (int i = 1; i <row ; i++) {
//            for (int j = 1; j <col ; j++) {
//                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
//            }
//        }
//        return dp[row-1][col-1];
//    }


    //=============================
    //压缩空间为一维
    public int minPathSum(int[][] grid) {
        int col=grid[0].length;
        int[] dp=new int[col];
        dp[0]=grid[0][0];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < col; j++) {
                if(i==0 && j==0){
                    continue;
                }else if(i-1<0){
                    //第一行
                    dp[j]=dp[j-1]+ grid[i][j];
                }else if(j-1<0){
                    //第一列
                    dp[j]=dp[j]+grid[i][j];
                }else{
                    dp[j]=Math.min(dp[j],dp[j-1])+grid[i][j];
                }
            }
        }
        return dp[col-1];
    }
}
