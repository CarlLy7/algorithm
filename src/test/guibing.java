package test;

/**
 * @description: 归并排序
 * @author: lyq
 * @createDate: 7/3/2023
 * @version: 1.0
 */
public class guibing {
    public static void mergerSort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        int n = array.length;
        int mergeSize = 1;//步长
        while (mergeSize < n) {
            int l=0;//左组的开始
            while(l<n){
                int m=l+mergeSize-1;//左组的右边界
                if(m>n){
                    break;
                }
                int r=Math.min(m+mergeSize,n-1);
                merge(array,l,m,r);
                l=r+1;
            }
            if(mergeSize>n/2){
                break;
            }
            mergeSize<<=1;
        }
    }

    private static void merge(int[] array, int l, int m, int r) {
        int[] temp=new int[r-l+1];
        int p1=l;
        int p2=m+1;
        int i=0;
        while(p1<=m && p2<=r){
            temp[i++]=array[p1]<=array[p2]?array[p1++] :array[p2++];
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
        int[] test=new int[]{1,4,2,6,7,6};
        mergerSort(test);
        for (int i : test) {
            System.out.print(i+" ");
        }
    }

}
