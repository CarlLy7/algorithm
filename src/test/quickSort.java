package test;

/**
 * @description: 快速排序  递归实现
 * @author: lyq
 * @createDate: 5/3/2023
 * @version: 1.0
 */
public class quickSort {
    //得到基准应该存放的位置,默认使用第一个位置的元素作为基准
    public static int partion(int[] array, int low, int high) {
        int flag = array[low];
        while (low < high) {
            while (low < high && array[high] >= flag) high--;
            array[low] = array[high];
            while (low < high && array[low] <= flag) low++;
            array[high]=array[low];
        }
        array[low]=flag;
        return low;
    }
    public static int[] sort(int[] array,int low,int high){
        if(low<high){
            int partion = partion(array, low, high);
            sort(array,low,partion-1);
            sort(array,partion+1,high);
            return array;
        }
        return array;
    }
    public static void main(String[] args) {
        int[] test=new int[]{5,3,2,10,7,9};
        test=sort(test,0, test.length-1);
        for (int i : test) {
            System.out.print(i+",");
        }
    }
}
