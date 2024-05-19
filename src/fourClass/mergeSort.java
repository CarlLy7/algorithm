package fourClass;

/**
 * @description: 归并排序训练
 * @author: lyq
 * @createDate: 28/8/2022
 * @version: 1.0
 */
public class mergeSort {

    public static void merge(int[] arr,int l,int m,int r){
        int[] temp=new int[r-l+1];//辅助数组的大小
        int p1=l;//指向左组的第一个位置
        int p2=m+1;//指向右组的第一个位置
        int i=0;//只指向辅助数组
        while(p1<=m && p2<=r){
            temp[i++]=arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
        }
        //下面的两个循环就是有一边有剩余的话怎么处理
        while(p1<=m){
            temp[i++]=arr[p1++];
        }
        while(p2<=r){
            temp[i++]=arr[p2++];
        }
        for (int j = 0; j < temp.length; j++) {
            arr[l+j]=temp[j];
        }
    }

    //归并排序
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        int mergerSize = 1;//归并的步长
        while (mergerSize < n) {
            int l = 0;//左组的第一个位置
            while (l < n) { //左组的第一个位置最大就是最后一个位置
                int m = l + mergerSize - 1;//左组的右边界
                if (m >= n) { //如果左组的右边界超了整个数组的范围的话，直接退出循环
                    break;
                }
                int r = Math.min(m + mergerSize, n - 1);//右组的右边界
                //调用合并的方法
                merge(arr, l, m, r);
                l = r + 1;//下一次合并的时候，左组的第一个位置为右组的右边界+1
            }
            //可以减少一次循环
            if (mergerSize > n / 2) {
                break;
            }
            mergerSize <<= 1;//步长每次乘2
        }
    }


    public static void main(String[] args) {
        int[] test=new int[]{2,3,6,1,4,6};
        mergeSort2(test);
        for (int i : test) {
            System.out.print(i+",");
        }
    }


}
