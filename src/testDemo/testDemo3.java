package testDemo;

/**
 * @description: 堆排序
 * @author: lyq
 * @createDate: 10/3/2023
 * @version: 1.0
 */
public class testDemo3 {
    static int heapLen;
    public static void buildMaxHeap(int[] array) {
        //建立大顶堆
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            //调整堆，让堆保持一个大顶堆的状态
            adjustHeap(array, i);
        }
    }

    private static void adjustHeap(int[] array, int k) {
        int l = k * 2 + 1;//左孩子
        int r = k * 2 + 2;//右孩子
        int largest=k;//此时最大的值，默认为k这个节点的值
        if(l<heapLen && array[l]>array[largest]){
            largest=l;
        }
        if(r<heapLen && array[r]>array[largest]){
            largest=r;
        }
        if(largest!=k){
            swap(array,k,largest);
            adjustHeap(array,largest);
        }
    }

    private static void swap(int[] array, int k, int largest) {
        int temp=array[k];
        array[k]=array[largest];
        array[largest]=temp;
    }
    public static void heapSort(int[] array){
        heapLen= array.length;
        buildMaxHeap(array);
        for (int i = heapLen-1; i >0 ; i--) {
            swap(array,0,i);
            heapLen-=1;
            adjustHeap(array,0);
        }
    }

    public static void main(String[] args) {
        int[] test=new int[]{3,5,1,2,6,43};
        heapSort(test);
        for (int i : test) {
            System.out.print(i+" ");
        }
    }
}
