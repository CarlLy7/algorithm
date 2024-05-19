package oneClass;


/**
 * @description: 归并排序算法  时间复杂度O（nlog2n）不管给的数组是有序的还是无序的时间复杂度不变  空间复杂度 O（n）主要就是辅助数组的大小，如果数据量太大的话会消耗大量空间而且在复制的过程会浪费大量时间
 * @author: lyq
 * @createDate: 14/8/2022
 * @version: 1.0
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27,-1};
//        mergeSort(a, 0, 6);
        mergeSortFuXi(a,0,7);
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * 将两个有序的数组合成一个有序的数组的方法
     *
     * @param a:需要进行合并的数组
     * @param low:左边有序数组的第一个元素的下标
     * @param mid：左边有序数组的最后一个元素的下标
     * @param high：右边有序数组的第一个元素的下标
     */
    public static void merge(int[] a, int low, int mid, int high) {
        int[] b = new int[a.length]; //这个是辅助数组
        int i, j, k;
        for (i = low; i <= high; i++) {  //这个循环是将所有的元素复制到辅助数组中
            b[i] = a[i];
        }
        for (i = low, j = mid + 1, k = i; i <= mid && j <= high; k++) {
            if (b[i] <= b[j]) {
                a[k] = b[i++];
            } else {
                a[k] = b[j++];
            }
        }
        while (i <= mid) {
            a[k++] = b[i++];
            k++;
            i++;
        }
        while (j <= high) {
            a[k++] = b[j++];
        }
    }

    /**
     * 进行归并排序的算法，使用了递归思想
     *
     * @param a：待排序的数组
     * @param low：数组的第一个元素
     * @param high：数组的最后一个元素
     */
    public static void mergeSort(int[] a, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(a, low, mid);//对左边元素进行递归排序
            mergeSort(a, mid + 1, high);//对右边的元素进行递归排序
            merge(a, low, mid, high); //再对左右两边都有序的这个数组进行归并
        }
    }


    /**
     * 归并排序算法的复习
     * 首先梳理一下算法的思路：
     *   首先这个算法不难，不要右畏惧心理
     *   首先我们要创建一个辅助数组，这个数组的大小应该和我们给出的数组的大小一样大，用来将我们给的原始的数组进行一个复制，可以认为是一个中转站
     *   然后我们要使用递归算法来进行排序，首先我们要从中间分开，先对左边进行归并，然后再对右边进行归并，然后我们再对这个大的数组进行一个归并
     *   其中最关键的算法就是我们进行归并的这个方法了，这个方法进行将两个有序的数组进行归并，我们将左边和右边的数组进行比较，将小的放在原来数组的前面大的放在后面就ok了
     *
     */

    /**
     * 归并排序复习
     *
     * @param a
     * @param low
     * @param high
     */
    public static void mergeSortFuXi(int[] a, int low, int high) {
        //使用递归来实现
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSortFuXi(a, low, mid);//对左边的元素进行归并
            mergeSortFuXi(a, mid + 1, high);//对右边的元素进行一个归并
            mergeFuXi(a, low, mid, high);
        }
    }

    /**
     * 归并过程的方法复习
     * @param a
     * @param low
     * @param mid
     * @param high
     */
    public static void mergeFuXi(int[] a,int low,int mid,int high){
        int i,j,k;
        int[] b=new int[a.length];
        for (i=low;i<=high;i++){
            b[i]=a[i];
        }
        for (i=low,j=mid+1,k=i;i<=mid && j<=high;k++){
            if(b[i]<=b[j]){
                a[k]=b[i];
                i++;
            }else{
                a[k]=b[j];
                j++;
            }
        }
        //当for循环退出之后可能是有一边已经越界了，我们只需要让没有越界的那部分一次放到a数组的后面就可以了
        while(i<=mid){
            a[k]=b[i];
            k++;
            i++;
        }
        while(j<=high){
            a[k]=b[j];
            k++;
            j++;
        }

    }

}


