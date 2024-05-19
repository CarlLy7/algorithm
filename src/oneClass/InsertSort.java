package oneClass;

import java.sql.PreparedStatement;

/**
 * @description: 插入排序算法
 * @author: lyq
 * @createDate: 1/8/2022
 * @version: 1.0
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49};
        int[] b = {15, 35, 21, 35, 10, 46};
//        InsertSortFuXi(a);
        insertSortYouHuaFuXi(b);
        for (int i : b) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    //未优化的插入排序
    public static void insertSort(int[] a) {
        int i, j, temp;
        //默认第一个数据是有序的，从后面开始和前面的进行比较然后排序
        for (i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                temp = a[i];
                for (j = i - 1; j >= 0 && a[j] > temp; --j) { //如果前面的这个数比i位置的数大的话就后移
                    a[j + 1] = a[j];//后移
                }
                a[j + 1] = temp;//把i位置的数据放到属于它的位置
            }
        }
    }

    //使用折半查找对插入排序进行优化

    /**
     * 折半查找的优化就是对插入排序中的前面的比较部分使用折半查找进行比较
     * 折半查找不能将时间复杂度缩短，插入排序的平均复杂度依然是o(n²),只是减少了比较的次数，但是移动元素的次数依然不变
     *
     * @param a
     */
    public static void insertSort2(int[] a) {
        int i, j, temp;
        int low, high, mid;
        for (i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                temp = a[i];
                low = 0;
                high = i - 1;
                //进行折半查找比较前面的数据
                while (low <= high) {
                    mid = low + ((high - low) >> 1); //左边加两点之间距离的一半，这么写是防止溢出
                    if (a[mid] <= temp) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                //将low到i-1位置的元素整体后移一位
                for (j = i - 1; j >= low; j--) {
                    a[j + 1] = a[j];
                }
                a[high + 1] = temp; //折半查找完了之后将元素放到合适的位置
            }
        }
    }


    /**
     * 复习未优化的插入排序算法
     * 15 35 21  10  56
     *
     * @param a
     */
    public static void InsertSortFuXi(int[] a) {
        int i, j, temp;
        for (i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                temp = a[i];
                for (j = i - 1; j >= 0 && a[j] > temp; j--) {
                    //后移
                    a[j + 1] = a[j];
                }
                a[j + 1] = temp;
            }
        }
    }

    /**
     * 复习优化后的插入排序,所谓的优化就是对我们前面进行比较的元素进行折半查找
     * 15 21 35  10  56
     *
     * @param a
     */
    public static void insertSortYouHuaFuXi(int[] a) {
        int i, j, temp;
        int low, high;
        for (i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                temp = a[i];
                low = 0;
                high = i - 1;
                while (low <= high) {
                    int mid = (low + high) / 2;
                    if (a[mid] > temp) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
                //移动
                for (j = i - 1; j >= low; j--) {
                    a[j + 1] = a[j];
                }
                a[low] = temp;
            }
        }
    }


}
