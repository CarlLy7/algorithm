package test;

/**
 * @description: 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
 *                  判断数组中是否含有该整数
 * @author: lyq
 * @createDate: 4/3/2023
 * @version: 1.0
 */
public class findTarget {
    public static boolean find(int[][] array,int target){
        int row= array.length-1;
        int column=0;
        //从左下角开始找
        while((row>=0) && (column<array[0].length)){
            if(target<array[row][column]){
                 row--;
            }else if(target>array[row][column]){
                column++;
            }else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] test=new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(find(test,0));
    }
}
