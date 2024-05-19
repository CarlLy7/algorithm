package test;

/**
 * @description: 堆排序
 * @author: lyq
 * @createDate: 7/3/2023
 * @version: 1.0
 */
public class heap {
    static int heapLen;
    public static void buildMaxHeap(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjuestHeap(array, i);
        }
    }

    private static void adjuestHeap(int[] array, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < heapLen && array[left] > array[largest]) {
            largest = left;
        }
        if (right < heapLen && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(array, i, largest);
            adjuestHeap(array,largest);
        }

    }

    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    public static void heapSort(int[] arr) {
        heapLen= arr.length;
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr,0,i);
            heapLen-=1;
            adjuestHeap(arr,0);
        }
    }

    public static void main(String[] args) {
        int[] test=new int[]{5,2,7,5,1};
        heapSort(test);
        for (int i : test) {
            System.out.print(i+" ");
        }
    }
}
