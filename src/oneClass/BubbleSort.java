package oneClass;

/**
 * @description: 冒泡排序算法
 * @author: lyq
 * @createDate: 3/8/2022
 * @version: 1.0
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 2};
//        beforeBubbleSort(a);
//        beforeBubbleSortFuXi(a);
        afterBubbleSortFuXi(a);

        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    /**
     * 冒泡排序算法(从前往后冒泡)
     *
     * @param a:数组
     * @param n:元素个数
     */
    public static void bubbleSort(int[] a, int n) {
        //冒泡排序需要进行元素个数减一次冒泡
        for (int i = 0; i <= n - 2; i++) {
            //定义一个标志是否进行交换的变量，如果这一趟的冒泡中没有进行交换的话说明是有序的了
            boolean flag = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;
                }
            }
            if (flag == false) {
                return;
            }
        }
    }

    /**
     * 从后往前冒
     *
     * @param a
     */
    public static void railSort(int[] a) {
        for (int i = 0; i <= a.length - 1; i++) {
            boolean flag = false;
            for (int j = a.length - 1; j > i; j--) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                    flag = true;
                }
            }
            if (flag == false) {
                return;
            }
        }
    }


    /**
     * 从前往后冒
     */
    public static void beforeBubbleSort(int[] a) {
        //冒泡排序需要进行元素个数减一次冒泡
        for (int i = 0; i < a.length; i++) {
            //定义一个标志是否进行交换的变量，如果这一趟的冒泡中没有进行交换的话说明是有序的了
            boolean flag = false;
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;
                }
            }
            if (flag == false) {
                return;
            }
        }
    }

    /**
     * 从前往后冒泡的复习
     *
     * @param a
     */
    public static void beforeBubbleSortFuXi(int[] a) {
        for (int i = 0; i < a.length - 1; i++) { //进行元素个数减一趟的排序
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 从后往前冒泡的方法复习
     *  1 2 3 4
     * @param a
     */
    public static void afterBubbleSortFuXi(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = a.length-1; j >i ; j--) {
                if(a[j]<a[j-1]){
                    int temp=a[j];
                    a[j]=a[j-1];
                    a[j-1]=temp;
                }
            }
        }
    }

}
