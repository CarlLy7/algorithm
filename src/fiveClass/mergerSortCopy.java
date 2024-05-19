package fiveClass;

/**
 * @description:
 * @author: lyq
 * @createDate: 26/2/2023
 * @version: 1.0
 */
public class mergerSortCopy {
    public static void mergeSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int n = array.length;
        int mergeSize = 1;//步长
        while (mergeSize < n) {
            int l = 0;
            while (l < n) { //保证左组的起点不能超过整个数组的范围
                int m = l + mergeSize - 1;//左组的右边界
                if (m >= n) {
                    break;
                }
                int r = Math.min(m + mergeSize, n - 1);//右组的右边界
                merger(array, l, m, r);
                l = r + 1;
            }
            if (mergeSize > n / 2) {
                break;
            }
            mergeSize <<= 1;
        }

    }

    private static void merger(int[] array, int l, int m, int r) {
        int[] temp=new int[r-l+1];
        int i=0;
        int p1=l;
        int p2=m+1;
        while(p1<=m&&p2<=r){
            temp[i++]=array[p1]<=array[p2]?array[p1++] :array[p2++]; ;
        }
        while(p1<=m){
            temp[i++]=array[p1++];
        }
        while(p2<=r){
            temp[i++]=array[p2++];
        }
        for (int j = 0; j < temp.length; j++) {
            array[l+j]=temp[j];
        }
    }

    public static void main(String[] args) {
        int[] test=new int[]{2,2,2,2,2,2};
        mergeSort(test);
        for (int i : test) {
            System.out.print(i+",");
        }
    }
}
