package array;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 螺旋矩阵 https://leetcode.cn/problems/spiral-matrix/
 * @author: lyq
 * @createDate: 29/4/2023
 * @version: 1.0
 */
public class spiralMatrix {
    //顺时针螺旋遍历一个二维数组，算法的核心就是规定遍历的方向以及边界
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int upper = 0;//上边界
        int lower = matrix.length - 1;//下边界的下标
        int left = 0;//左边界
        int right = matrix[0].length - 1;//右边界
        List<Integer> res = new LinkedList<>();
        while (res.size() < m * n) {
            if(upper<=lower){
                //向右进行遍历
                for (int i = left; i <= right; i++) {
                    res.add(matrix[upper][i]);
                }
                upper++;//遍历完一行之后，上边界下移
            }
            if(left<=right){
                for (int i = upper; i <=lower; i++) {
                    //从上到下进行遍历
                    res.add(matrix[i][right]);
                }
                //遍历完之后，右边界左移一位
                right--;
            }
            if(upper<=lower){
                for (int i = right; i >=left ; i--) {
                    //从右往左遍历
                    res.add(matrix[lower][i]);
                }
                //遍历完之后，下边界上移一位
                lower--;
            }
            if(left<=right){
                for (int i = lower; i >=upper ; i--) {
                    //从下往上遍历
                    res.add(matrix[i][left]);
                }
                //遍历完之后，左边界右移一位
                left++;
            }
        }
        return res;
    }
}
