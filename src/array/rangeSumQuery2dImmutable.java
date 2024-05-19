package array;

/**
 * @description: 二维区域和检索 - 矩阵不可变 https://leetcode.cn/problems/range-sum-query-2d-immutable/
 * @author: lyq
 * @createDate: 28/4/2023
 * @version: 1.0
 */
public class rangeSumQuery2dImmutable {
    class NumMatrix {
        //二维数组来表示前缀和
        private int[][] presum;
        public NumMatrix(int[][] matrix) {
            int m=matrix.length;
            int n=matrix[0].length;
            presum=new int[m+1][n+1];
            //计算原点(0,0)到所有位置(i,j)的元素和
            for (int i = 1; i <=m ; i++) {
                for (int j = 1; j <=n ; j++) {
                    //（i,j）位置的前置和等于上一行的前缀和+前一列的前缀和+当前位置的元素-左上角位置的前缀和，因为加重复了
                    //其实也可以简单的理解为和下面方法中求[row1,col1,row2,col2]的前缀和一样，通过相邻的几个矩阵相加减来求
                    presum[i][j]=presum[i-1][j]+presum[i][j-1]+matrix[i-1][j-1]-presum[i-1][j-1];
                }
            }
        }

        //二维数组中的任何一个位置的前缀和可以有四个相邻的数组加减组成
        public int sumRegion(int row1, int col1, int row2, int col2) {
            //因为我们的presum前缀和数组整体右移和下移了一位，所以presum[i][j]其实求的前置和不包括(i,j)的
                return presum[row2+1][col2+1]-presum[row1][col2+1]-presum[row2+1][col1]+presum[row1][col1];
        }
    }
}
