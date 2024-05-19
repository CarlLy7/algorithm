package oneClass;

/**
 * @description: 希尔排序
 * 希尔排序思想：
 * 我们按照步长每次减半的规则对数组进行分组，我们对每一组中的元素进行一个插入排序算法进行排序
 * @author: lyq
 * @createDate: 2/8/2022
 * @version: 1.0
 */
public class HillSort {
    public static void main(String[] args) {
        int[] a = {-1, 49, 38, 65, 97, 76, 13, 27, 49};
//        hillSort(a, 8);
//        fuXiHillSort(a, 8);
        hillSortFuXi(a, 8);
        for (int i = 1; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void hillSort(int[] a, int n) {
        int i, j, d;
        for (d = n / 2; d >= 1; d = d / 2) { //步长每次缩短一半
            for (i = 1 + d; i <= n; ++i) {
                if (a[i] < a[i - d]) {
                    a[0] = a[i]; //如果这个数据比前面的数据小的话，我们先把他放到a[0]位置，然后我们再比较前面的数据，如果比它大就后移
                    for (j = i - d; j > 0 && a[j] > a[0]; j -= d) {
                        a[j + d] = a[j];
                    }
                    a[j + d] = a[0];
                }
            }
        }
    }

    /**
     * 复习希尔排序算法
     *
     * @param a
     * @param n
     */
    public static void fuXiHillSort(int[] a, int n) {
        int i, j, d;
        for (d = n / 2; d >= 1; d = d / 2) { //按照步长进行分进行几趟
            for (i = d + 1; i <= n; i++) {//我们默认为a[0]位置的元素是无用的，所以我们要进行处理的第一个元素是1+步长 位置的元素
                if (a[i] < a[i - d]) {
                    a[0] = a[i];
                    for (j = i - d; j > 0 && a[j] > a[0]; j -= d) {
                        a[j + d] = a[j];
                    }
                    a[j + d] = a[0];
                }

            }
        }
    }


    /**
     * 希尔排序的复习
     *
     * @param a:插入的待排序的数组
     * @param n：数组中有效元素的个数，希尔排序中默认数组中的第一个位置的元素是没有实际意义的
     */
    public static void hillSortFuXi(int[] a, int n) {
        int i, j, d;
        for (d = n / 2; d >= 1; d = d / 2) {    //循环进行的次数取决于步长，从元素个数减半开始一直到步长为1位置
            //希尔排序就是分组使用插入排序
            for (i = 1 + d; i <= n; i++) {
                if (a[i] < a[i - d]) {
                    a[0] = a[i];
                    for (j = i - d; j > 0 && a[j] > a[0]; j -= d) {//如果比a[0]位置存放的元素大的话就往后移动
                        a[j + d] = a[j];
                    }
                    //移动完了之后把a[0]元素放到应该放的位置，也就是a[j+d]位置
                    a[j + d] = a[0];
                }

            }
        }
    }


}
