package threeClas;

import com.sun.scenario.animation.shared.ClipEnvelope;

/**
 * @description: 学习时间复杂度写的代码
 * @createDate: 23/8/2022
 * @version: 1.0
 */
public class TimeComplexity {
    public static void main(String[] args) {
        int testTime = 5000;
        int maxLen = 50;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] a = randomArray(maxLen, maxValue);
//            bubbleSort(a);
            insertSort(a);
            if (!isSorted(a)) {
                System.out.println("排序错误");
                for (int i1 : a) {
                    System.out.print(i1 + " ");
                }
                break;
            }
        }
        System.out.println();
            System.out.println("排序正确");
    }

    /**
     * 冒泡排序算法  时间复杂度O(N)
     * 15 7 3 12 35
     *
     * @param a
     */
    public static void bubbleSort(int[] a) {
        int i, j;
        for (i = 0; i < a.length - 1; i++) {  //需要冒泡的趟数是元素个数减一
            for (j = 0; j < a.length - 1 - i; j++) {  //每一趟比较的次数
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 王道的插入排序 插入排序算法   时间复杂度为O(n²)
     * 15 36 14 2 35
     */
    public static void insertSort(int[] a) {
        int i, j,temp;
        for (i = 1; i < a.length; i++) {  //插入排序需要进行n-1趟排序,默认第一个数是有序的，所以从第二个数开始进行插入排序
           if(a[i]<a[i-1]){
               temp=a[i];
               for (j=i-1;j>=0 && a[j]>temp;j--){
                   a[j+1]=a[j];
               }
               a[j+1]=temp;
           }
        }
    }

    /**
     * 左神的插入排序
     * @param a
     */
//    public static void insertSort(int[] a) {
//        if (a.length <= 1) {
//            return;
//        } else {
//            for (int i = 1; i < a.length; i++) {
//                for (int j = i - 1; j >= 0 && a[j] > a[j + 1]; j--) {
//                    swap(a, j, j + 1);
//                }
//            }
//        }
//    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 写个对数器
     */

    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen + 1);
        int[] a = new int[len];
        for (int i = 0; i < len; i++) {
            a[i] = (int) (Math.random() * maxValue);
        }
        return a;
    }

    public static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
