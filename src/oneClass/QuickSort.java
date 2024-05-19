package oneClass;

import java.util.Arrays;

/**
 * @description: 快速排序, 需要递归实现
 * @author: lyq
 * @createDate: 3/8/2022
 * @version: 1.0
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49,-1};
//        quickSort(a, 0, 7);
//        quickSortFuXi(a, 0, 7);
        quickSortFuXi(a,0,8);
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pivotPosition = Partition(a, low, high);//找到基准位置
            quickSort(a, low, pivotPosition - 1);//对左边的元素使用递归进行排序
            quickSort(a, pivotPosition + 1, high);//对右边的元素使用递归进行排序
        }
    }

    /**
     * 进行划分找到基准的方法
     *
     * @param a
     * @param low
     * @param high
     * @return
     */
    private static int Partition(int[] a, int low, int high) {
        int pivot = a[low];//认为low位置的元素作为基准
        while (low < high) {
            //从high出发，如果a[high]位置的元素比基准大的话，high--
            while (low < high && a[high] >= pivot)
                high--;
            //到我们从high出发后找到了比基准小的元素，我们就将这个元素放到low位置上，然后从low开始往后找
            a[low] = a[high];
            while (low < high && a[low] < pivot)
                low++;
            a[high] = a[low];
        }
        //到low==high会退出while循环，此时low==high 的位置就是我们这个基准要放进去的位置
        a[low] = pivot;
        return low;

    }




    /**
     * 快速排序的复习
     * 先整理一下思路：
     * 快速排序和归并排序一样都需要使用递归算法来实现
     * 首先我们可以让第一个元素作为基准，然后把比他小的元素放在它的左边，比它大的元素放在它的右边，最后肯定会空出来一个位置，这个位置就放我们的基准元素
     * 并且把这个基准的位置返回出去，这样我们就知道哪个位置的左右部分有序了，所以我们就可以对基准的左边元素和右边元素使用递归算法
     */

    public static void quickSortFuXi(int[] a, int low, int high) {
        if(low<high){
            int standard=getPosition(a,low,high);
            quickSortFuXi(a,low,standard-1);//对左边的数据进行递归快排
            quickSortFuXi(a,standard+1,high);//对右边的数据进行递归快排
        }
    }

    /**
     * 得到基准位置的方法
     * @param a
     * @param low
     * @param high
     * @return
     */
    private static int getPosition(int[] a, int low, int high) {
        int temp=a[low];
        while(low<high){
            //先从右边开始看，如果比基准大就让high--，如果比基准小就放到左边
            while(low<high && a[high]>=temp){
                high--;
            }
            //如果右边的数据比基准元素小的话，把这个数据放到左边
            a[low]=a[high];
            //如果右边比基准小了，就来看左边，如果比基准小的话，就让low++，如果比基准大的话放到右边
            while(low<high && a[low]<=temp){
                low++;
            }
            //比基准大，放到右边
            a[high]=a[low];
        }
        //这个循环退出的位置，low=high就是我们基准要放进去的位置，也是基准的位置
        a[low]=temp;
        return low;
    }
}
