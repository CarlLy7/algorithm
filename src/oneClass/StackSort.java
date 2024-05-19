package oneClass;

/**
 * @description: 堆排序算法
 * 思路整理：   首先我们需要建立一个大顶堆
 * 然后我们建立一个调整堆的算法让我们的堆能调整成大顶堆，里面需要注意的就是小元素要一层层的往下沉
 * 排序的时候我们需要进行元素个数-1趟的排序，每次交换第一个元素和最后一个元素就会把最大的数放到最后面，最终得到一个递增的数组
 * @author: lyq
 * @createDate: 6/8/2022
 * @version: 1.0
 */
public class StackSort {
    public static void main(String[] args) {
        int[] a = {-1, 53, 17, 78, 9, 45, 65, 87, 32};
        stackSortFuXi(a, 8);
        for (int i = 1; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     * 建立大顶堆的方法  时间复杂度O(n)
     *
     * @param a：数组,注意我们默认第一个位置是一个中转的位置，实际有效的元素下标从1开始
     * @param len：数组长度
     */
    public static void buildMaxStack(int[] a, int len) {
        //默认从最下面的堆顶节点开始逐次往上
        for (int i = len / 2; i > 0; i--) {
            HeadAdjust(a, i, len);
        }
    }

    /**
     * 小元素下坠来调整大顶堆，让这个堆始终保持一个大顶堆
     *
     * @param a:数组
     * @param k：当前的根节点的下标
     * @param len：数组的长度
     */
    public static void HeadAdjust(int[] a, int k, int len) {
        a[0] = a[k];//先用a[0]把我们的当前的根节点保存起来
        for (int i = k * 2; i <= len; i *= 2) {   //从当前节点的左孩子开始进行遍历，为什么进行一个循环每次都乘2呢，因为小元素可能会逐层的下坠，所以这里我们才需要进行一个循环
            if (i < len && a[i] < a[i + 1]) {  //如果右孩子比左孩子大的话，i变成右孩子
                i++;
            }
            if (a[0] >= a[i])
                break; //如果当前的根节点大于等于左右孩子就退出这个循环
            else {
                a[k] = a[i];
                k = i;
            }
        }
        //当小元素下沉到的最下面的一层后，将根元素放到这里
        a[k] = a[0];
    }

    /**
     * 堆排序的方法 时间复杂度 O（nlog2n）
     *
     * @param a
     * @param len
     */
    public static void stackSort(int[] a, int len) {
        //堆排序需要进行len-1趟的排序
        buildMaxStack(a, len);
        for (int i = len; i > 1; i--) {
            //将第一个位置的元素和最后一个位置的元素互换，可以确定最后面那个最大的数
            int temp = a[1];
            a[1] = a[i];
            a[i] = temp;
            //然后调整堆为大顶堆
            HeadAdjust(a, 1, i - 1);  //为什么i-1呢，因为最后一个元素已经有序了，所以数量就是减一了
        }
    }


    /**
     * 创建大顶堆的方法复习
     *
     * @param a
     * @param len
     */
    public static void buildBigStackFuXi(int[] a, int len) {
        for (int i = len/2; i>0 ; i--) {
            HeadAdJustFuXi(a,i,len);
        }
    }

    /**
     * 调整大顶堆的复习方法
     *
     * @param a
     * @param k
     * @param len
     */
    public static void HeadAdJustFuXi(int[] a, int k, int len) {
        a[0]=a[k];
        for (int i = k*2; i <=len ; i*=2) {
            if(i<len && a[i]<a[i+1]){
                i++;
            }
            if(a[0]>=a[i]){
                break;
            }else{
                a[k]=a[i];
                k=i;
            }
        }
        //退出循环之后留下了一个空位置，这个位置放根元素
        a[k]=a[0];
    }

    /**
     * 堆排序的复习
     *
     * @param a
     * @param len
     */
    public static void stackSortFuXi(int[] a, int len) {
       buildMaxStack(a,len);
        for (int i = len; i >1 ; i--) {
            int temp=a[1];
            a[1]=a[i];
            a[i]=temp;
            HeadAdJustFuXi(a,1,i-1);
        }
    }
}
