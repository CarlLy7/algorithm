package test;

/**
 * @description: 插入排序，想象成打牌，从后往前插入
 * @author: lyq
 * @createDate: 5/3/2023
 * @version: 1.0
 */
public class charu {
    public static int[] insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) { //轮数
            int pre = i - 1;
            int current = arr[i];
            while (pre >= 0 && current < arr[pre]) {
                arr[pre + 1] = arr[pre];
                pre -= 1;
            }
            arr[pre + 1] = current;
        }
        return arr;
    }

    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {
        int[] test = new int[]{3, 5, 2, 10, 7, 9};
        test = insertSort(test);
        for (int i : test) {
            System.out.print(i + ",");
        }
    }
}
