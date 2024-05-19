package array;

/**
 * @description: 旋转图像 https://leetcode.cn/problems/rotate-image/
 * @author: lyq
 * @createDate: 29/4/2023
 * @version: 1.0
 */
public class rotateImage {
    //顺时针旋转二维数组
    //旋转二维数组的关键是将行转成列，列转成行，而按照对角线对称的话可以很轻松的完成这点，最后只需要将行进行倒转就可以得到想要的结果
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j =i; j <m ; j++) {
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }
        for (int[] ints : matrix) {
            reverse(ints);
        }
    }

    //将这行数据进行反转
    private void reverse(int[] ints) {
        int j=ints.length-1;
        int i=0;
        while(j>i){
            int temp=ints[i];
            ints[i]=ints[j];
            ints[j]=temp;
            i++;
            j--;
        }
    }

    //逆时针旋转二维数组
    public void rotateNi(int[][] matrix) {
        int n=matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j =0; j <n-i ; j++) {
                int temp=matrix[i][j];
                matrix[i][j]=matrix[n-j-1][n-i-1];
                matrix[n-j-1][n-i-1]=temp;
            }
        }
        for (int[] ints : matrix) {
            reverse(ints);
        }
    }
}
