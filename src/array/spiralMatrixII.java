package array;

/**
 * @description: 螺旋矩阵 II https://leetcode.cn/problems/spiral-matrix-ii/
 * @author: lyq
 * @createDate: 29/4/2023
 * @version: 1.0
 */
public class spiralMatrixII {
    //顺时针螺旋生成一个二维数组,关键依然是控制遍历方向以及四个边界
    public int[][] generateMatrix(int n) {
        int upper = 0;//上边界
        int lower = n - 1;//下边界
        int left = 0;//左边界
        int right = n - 1;//右边界
        int count = 1;//计数器，也是填进去的值
        int[][] res=new int[n][n];//返回的二维数组
        while (count <= n * n) {
            //下面的操作可千万别转晕了
            if(upper<=lower){
                //从左往右遍历
                for (int j = left; j <=right ; j++) {
                    res[upper][j]=count++;
                }
                //遍历完之后，上边界下移一位
                upper++;
            }
            if(left<=right){
                for (int i = upper; i <=lower ; i++) {
                    //从上往下遍历
                    res[i][right]=count++;
                }
                //遍历完之后右边界左移一位
                right--;
            }
            if(upper<=lower){
                //从右往左遍历
                for (int j = right; j >=left ; j--) {
                    res[lower][j]=count++;
                }
               //遍历完之后下边界上移一位
                lower--;
            }
            if(left<=right){
                //从下往上遍历
                for (int i = lower; i >=upper ; i--) {
                    res[i][left]=count++;
                }
                //遍历完之后，左边界右移一位
                left++;
            }
        }
        return res;
    }
}
