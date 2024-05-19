package fourClass;

/**
 * @description: 求一个数组中的逆序对的个数
 * 逆序对：后面的数比这个数小 比如： 7,2,4,5,3,1  【7,2】【7,4】【7,5】【7，3】【7,1】都是逆序对
 * 思路：
 * 也是使用归并排序的思路：
 * 不过这次我们从右边开始忘前找，如果左右组相等的时候依然是让右边组的数放入辅助数组中
 * 只有左组的数落到辅助数组的时候才计算个数，此时右边组中的个数就是左边这个数的逆序对个数   2,4,7  1,3,5
 * @author: lyq
 * @createDate: 23/2/2023
 * @version: 1.0
 */
public class ReversePairNumber {
    public static int getReversePairNumber(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int ans = process(array, 0, array.length - 1);
        return ans;
    }

    private static int process(int[] array, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(array,l,m)+process(array,m+1,r)+merge(array,l,m,r);
    }

    private static int merge(int[] array, int l, int m, int r) {
        int[] temp=new int[r-l+1];
        int i= temp.length-1;//指向辅助数组的最后一个位置，因为我们是倒着来的
        int p1=m;//指向左组的右边界
        int p2=r;//指向右组的右边界
        int ans=0;
        while(p1>=l && p2>m){
            ans+=array[p1]>array[p2]?(p2-m):0;
            temp[i--]=array[p1]>array[p2]?array[p1--]:array[p2--];
        }
        while(p1>=l){
            temp[i--]=array[p1--];
        }
        while(p2>m){
            temp[i--]=array[p2--];
        }
        for (int j = 0; j < temp.length; j++) {
            array[l+j]=temp[j];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test=new int[]{2,3,6,1,4,6};
        System.out.println(getReversePairNumber(test));
    }
}
