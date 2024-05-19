package test;

/**
 * @description: 归并排序
 * @author: lyq
 * @createDate: 5/3/2023
 * @version: 1.0
 */
public class merger {
    public static void mergeSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int n = array.length;
        int mergeSize = 1;//初始步长

        while (mergeSize < n) {
            int l=0;//左组的开头
            while(l<n){ //左组的开头不能超过数组下标
                int m=l+mergeSize-1;//左组的右边界
                if(m>n){
                    break;//如果右边界超过数组范围了，直接退出
                }
                int r=Math.min(m+mergeSize,n-1);//右组的右边界
                mergerProcess(array,l,m,r);
                l=r+1;//下次合并左组的左边界是上次的右组的右边界+1
            }
            if(mergeSize>n/2){
                break;
            }
            mergeSize<<=1;
        }
    }

    private static void mergerProcess(int[] array, int l, int m, int r) {
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
        int[] test=new int[]{2,3,6,1,4,6};
        mergeSort(test);
        for (int i : test) {
            System.out.print(i+",");
        }
    }
}
