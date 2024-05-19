package dp;

import java.util.Arrays;

/**
 * @description: 下降路径最小和 https://leetcode.cn/problems/minimum-falling-path-sum/
 * @author: lyq
 * @createDate: 1/4/2023
 * @version: 1.0
 */
public class LoweringPathMinimumSum {
    int[][] memo;

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int res = Integer.MAX_VALUE;
        memo = new int[n][n];
        for (int[] ints : memo) {
            Arrays.fill(ints, 66666);
        }
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp(matrix, 0, i));
        }
        return res;
    }

    private int dp(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[i].length) {
            return 99999;
        }
        if(i== matrix.length-1){
            return matrix[i][j];
        }
        if(memo[i][j]!=6666){
            return memo[i][j];
        }else{
            memo[i][j]=matrix[i][j]+min(dp(matrix,i+1,j),dp(matrix,i+1,j-1),dp(matrix,i+1,j+1));
        }
        return memo[i][j];
    }

    private int min(int a, int b, int c) {
        return Math.min(a,Math.min(b,c));
    }
}
