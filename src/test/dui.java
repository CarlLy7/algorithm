package test;

/**
 * @description: 堆排序
 * @author: lyq
 * @createDate: 5/3/2023
 * @version: 1.0
 */
public class dui {
    static int heapLen;
    public static void buildMaxHeap(int[] a){
        for (int i = a.length/2-1; i >=0 ; i--) {
            adjustHeap(a,i);
        }
    }

    private static void adjustHeap(int[] a, int i) {
        int left=2*i+1;
        int right=2*i+2;
        int largest=i;
        if(left<heapLen && a[left]>a[largest]){
            largest=left;
        }
        if(right<heapLen && a[right]>a[largest]){
            largest=right;
        }
        if(largest!=i){
            swap(a,i,largest);
            adjustHeap(a,largest);
        }
    }
    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
    public static void heapSort(int[] a){
        heapLen=a.length;
        buildMaxHeap(a);
        for (int i = a.length-1; i >0 ; i--) {
            swap(a,0,i);
            heapLen-=1;
            adjustHeap(a,0);
        }
    }
    public static void main(String[] args) {
        int[] test=new int[]{5,2,7,5,1};
        heapSort(test);
        for (int i : test) {
            System.out.print(i+",");
        }
    }
}
